package com.codathon.blue_eMatket_api.dto;

import lombok.Data;

@Data
public class RateReqDto {
    private String rateName;
    private String description;
    private int status;
}
