package com.codathon.blue_eMatket_api.web;

import com.codathon.blue_eMatket_api.dto.ProductGroupReqDto;
import com.codathon.blue_eMatket_api.services.ProductGroupService;
import com.codathon.blue_eMatket_api.web.api.ProductGroupApi;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Data
@RestController
public class ProductGroupController implements ProductGroupApi {
    @Autowired
    private final ProductGroupService productGroupService;

    public ResponseEntity addProductGroup(ProductGroupReqDto groupReqDto) {
        return ResponseEntity.ok().body(productGroupService.add(groupReqDto));
    }

    public ResponseEntity getProductGroups(int page, int size) {
        return ResponseEntity.ok().body(productGroupService.getAll(page, size));
    }

    public ResponseEntity getProductGroupById(Integer id) {

        return  ResponseEntity.ok().body(productGroupService.getById(id));
    }

    public ResponseEntity editProductGroup(Integer id, ProductGroupReqDto groupReqDto) {
        return  ResponseEntity.ok().body(productGroupService.edit(id, groupReqDto));
    }
}
