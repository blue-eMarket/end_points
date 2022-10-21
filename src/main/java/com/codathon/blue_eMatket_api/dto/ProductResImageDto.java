package com.codathon.blue_eMatket_api.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductResImageDto {
    private String productCode;
    private String price;
    private int status;
    private int productCategoryId;
    private int rateId;
    private String productName;
    private String  vendorCode;
    private String productDescription;
    private int discountId;
    private List<ProductImageRespDto> productImageRespDto;
}
