package com.codathon.blue_eMatket_api.web;

import com.codathon.blue_eMatket_api.dto.VendorReqDto;
import com.codathon.blue_eMatket_api.services.VendorService;
import com.codathon.blue_eMatket_api.web.api.VendorApi;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Data
public class VendorController implements VendorApi {
        @Autowired
        private final VendorService vendorService;

        public ResponseEntity addVendor(VendorReqDto vendorReqDto) {
            return ResponseEntity.ok().body(vendorService.add(vendorReqDto));
        }

        public ResponseEntity getVendors(int page, int size) {
            return ResponseEntity.ok().body(vendorService.getAll(page, size));
        }

        public ResponseEntity getVendorById(String id) {

            return  ResponseEntity.ok().body(vendorService.getById(id));
        }

        public ResponseEntity editVendor(String id, VendorReqDto vendorReqDto) {
            return  ResponseEntity.ok().body(vendorService.edit(id, vendorReqDto));
        }
}
