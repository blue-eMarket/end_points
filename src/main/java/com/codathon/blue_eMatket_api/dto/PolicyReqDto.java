package com.codathon.blue_eMatket_api.dto;

import lombok.Data;

@Data
public class PolicyReqDto {
    private String policyStatement;
    private String policyType;
    private int status;
}
