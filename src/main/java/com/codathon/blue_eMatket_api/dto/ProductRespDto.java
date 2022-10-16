package com.codathon.blue_eMatket_api.dto;

import lombok.Data;

@Data
public class ProductRespDto {
    private String productId;
    private String price;
    private int status;
    private String productName;
    private int productCategoryId;
    private int rateId;
    private String  vendorCode;
    private String productDescription;
    private int discountId;
}
