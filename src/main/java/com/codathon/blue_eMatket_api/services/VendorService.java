package com.codathon.blue_eMatket_api.services;

import com.codathon.blue_eMatket_api.dto.VendorReqDto;
import com.codathon.blue_eMatket_api.dto.VendorRespDto;
import com.codathon.blue_eMatket_api.model.Vendor;
import com.codathon.blue_eMatket_api.model.VendorType;
import com.codathon.blue_eMatket_api.repo.VendorRepository;
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
public class VendorService {
    private final ModelMapper modelMapper;
    private final VendorRepository vendorRepository;
    private final VendorTypeRepository vendorTypeRepository;

    public VendorService(ModelMapper modelMapper, VendorRepository vendorRepository, VendorTypeRepository vendorTypeRepository){
        this.modelMapper = modelMapper;
        this.vendorRepository = vendorRepository;
        this.vendorTypeRepository = vendorTypeRepository;
    }

    public ResponseEntity add (VendorReqDto vendorReqDto){
        Vendor vendor = modelMapper.map(vendorReqDto, Vendor.class);
        Optional<VendorType> vt = vendorTypeRepository.findById(vendorReqDto.getVendorTypeId());
        if(vt.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Vendor type with id"+" "+vendorReqDto.getVendorTypeId()+" "+"doesn't exist");
        }
        VendorType vendorType = vt.get();
        vendor.setVendorType(vendorType);
        vendor.setStatus(1);
        vendorRepository.save(vendor);

        Map response=new HashMap();
        response.put("response",Boolean.TRUE);
        return  ResponseEntity.ok().body(response);
    }
    public List<VendorRespDto> getAll(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        VendorRespDto vendorRespDto = null;
        List list = new ArrayList();
        for(Vendor vendor: vendorRepository.findAll(pageable)){
            vendorRespDto = modelMapper.map(vendor,VendorRespDto.class);
            list.add(vendorRespDto);
        }
        return list;
    }

    public VendorRespDto getById(String vendorId){
        Optional<Vendor> v = vendorRepository.findById(vendorId);
        if(!v.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,vendorId);
        }
        VendorRespDto vendorRespDto = modelMapper.map(v.get(), VendorRespDto.class);
        return vendorRespDto;
    }

    public ResponseEntity edit(String vendorId, VendorReqDto vendorReqDto){
        Optional<Vendor> v = vendorRepository.findById(vendorId);
        if(v.isPresent()){
            Vendor vendor = modelMapper.map(vendorReqDto,Vendor.class);
            vendor.setVendorId(vendorId);
            vendorRepository.save(vendor);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,vendorId);
        }
        Map response=new HashMap();
        response.put("response",Boolean.TRUE);
        return  ResponseEntity.ok().body(response);

    }
}
