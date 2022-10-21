package com.codathon.blue_eMatket_api.web;

import com.codathon.blue_eMatket_api.dto.ProductReqDto;
import com.codathon.blue_eMatket_api.services.ProductService;
import com.codathon.blue_eMatket_api.web.api.ProductApi;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Data
public class ProductController implements ProductApi {
    @Autowired
    private final ProductService productService;

    public ResponseEntity addProduct(ProductReqDto productReqDto) {
        return ResponseEntity.ok().body(productService.add(productReqDto));
    }

    public ResponseEntity getProducts(int page, int size) {
        return ResponseEntity.ok().body(productService.getAll(page, size));
    }

    @Override
    public ResponseEntity getById(String id) {
        return ResponseEntity.ok().body(productService.getProductById(id));

    }

    @Override
    public ResponseEntity getChangeStatu(String id) {
        return ResponseEntity.ok().body(productService.changeStatus(id));

    }

    @Override
    public ResponseEntity getProductsImage(int page, int size) {
        return ResponseEntity.ok().body(productService.getAllByImages(page, size));

    }
}
