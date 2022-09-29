package com.codathon.blue_eMatket_api.web.api;
import com.codathon.blue_eMatket_api.dto.VendorTypeReqDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin
@RequestMapping("/vendorTypes")
public interface VendorTypeApi {
    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")

    public ResponseEntity addVendorType(@RequestBody VendorTypeReqDto vendorTypeReqDto );

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getVendorTypes(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size);

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getVendorTypeById(@PathVariable("id") Integer id);

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
    public ResponseEntity editVendorType(@PathVariable("id") Integer id, @RequestBody VendorTypeReqDto vendorTypeReqDto);

}
