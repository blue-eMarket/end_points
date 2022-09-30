package com.codathon.blue_eMatket_api.dto;

import lombok.Data;

@Data
public class FeedbackReqDto {
    private String feedbackStatement;
    private String sentBy;
    private int status;
    private int feedbackTypeId;
}
