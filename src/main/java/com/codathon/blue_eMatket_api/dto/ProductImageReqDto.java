package com.codathon.blue_eMatket_api.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProductImageReqDto {
    private String productId;
    private MultipartFile productImage;
}
