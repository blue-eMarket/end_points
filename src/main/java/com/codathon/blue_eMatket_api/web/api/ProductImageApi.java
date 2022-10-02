package com.codathon.blue_eMatket_api.web.api;

import com.codathon.blue_eMatket_api.dto.ProductImageReqDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping("/productImage")
public interface ProductImageApi {
    @RequestMapping(value = "/", method = RequestMethod.POST,  consumes = "multipart/form-data")
    public ResponseEntity addProductImage(@ModelAttribute ProductImageReqDto productImageReqDto );

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getProductImages(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size);

}
