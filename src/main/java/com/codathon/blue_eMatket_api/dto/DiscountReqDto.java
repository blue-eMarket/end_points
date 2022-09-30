package com.codathon.blue_eMatket_api.dto;

import lombok.Data;

@Data
public class DiscountReqDto {
    private int discountId;
    private String amount;
    private String fromDate;
    private String toDate;
    private int status;
    private int discountTypeId;
}
