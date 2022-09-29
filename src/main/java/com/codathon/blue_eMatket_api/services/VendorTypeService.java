package com.codathon.blue_eMatket_api.services;
import com.codathon.blue_eMatket_api.dto.VendorTypeReqDto;
import com.codathon.blue_eMatket_api.dto.VendorTypeRespDto;
import com.codathon.blue_eMatket_api.model.VendorType;
import com.codathon.blue_eMatket_api.repo.VendorTypeRepository;
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
public class VendorTypeService {
    private final ModelMapper modelMapper;
    private final VendorTypeRepository vendorTypeRepository;

    public VendorTypeService(ModelMapper modelMapper, VendorTypeRepository vendorTypeRepository){
        this.modelMapper = modelMapper;
        this.vendorTypeRepository = vendorTypeRepository;
    }
    public ResponseEntity add (VendorTypeReqDto vendorTypeReqDto){
        VendorType vendorType = modelMapper.map(vendorTypeReqDto, VendorType.class);
        vendorType.setStatus(1);
        vendorTypeRepository.save(vendorType);

        Map response=new HashMap();
        response.put("response",Boolean.TRUE);
        return  ResponseEntity.ok().body(response);
    }

    public List<VendorTypeRespDto> getAll(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        VendorTypeRespDto vendorTypeRespDto = null;
        List list = new ArrayList();
        for(VendorType vendorType: vendorTypeRepository.findAll(pageable)){
            vendorTypeRespDto = modelMapper.map(vendorType,VendorTypeRespDto.class);
            list.add(vendorTypeRespDto);
        }
        return list;
    }
    public VendorTypeRespDto getById(Integer vendorTypeId){
        Optional<VendorType> vt = vendorTypeRepository.findById(vendorTypeId);
        if(!vt.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.valueOf(vendorTypeId));
        }
        VendorTypeRespDto vendorTypeRespDto = modelMapper.map(vt.get(), VendorTypeRespDto.class);
        return vendorTypeRespDto;
    }

    public ResponseEntity edit(Integer vendorTypeId, VendorTypeReqDto vendorTypeReqDto){
        Optional<VendorType> vt = vendorTypeRepository.findById(vendorTypeId);
        if(vt.isPresent()){
            VendorType vendorType = modelMapper.map(vendorTypeReqDto,VendorType.class);
            vendorType.setVendorTypeId(vendorTypeId);
            vendorTypeRepository.save(vendorType);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.valueOf(vendorTypeId));
        }
        Map response=new HashMap();
        response.put("response",Boolean.TRUE);
        return  ResponseEntity.ok().body(response);

    }

}
