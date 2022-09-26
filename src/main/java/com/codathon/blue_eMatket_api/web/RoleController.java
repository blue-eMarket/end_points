package com.codathon.blue_eMatket_api.web;

import com.codathon.blue_eMatket_api.dto.RoleReqDto;
import com.codathon.blue_eMatket_api.services.RoleService;
import com.codathon.blue_eMatket_api.web.api.RoleApi;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Data
public class RoleController implements RoleApi {
    @Autowired
    private final RoleService roleService;

    public ResponseEntity addRole(RoleReqDto roleReqDto) {
        return ResponseEntity.ok().body(roleService.add(roleReqDto));
    }

    public ResponseEntity getRoles(int page, int size) {
        return ResponseEntity.ok().body(roleService.getAll(page, size));
    }

    public ResponseEntity getRoleById(Integer id) {
        return  ResponseEntity.ok().body(roleService.getById(id));
    }

    public ResponseEntity editRole(Integer id, RoleReqDto roleReqDto) {
        return  ResponseEntity.ok().body(roleService.edit(id, roleReqDto));
    }

}
