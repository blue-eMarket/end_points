package com.codathon.blue_eMatket_api.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProductReqDto {
    private String price;
    private int status;
    private int productCategoryId;
    private int rateId;
    private String productDescription;
    private int discountId;
    private MultipartFile primaryImage;
}
