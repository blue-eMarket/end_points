package com.codathon.blue_eMatket_api.dto;

import lombok.Data;

@Data
public class VendorTypeRespDto {
    private int vendorTypeId;
    private String vendorTypeName;
    private String description;
    private int status;
}
