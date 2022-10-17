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
        Product product = modelMapper.map(productReqDto,Product.class);
        ProductCategory productCategory = new ProductCategory();
        productCategory.setPrCategoryId(productReqDto.getProductCategoryId());
        product.setProductCategory(productCategory);
        Vendor vendor = new Vendor();
        vendor.setVendorId(productReqDto.getVendorId());
        product.setVendor(vendor);

        productRepository.save(product);
//        Map response=new HashMap();
//        response.put("response",Boolean.TRUE);
        return  ResponseEntity.ok().body(product.getProductCode());
    }
    public List<ProductRespDto> getAll(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        ProductRespDto productRespDto = null;
        List list = new ArrayList();
        for(Product station: productRepository.findAll(pageable)){
            productRespDto= modelMapper.map(station,ProductRespDto.class);
            productRespDto.setVendorCode(station.getVendor().getVendorId());
            list.add(productRespDto);
        }
        return list;
    }

    public List<ProductRespDto> getAllByImages(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        ProductResImageDto productRespDto = null;
        ProductImageRespDto productImageRespDto=null;
        List list = new ArrayList();

        for(Product station: productRepository.findAll(pageable)){
            productRespDto= modelMapper.map(station,ProductResImageDto.class);
            productRespDto.setVendorCode(station.getVendor().getVendorId());
            List<ProductImageRespDto> list1=new ArrayList<>();
            for (ProductImages productImages:productImageRepository.findByProduct(station.getProductCode())){
                productImageRespDto = modelMapper.map(productImages,ProductImageRespDto.class);
                list1.add(productImageRespDto);
            }
            productRespDto.setProductImageRespDto(list1);
            list.add(productRespDto);
        }
        return list;
    }

}
