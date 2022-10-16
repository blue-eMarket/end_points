package com.codathon.blue_eMatket_api.services;

import com.codathon.blue_eMatket_api.dto.ProductImageReqDto;
import com.codathon.blue_eMatket_api.dto.ProductImageRespDto;
import com.codathon.blue_eMatket_api.dto.UserRespDto;
import com.codathon.blue_eMatket_api.model.Product;
import com.codathon.blue_eMatket_api.model.ProductImages;
import com.codathon.blue_eMatket_api.model.Role;
import com.codathon.blue_eMatket_api.model.Users;
import com.codathon.blue_eMatket_api.repo.ProductImageRepository;
import com.codathon.blue_eMatket_api.repo.ProductRepository;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
@Data
public class ProductImageService {
    private final ModelMapper modelMapper;
    private final ProductImageRepository productImageRepository;
    private final ProductRepository productRepository;
    private final Path root = Paths.get("uploads");

    public ProductImageService(ModelMapper modelMapper, ProductImageRepository productImageRepository, ProductRepository productRepository){
        this.modelMapper = modelMapper;
        this.productImageRepository = productImageRepository;
        this.productRepository = productRepository;
    }
    public Boolean add (ProductImageReqDto productImageReqDto){
        Random rand = new Random();
        int upperbound = 100;
        int int_random = rand.nextInt(upperbound);
        Optional<Product> pr = productRepository.findById(productImageReqDto.getProductId());
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        if (!pr.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with id" + " " + productImageReqDto.getProductId() + " " + "doesn't exist");
        }
        String ext;
        try {
            String fileName = productImageReqDto.getProductImage().getOriginalFilename();
            ext = fileName.substring(productImageReqDto.getProductImage().getOriginalFilename().lastIndexOf(".") + 1);
            Files.copy(productImageReqDto.getProductImage().getInputStream(), this.root.resolve(String.valueOf(int_random) + "." + ext));
            ProductImages productImages = modelMapper.map(productImageReqDto, ProductImages.class);
            Product product = pr.get();
            productImages.setProduct(product);
            productImages.setProductImage("/uploads/" + String.valueOf(int_random) + "." + ext);
            productImageRepository.save(productImages);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }



        Map response = new HashMap();
        response.put("response", Boolean.TRUE);
        return Boolean.TRUE;
    }
    public List<ProductImageRespDto> getAll(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        ProductImageRespDto productImageRespDto = null;
        List list = new ArrayList();
        for(ProductImages productImages : productImageRepository.findAll(pageable)){
            productImageRespDto = modelMapper.map(productImages,ProductImageRespDto.class);
            list.add(productImageRespDto);
        }
        return list;
    }
}
