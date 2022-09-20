package com.codathon.blue_eMatket_api.dto;

import com.codathon.blue_eMatket_api.model.Auditable;
import lombok.Data;

@Data
public class PolicyRespDto extends Auditable<String> {
    private int policyId;
    private String policyStatement;
    private int status;
}
