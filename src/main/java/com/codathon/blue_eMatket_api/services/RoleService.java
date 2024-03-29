package com.codathon.blue_eMatket_api.services;


import com.codathon.blue_eMatket_api.dto.RoleReqDto;
import com.codathon.blue_eMatket_api.dto.RoleRespDto;
import com.codathon.blue_eMatket_api.model.Role;
import com.codathon.blue_eMatket_api.repo.RoleRepository;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
@Data
public class RoleService {
    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;

    public RoleService(ModelMapper modelMapper, RoleRepository roleRepository){
        this.modelMapper = modelMapper;
        this.roleRepository = roleRepository;
    }
    public ResponseEntity add (RoleReqDto roleReqDto){
        Role role = modelMapper.map(roleReqDto, Role.class);
        role.setStatus(1);
        roleRepository.save(role);

        Map response=new HashMap();
        response.put("response",Boolean.TRUE);
        return  ResponseEntity.ok().body(response);
    }
    public List<RoleRespDto> getAll(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        RoleRespDto roleRespDto = null;
        List list = new ArrayList();
        for(Role role: roleRepository.findAll(pageable)){
            roleRespDto = modelMapper.map(role,RoleRespDto.class);
            list.add(roleRespDto);
        }
        return list;
    }
    public RoleRespDto getById(Integer roleId){
        Optional<Role> r = roleRepository.findById(roleId);
        if(!r.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.valueOf(roleId));
        }
        RoleRespDto roleRespDto = modelMapper.map(r.get(), RoleRespDto.class);
        return roleRespDto;
    }

    public ResponseEntity edit(Integer roleId, RoleReqDto roleReqDto){
        Optional<Role> r = roleRepository.findById(roleId);
        if(r.isPresent()){
            Role role = modelMapper.map(roleReqDto,Role.class);
            role.setRoleId(roleId);
            roleRepository.save(role);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.valueOf(roleId));
        }
        Map response=new HashMap();
        response.put("response",Boolean.TRUE);
        return  ResponseEntity.ok().body(response);

    }
}


