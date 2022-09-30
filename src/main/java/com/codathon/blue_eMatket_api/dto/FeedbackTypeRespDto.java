package com.codathon.blue_eMatket_api.dto;

import lombok.Data;

@Data
public class FeedbackTypeRespDto {
    private int feedbackTypeId;
    private String feedbackTypeName;
    private String description;
    private int status;
}
