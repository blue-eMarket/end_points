package com.codathon.blue_eMatket_api.web.api;

import com.codathon.blue_eMatket_api.dto.VendorReqDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping("/vendor")
public interface VendorApi {
    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")

    public ResponseEntity addVendor(@RequestBody VendorReqDto vendorReqDto );

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getVendors(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size);

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getVendorById(@PathVariable("id") String id);

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
    public ResponseEntity editVendor(@PathVariable("id") String id, @RequestBody VendorReqDto vendorReqDto);

}
