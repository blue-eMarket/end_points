package com.codathon.blue_eMatket_api.web.api;

import com.codathon.blue_eMatket_api.dto.ProductReqDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping("/products")
public interface ProductApi {
    @RequestMapping(value = "/", method = RequestMethod.POST,  consumes = "multipart/form-data")
    public ResponseEntity addProduct(@ModelAttribute ProductReqDto productReqDto );

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getProducts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size);

}
