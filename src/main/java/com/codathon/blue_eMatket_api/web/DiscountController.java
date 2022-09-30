package com.codathon.blue_eMatket_api.web;

import com.codathon.blue_eMatket_api.dto.DiscountReqDto;
import com.codathon.blue_eMatket_api.services.DiscountService;
import com.codathon.blue_eMatket_api.web.api.DiscountApi;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Data
public class DiscountController implements DiscountApi {
    @Autowired
    private final DiscountService discountService;

    public ResponseEntity addDiscount(DiscountReqDto discountReqDto) {
        return ResponseEntity.ok().body(discountService.add(discountReqDto));
    }

    public ResponseEntity getDiscounts(int page, int size) {
        return ResponseEntity.ok().body(discountService.getAll(page, size));
    }

    public ResponseEntity getDiscountById(Integer id) {
        return  ResponseEntity.ok().body(discountService.getById(id));
    }

    public ResponseEntity editDiscount(Integer id, DiscountReqDto discountReqDto) {
        return  ResponseEntity.ok().body(discountService.edit(id, discountReqDto));
    }

}
