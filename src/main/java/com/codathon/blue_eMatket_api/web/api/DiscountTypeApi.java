package com.codathon.blue_eMatket_api.web.api;

import com.codathon.blue_eMatket_api.dto.DiscountTypeReqDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping("/discountTypes")
public interface DiscountTypeApi {
    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")

    public ResponseEntity addDiscountType(@RequestBody DiscountTypeReqDto discountTypeReqDto );

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getDiscountTypes(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size);

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getDiscountTypeById(@PathVariable("id") Integer id);

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
    public ResponseEntity editDiscountType(@PathVariable("id") Integer id, @RequestBody DiscountTypeReqDto discountTypeReqDto);

}


