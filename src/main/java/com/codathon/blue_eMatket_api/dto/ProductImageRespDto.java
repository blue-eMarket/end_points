package com.codathon.blue_eMatket_api.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProductImageRespDto {
    private int prImageId;
    private String productId;
    private int isPrimary;
    private String productImage;
}
