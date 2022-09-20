package com.codathon.blue_eMatket_api.web.api;

import com.codathon.blue_eMatket_api.dto.ProductCategoryReqDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin
@RequestMapping("/productCategory")
public interface ProductCategoryApi {
    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity addProductCategory(@RequestBody ProductCategoryReqDto categoryReqDto);

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getProductCategories(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size);

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getProductCategoryById(@PathVariable("id") Integer id);

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
    public ResponseEntity editProductCategory(@PathVariable("id") Integer id, @RequestBody ProductCategoryReqDto categoryReqDto);

}
