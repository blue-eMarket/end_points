package com.codathon.blue_eMatket_api.web;

import com.codathon.blue_eMatket_api.dto.DiscountTypeReqDto;
import com.codathon.blue_eMatket_api.services.DiscountTypeService;
import com.codathon.blue_eMatket_api.web.api.DiscountTypeApi;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Data
public class DiscountTypeController implements DiscountTypeApi {
    @Autowired
    private final DiscountTypeService discountTypeService;

    public ResponseEntity addDiscountType(DiscountTypeReqDto discountTypeReqDto) {
        return ResponseEntity.ok().body(discountTypeService.add(discountTypeReqDto));
    }

    public ResponseEntity getDiscountTypes(int page, int size) {
        return ResponseEntity.ok().body(discountTypeService.getAll(page, size));
    }

    public ResponseEntity getDiscountTypeById(Integer id) {
        return  ResponseEntity.ok().body(discountTypeService.getById(id));
    }

    public ResponseEntity editDiscountType(Integer id, DiscountTypeReqDto discountTypeReqDto) {
        return  ResponseEntity.ok().body(discountTypeService.edit(id, discountTypeReqDto));
    }

}

