package com.codathon.blue_eMatket_api.web;

import com.codathon.blue_eMatket_api.dto.LoginDto;
import com.codathon.blue_eMatket_api.dto.UserReqDto;
import com.codathon.blue_eMatket_api.services.UserService;
import com.codathon.blue_eMatket_api.web.api.UserApi;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Data
public class UserController implements UserApi {
    private final UserService userService;

    public ResponseEntity addUser(UserReqDto userReqDto) {
        return ResponseEntity.ok().body(userService.add(userReqDto));
    }

    public ResponseEntity getUsers(int page, int size) {
        return ResponseEntity.ok().body(userService.getAll(page, size));
    }

    public ResponseEntity getUserById(String id) {

        return ResponseEntity.ok().body(userService.getById(id));
    }

    public ResponseEntity editUser(String id, UserReqDto userReqDto) {
        return ResponseEntity.ok().body(userService.edit(id, userReqDto));
    }

    @Override
    public ResponseEntity auth(LoginDto loginDto) {
        return ResponseEntity.ok().body(userService.getLogin(loginDto));

    }
}
