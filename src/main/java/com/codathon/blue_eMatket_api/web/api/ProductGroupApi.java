package com.codathon.blue_eMatket_api.web.api;

import com.codathon.blue_eMatket_api.dto.ProductGroupReqDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping("/productGroup")
public interface ProductGroupApi {
    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity addProductGroup(@RequestBody ProductGroupReqDto groupReqDto);

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getProductGroups(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size);

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getProductGroupById(@PathVariable("id") Integer id);

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
    public ResponseEntity editProductGroup(@PathVariable("id") Integer id, @RequestBody ProductGroupReqDto groupReqDto);

}
