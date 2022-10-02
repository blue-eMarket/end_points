package com.codathon.blue_eMatket_api.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UserReqDto {
    private String userName;
    private int status;
    private String password;
    private int roleId;
    private MultipartFile profile;
}
