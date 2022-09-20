package com.codathon.blue_eMatket_api.dto;

import lombok.Data;

@Data
public class ProductCategoryRespDto {
    private int prCategoryId;
    private String categoryName;
    private String description;
    private int status;
}
