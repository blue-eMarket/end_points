package com.codathon.blue_eMatket_api.dto;

import lombok.Data;

@Data
public class UserReqDto {
    private String userName;
    private int status;
    private String password;
    private int roleId;
}
