package com.codathon.blue_eMatket_api.web;

import com.codathon.blue_eMatket_api.dto.VendorTypeReqDto;
import com.codathon.blue_eMatket_api.services.VendorTypeService;
import com.codathon.blue_eMatket_api.web.api.VendorTypeApi;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Data
public class VendorTypeController implements VendorTypeApi {
    @Autowired
    private final VendorTypeService vendorTypeService;

    public ResponseEntity addVendorType(VendorTypeReqDto vendorTypeReqDto) {
        return ResponseEntity.ok().body(vendorTypeService.add(vendorTypeReqDto));
    }

    public ResponseEntity getVendorTypes(int page, int size) {
        return ResponseEntity.ok().body(vendorTypeService.getAll(page, size));
    }

    public ResponseEntity getVendorTypeById(Integer id) {

        return  ResponseEntity.ok().body(vendorTypeService.getById(id));
    }

    public ResponseEntity editVendorType(Integer id, VendorTypeReqDto vendorTypeReqDto) {
        return  ResponseEntity.ok().body(vendorTypeService.edit(id, vendorTypeReqDto));
    }

}

