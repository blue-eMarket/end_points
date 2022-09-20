package com.codathon.blue_eMatket_api.services;

import com.codathon.blue_eMatket_api.dto.RateReqDto;
import com.codathon.blue_eMatket_api.dto.RateRespDto;
import com.codathon.blue_eMatket_api.model.Rate;
import com.codathon.blue_eMatket_api.repo.RateRepository;
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
public class RateService {
    private final ModelMapper modelMapper;
    private final RateRepository rateRepository;

    public RateService(RateRepository rateRepository, ModelMapper modelMapper){
        this.rateRepository = rateRepository;
        this.modelMapper = modelMapper;
    }
    public ResponseEntity add(RateReqDto rateReqDto){
        Rate rate = modelMapper.map(rateReqDto,Rate.class);
        rateRepository.save(rate);

        Map response=new HashMap();
        response.put("response",Boolean.TRUE);
        return  ResponseEntity.ok().body(response);
    }
    public List<RateRespDto> getAll(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        RateRespDto rateRespDto = null;
        List list = new ArrayList();
        for(Rate rate: rateRepository.findAll(pageable)){
            rateRespDto = modelMapper.map(rate,RateRespDto.class);
            list.add(rateRespDto);
        }
        return list;
    }
    public RateRespDto getById(Integer rateId){
        Optional<Rate> r = rateRepository.findById(rateId);
        if(!r.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.valueOf(rateId));
        }
        RateRespDto rateRespDto = modelMapper.map(r.get(), RateRespDto.class);
        return rateRespDto;
    }
    public ResponseEntity edit(Integer rateId, RateReqDto rateReqDto){
        Optional<Rate> r = rateRepository.findById(rateId);
        if(r.isPresent()){
            Rate rate = modelMapper.map(rateReqDto,Rate.class);
            rate.setRateId(rateId);
            rateRepository.save(rate);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.valueOf(rateId));
        }
        Map response=new HashMap();
        response.put("response",Boolean.TRUE);
        return  ResponseEntity.ok().body(response);

    }
}
