package com.codathon.blue_eMatket_api.services;

import com.codathon.blue_eMatket_api.dto.UserReqDto;
import com.codathon.blue_eMatket_api.dto.UserRespDto;
import com.codathon.blue_eMatket_api.model.Role;
import com.codathon.blue_eMatket_api.model.Users;
import com.codathon.blue_eMatket_api.repo.RoleRepository;
import com.codathon.blue_eMatket_api.repo.UserRepository;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
@Data
public class UserService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UserService(ModelMapper modelMapper, UserRepository userRepository,RoleRepository roleRepository){
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }
    public ResponseEntity add (UserReqDto userReqDto){
        Optional<Role> r =roleRepository.findById(userReqDto.getRoleId());
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        if(r.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Role with id"+" "+userReqDto.getRoleId()+" "+"doesn't exist");
        }

        Users users = modelMapper.map(userReqDto, Users.class);
        String encPsd = this.passwordEncoder.encode(userReqDto.getPassword());
        Role role = r.get();
        users.setRole(role);
        users.setPassword(encPsd);
        userRepository.save(users);

        Map response=new HashMap();
        response.put("response",Boolean.TRUE);
        return  ResponseEntity.ok().body(response);

    }
    public List<UserRespDto> getAll(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        UserRespDto userRespDto = null;
        List list = new ArrayList();
        for(Users users : userRepository.findAll(pageable)){
            userRespDto = modelMapper.map(users,UserRespDto.class);
            list.add(userRespDto);
        }
        return list;
    }
    public UserRespDto getById(String userId){
        Optional<Users> u = userRepository.findById(userId);
        if(u.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,userId+"  "+"doesn't exist");
        }
        return modelMapper.map(u.get(), UserRespDto.class);
    }
    public ResponseEntity edit(String userId, UserReqDto userReqDto){
        Optional<Users> s = userRepository.findById(userId);
        String encPsd = this.passwordEncoder.encode(userReqDto.getPassword());

        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        if(s.isPresent()){
            Users users = modelMapper.map(userReqDto, Users.class);
            users.setUserId(userId);
            users.setPassword(encPsd);
            userRepository.save(users);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,userId);
        }
        Map response=new HashMap();
        response.put("response",Boolean.TRUE);
        return  ResponseEntity.ok().body(response);

    }
}
