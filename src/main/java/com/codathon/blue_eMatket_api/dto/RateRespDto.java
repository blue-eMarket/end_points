package com.codathon.blue_eMatket_api.dto;

import com.codathon.blue_eMatket_api.model.Auditable;
import lombok.Data;

@Data
public class RateRespDto extends Auditable<String> {
    private int rateId;
    private String rateName;
    private String description;
    private int status;
}
