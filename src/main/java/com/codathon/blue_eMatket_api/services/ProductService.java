package com.codathon.blue_eMatket_api.services;

import com.codathon.blue_eMatket_api.repo.DiscountRepository;
import com.codathon.blue_eMatket_api.repo.ProductCategoryRepository;
import com.codathon.blue_eMatket_api.repo.ProductGroupRepository;
import com.codathon.blue_eMatket_api.repo.ProductRepository;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Data
public class ProductService {
    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;
    private final ProductGroupRepository productGroupRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final DiscountRepository discountRepository;

    public ProductService(ModelMapper modelMapper, ProductRepository productRepository, ProductCategoryRepository productCategoryRepository, ProductGroupRepository productGroupRepository, DiscountRepository discountRepository){
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
        this.productCategoryRepository = productCategoryRepository;
        this.productGroupRepository = productGroupRepository;
        this.discountRepository = discountRepository;
    }

}
