package com.codathon.blue_eMatket_api.dto;

import lombok.Data;

@Data
public class FeedbackTypeReqDto {
    private String feedbackTypeName;
    private String description;
    private int status;
}
