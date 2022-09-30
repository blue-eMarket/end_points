package com.codathon.blue_eMatket_api.services;

import com.codathon.blue_eMatket_api.dto.DiscountTypeReqDto;
import com.codathon.blue_eMatket_api.dto.DiscountTypeRespDto;
import com.codathon.blue_eMatket_api.model.DiscountTypes;
import com.codathon.blue_eMatket_api.repo.DiscountTypeRepository;
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
public class DiscountTypeService {
    private final ModelMapper modelMapper;
    private final DiscountTypeRepository discountTypeRepository;

    public DiscountTypeService(ModelMapper modelMapper, DiscountTypeRepository discountTypeRepository){
        this.modelMapper = modelMapper;
        this.discountTypeRepository = discountTypeRepository;
    }
    public ResponseEntity add (DiscountTypeReqDto discountTypeReqDto){
        DiscountTypes discountTypes = modelMapper.map(discountTypeReqDto, DiscountTypes.class);
        discountTypes.setStatus(1);
        discountTypeRepository.save(discountTypes);

        Map response=new HashMap();
        response.put("response",Boolean.TRUE);
        return  ResponseEntity.ok().body(response);
    }
    public List<DiscountTypeRespDto> getAll(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        DiscountTypeRespDto discountTypeRespDto = null;
        List list = new ArrayList();
        for(DiscountTypes discountTypes: discountTypeRepository.findAll(pageable)){
            discountTypeRespDto = modelMapper.map(discountTypes,DiscountTypeRespDto.class);
            list.add(discountTypeRespDto);
        }
        return list;
    }
    public DiscountTypeRespDto getById(Integer discountTypeId){
        Optional<DiscountTypes> dt = discountTypeRepository.findById(discountTypeId);
        if(!dt.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.valueOf(discountTypeId));
        }
        DiscountTypeRespDto discountTypeRespDto = modelMapper.map(dt.get(), DiscountTypeRespDto.class);
        return discountTypeRespDto;
    }

    public ResponseEntity edit(Integer discountTypeId, DiscountTypeReqDto discountTypeReqDto){
        Optional<DiscountTypes> dt = discountTypeRepository.findById(discountTypeId);
        if(dt.isPresent()){
            DiscountTypes discountTypes = modelMapper.map(discountTypeReqDto,DiscountTypes.class);
            discountTypes.setDiscountTypeId(discountTypeId);
            discountTypeRepository.save(discountTypes);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.valueOf(discountTypeId));
        }
        Map response=new HashMap();
        response.put("response",Boolean.TRUE);
        return  ResponseEntity.ok().body(response);

    }
}
