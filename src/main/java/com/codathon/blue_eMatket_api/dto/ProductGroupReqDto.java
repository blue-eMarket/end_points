package com.codathon.blue_eMatket_api.dto;

import lombok.Data;

@Data
public class ProductGroupReqDto {
    private String prGroupName;
    private String prDescription;
    private int status;
}
