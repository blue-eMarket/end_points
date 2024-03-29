package com.codathon.blue_eMatket_api.web.api;

import com.codathon.blue_eMatket_api.dto.PolicyReqDto;
import com.codathon.blue_eMatket_api.dto.RoleReqDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping("/policy")
public interface PolicyApi {
    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")

    public ResponseEntity addPolicy(@RequestBody PolicyReqDto policyReqDto );

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getPolicies(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size);

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getPolicyById(@PathVariable("id") Integer id);

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
    public ResponseEntity editPolicy(@PathVariable("id") Integer id, @RequestBody PolicyReqDto policyReqDto);

}
