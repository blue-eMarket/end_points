package com.codathon.blue_eMatket_api.services;

import com.codathon.blue_eMatket_api.dto.ProductCategoryReqDto;
import com.codathon.blue_eMatket_api.dto.ProductCategoryRespDto;
import com.codathon.blue_eMatket_api.model.ProductCategory;
import com.codathon.blue_eMatket_api.repo.ProductCategoryRepository;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
@Data
public class ProductCategoryService {
    private final ModelMapper modelMapper;
    private final ProductCategoryRepository categoryRepository;

    public ProductCategoryService(ModelMapper modelMapper, ProductCategoryRepository categoryRepository){
        this.modelMapper = modelMapper;
        this.categoryRepository = categoryRepository;
    }
    public ResponseEntity add(ProductCategoryReqDto categoryReqDto){
        ProductCategory category = modelMapper.map(categoryReqDto, ProductCategory.class);
        categoryRepository.save(category);

        Map response=new HashMap();
        response.put("response",Boolean.TRUE);
        return  ResponseEntity.ok().body(response);
    }
    public List<ProductCategoryRespDto> getAll(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        ProductCategoryRespDto categoryRespDto = null;
        List list = new ArrayList();
        for(ProductCategory category: categoryRepository.findAll(pageable)){
            categoryRespDto = modelMapper.map(category,ProductCategoryRespDto.class);
            list.add(categoryRespDto);
        }
        return list;
    }
    public ProductCategoryRespDto getById(Integer categoryId){
        Optional<ProductCategory> c = categoryRepository.findById(categoryId);
        if(!c.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.valueOf(categoryId));
        }
        ProductCategoryRespDto categoryRespDto = modelMapper.map(c.get(), ProductCategoryRespDto.class);
        return categoryRespDto;
    }
    public ResponseEntity edit(Integer categoryId, ProductCategoryReqDto categoryReqDto){
        Optional<ProductCategory> c = categoryRepository.findById(categoryId);
        if(c.isPresent()){
            ProductCategory category = modelMapper.map(categoryReqDto,ProductCategory.class);
            category.setPrCategoryId(categoryId);
            categoryRepository.save(category);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.valueOf(categoryId));
        }
        Map response=new HashMap();
        response.put("response",Boolean.TRUE);
        return  ResponseEntity.ok().body(response);

    }
}