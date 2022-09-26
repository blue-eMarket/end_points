package com.codathon.blue_eMatket_api.web;

import com.codathon.blue_eMatket_api.dto.PolicyReqDto;
import com.codathon.blue_eMatket_api.services.PolicyService;
import com.codathon.blue_eMatket_api.web.api.PolicyApi;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Data
public class PolicyController implements PolicyApi {
    @Autowired
    private final PolicyService policyService;

    public ResponseEntity addPolicy(PolicyReqDto policyReqDto) {
        return ResponseEntity.ok().body(policyService.add(policyReqDto));
    }

    public ResponseEntity getPolicies(int page, int size) {
        return ResponseEntity.ok().body(policyService.getAll(page, size));
    }

    public ResponseEntity getPolicyById(Integer id) {

        return  ResponseEntity.ok().body(policyService.getById(id));
    }

    public ResponseEntity editPolicy(Integer id, PolicyReqDto policyReqDto) {
        return  ResponseEntity.ok().body(policyService.edit(id, policyReqDto));
    }
}
