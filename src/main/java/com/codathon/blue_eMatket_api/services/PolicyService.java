package com.codathon.blue_eMatket_api.services;

import com.codathon.blue_eMatket_api.dto.PolicyReqDto;
import com.codathon.blue_eMatket_api.dto.PolicyRespDto;
import com.codathon.blue_eMatket_api.model.Policy;
import com.codathon.blue_eMatket_api.repo.PolicyRepository;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Data
@Service
public class PolicyService {
    private final ModelMapper modelMapper;
    private final PolicyRepository policyRepository;

    public PolicyService(ModelMapper modelMapper, PolicyRepository policyRepository){
        this.modelMapper = modelMapper;
        this.policyRepository = policyRepository;
    }
    public ResponseEntity add (PolicyReqDto policyReqDto){
        Policy policy = modelMapper.map(policyReqDto, Policy.class);
        policy.setStatus(1);
        policyRepository.save(policy);

        Map response=new HashMap();
        response.put("response",Boolean.TRUE);
        return  ResponseEntity.ok().body(response);
    }
    public List<PolicyRespDto> getAll(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        PolicyRespDto policyRespDto = null;
        List list = new ArrayList();
        for(Policy policy: policyRepository.findAll(pageable)){
            policyRespDto = modelMapper.map(policy,PolicyRespDto.class);
            list.add(policyRespDto);
        }
        return list;
    }
    public PolicyRespDto getById(Integer policyId){
        Optional<Policy> p = policyRepository.findById(policyId);
        if(!p.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.valueOf(policyId));
        }
        PolicyRespDto policyRespDto = modelMapper.map(p.get(), PolicyRespDto.class);
        return policyRespDto;
    }

    public ResponseEntity edit(Integer policyId, PolicyReqDto policyReqDto){
        Optional<Policy> p = policyRepository.findById(policyId);
        if(p.isPresent()){
            Policy policy = modelMapper.map(policyReqDto,Policy.class);
            policy.setPolicyId(policyId);
            policyRepository.save(policy);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.valueOf(policyId));
        }
        Map response=new HashMap();
        response.put("response",Boolean.TRUE);
        return  ResponseEntity.ok().body(response);

    }
}
