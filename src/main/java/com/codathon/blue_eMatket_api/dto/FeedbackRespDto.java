package com.codathon.blue_eMatket_api.dto;

import lombok.Data;

@Data
public class FeedbackRespDto {
    private int feedbackId;
    private String feedbackStatement;
    private String sentBy;
    private int status;
}
