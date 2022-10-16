package com.codathon.blue_eMatket_api.web.api;

import com.codathon.blue_eMatket_api.dto.ProductReqDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping("/products")
public interface ProductApi {
//    consumes = "multipart/form-data"
    @RequestMapping(value = "/", method = RequestMethod.POST)
//    public ResponseEntity addProduct(@ModelAttribute ProductReqDto productReqDto );
    public ResponseEntity addProduct(@RequestBody ProductReqDto productReqDto );

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getProducts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size);

}
