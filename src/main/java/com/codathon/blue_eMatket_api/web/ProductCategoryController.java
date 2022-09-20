package com.codathon.blue_eMatket_api.web;

import com.codathon.blue_eMatket_api.dto.ProductCategoryReqDto;
import com.codathon.blue_eMatket_api.services.ProductCategoryService;
import com.codathon.blue_eMatket_api.web.api.ProductCategoryApi;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Data
public class ProductCategoryController implements ProductCategoryApi {
    private final ProductCategoryService categoryService;

    public ResponseEntity addProductCategory(ProductCategoryReqDto categoryReqDto){
        return ResponseEntity.ok().body(categoryService.add(categoryReqDto));
    }
    public ResponseEntity getProductCategories(int page, int size){
        return ResponseEntity.ok().body(categoryService.getAll(page,size));
    }
    public ResponseEntity getProductCategoryById(Integer id) {
        return  ResponseEntity.ok().body(categoryService.getById(id));
    }

    public ResponseEntity editProductCategory(Integer id, ProductCategoryReqDto categoryRespDto) {
        return  ResponseEntity.ok().body(categoryService.edit(id, categoryRespDto));
    }
}

