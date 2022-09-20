package com.codathon.blue_eMatket_api.dto;

import com.codathon.blue_eMatket_api.model.Auditable;
import lombok.Data;

@Data
public class RoleRespDto extends Auditable<String> {
    private int roleId;
    private String roleName;
    private int status;
}

