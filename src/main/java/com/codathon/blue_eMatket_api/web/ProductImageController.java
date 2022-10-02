package com.codathon.blue_eMatket_api.web;

import com.codathon.blue_eMatket_api.dto.ProductImageReqDto;
import com.codathon.blue_eMatket_api.services.ProductImageService;
import com.codathon.blue_eMatket_api.web.api.ProductImageApi;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Data
public class ProductImageController implements ProductImageApi {
    private final ProductImageService productImageService;

    public ResponseEntity addProductImage(ProductImageReqDto productImageReqDto) {
        return ResponseEntity.ok().body(productImageService.add(productImageReqDto));
    }

    public ResponseEntity getProductImages(int page, int size) {
        return ResponseEntity.ok().body(productImageService.getAll(page, size));
    }
}
