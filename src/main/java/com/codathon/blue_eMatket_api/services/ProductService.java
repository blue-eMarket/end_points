package com.codathon.blue_eMatket_api.services;

import com.codathon.blue_eMatket_api.dto.*;
import com.codathon.blue_eMatket_api.model.*;
import com.codathon.blue_eMatket_api.repo.*;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
@Data
public class ProductService {
    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;
    private final ProductGroupRepository productGroupRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final ProductImageRepository productImageRepository;
    private final DiscountRepository discountRepository;
    private final Path root = Paths.get("uploads");

    public ProductService(ModelMapper modelMapper, ProductRepository productRepository, ProductCategoryRepository productCategoryRepository, ProductGroupRepository productGroupRepository, DiscountRepository discountRepository, ProductImageRepository productImageRepository){
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
        this.productCategoryRepository = productCategoryRepository;
        this.productGroupRepository = productGroupRepository;
        this.discountRepository = discountRepository;
        this.productImageRepository = productImageRepository;
    }
    public ResponseEntity add (ProductReqDto productReqDto){
        Random rand = new Random();
        int upperbound = 100;
        int int_random = rand.nextInt(upperbound);
        Optional<ProductCategory> pc = productCategoryRepository.findById(productReqDto.getProductCategoryId());
        if(pc.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Product Category with id"+" "+productReqDto.getProductCategoryId()+" "+"doesn't exist");
        }
        String ext;
//        try {
//            String fileName = productReqDto.getPrimaryImage().getOriginalFilename();
//            ext = fileName.substring(productReqDto.getPrimaryImage().getOriginalFilename().lastIndexOf(".") + 1);
//            Files.copy(productReqDto.getPrimaryImage().getInputStream(), this.root.resolve(String.valueOf(int_random) + "." + ext));
//
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//        Product product = modelMapper.map(productReqDto, Product.class);
//        ProductCategory productCategory = pc.get();
//        product.setProductCategory(productCategory);
//        product.setStatus(1);
//
//
//        productRepository.save(product);
//        ProductImages productImages = new ProductImages();
//
//        productImages.setProductImage("/uploads/" + String.valueOf(int_random) + "." + ext);
//        productImages.setProduct(product);

        Map response=new HashMap();
        response.put("response",Boolean.TRUE);
        return  ResponseEntity.ok().body(response);
    }
    public List<ProductRespDto> getAll(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        ProductRespDto productRespDto = null;
        List list = new ArrayList();
        for(Product station: productRepository.findAll(pageable)){
            productRespDto= modelMapper.map(station,ProductRespDto.class);
            list.add(productRespDto);
        }
        return list;
    }

}
