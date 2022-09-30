package com.codathon.blue_eMatket_api.web.api;

import com.codathon.blue_eMatket_api.dto.DiscountReqDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping("/discount")
public interface DiscountApi {
    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")

    public ResponseEntity addDiscount(@RequestBody DiscountReqDto discountReqDto );

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getDiscounts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size);

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getDiscountById(@PathVariable("id") Integer id);

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
    public ResponseEntity editDiscount(@PathVariable("id") Integer id, @RequestBody DiscountReqDto discountReqDto);

}
