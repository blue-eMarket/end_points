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

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
@Data
public class UserService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private final Path root = Paths.get("uploads");

    public UserService(ModelMapper modelMapper, UserRepository userRepository,RoleRepository roleRepository){
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }
    public Boolean add (UserReqDto userReqDto) {
        Random rand = new Random();
        int upperbound = 100;
        int int_random = rand.nextInt(upperbound);
        Optional<Role> r = roleRepository.findById(userReqDto.getRoleId());
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        if (!r.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Role with id" + " " + userReqDto.getRoleId() + " " + "doesn't exist");
        }
        String ext;
        try {
            String fileName = userReqDto.getProfile().getOriginalFilename();
            ext = fileName.substring(userReqDto.getProfile().getOriginalFilename().lastIndexOf(".") + 1);
            Files.copy(userReqDto.getProfile().getInputStream(), this.root.resolve(String.valueOf(int_random) + "." + ext));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Users users = modelMapper.map(userReqDto, Users.class);
        String encPsd = this.passwordEncoder.encode(userReqDto.getPassword());
        Role role = r.get();
        users.setRole(role);
        users.setPassword(encPsd);
        users.setProfile("/uploads/" + String.valueOf(int_random) + "." + ext);
        userRepository.save(users);

        Map response = new HashMap();
        response.put("response", Boolean.TRUE);
        return Boolean.TRUE;

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
        if(!u.isPresent()){
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
