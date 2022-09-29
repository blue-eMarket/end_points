package com.codathon.blue_eMatket_api.dto;

import lombok.Data;

@Data
public class VendorReqDto {
    private String email;
    private String phoneNumber;
    private String address;
    private int status;
    private int vendorTypeId;
}
