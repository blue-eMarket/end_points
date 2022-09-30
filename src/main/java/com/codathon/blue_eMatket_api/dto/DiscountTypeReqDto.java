package com.codathon.blue_eMatket_api.dto;

import lombok.Data;

@Data
public class DiscountTypeReqDto {
    private String discountTypeName;
    private String description;
    private int status;
}
