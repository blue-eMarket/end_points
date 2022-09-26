package com.codathon.blue_eMatket_api.dto;

import lombok.Data;

@Data
public class ProductGroupRespDto {
    private int prGroupId;
    private String prGroupName;
    private String prDescription;
    private int status;
}
