package com.codathon.blue_eMatket_api.services;

import com.codathon.blue_eMatket_api.dto.DiscountReqDto;
import com.codathon.blue_eMatket_api.dto.DiscountRespDto;
import com.codathon.blue_eMatket_api.model.Discount;
import com.codathon.blue_eMatket_api.model.DiscountTypes;
import com.codathon.blue_eMatket_api.repo.DiscountRepository;
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
public class DiscountService {
    private final ModelMapper modelMapper;
    private final DiscountRepository discountRepository;
    private final DiscountTypeRepository discountTypeRepository;

    public DiscountService(ModelMapper modelMapper, DiscountRepository discountRepository, DiscountTypeRepository discountTypeRepository){
        this.modelMapper = modelMapper;
        this.discountRepository = discountRepository;
        this.discountTypeRepository = discountTypeRepository;
    }
    public ResponseEntity add (DiscountReqDto discountReqDto){
        Optional<DiscountTypes> dt = discountTypeRepository.findById(discountReqDto.getDiscountTypeId());
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        if(dt.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Discount type with id"+" "+ discountReqDto.getDiscountTypeId()+" "+"doesn't exist");
        }

        Discount discount = modelMapper.map(discountReqDto, Discount.class);
        DiscountTypes discountTypes = dt.get();
        discount.setDiscountTypes(discountTypes);
        discountRepository.save(discount);

        Map response=new HashMap();
        response.put("response",Boolean.TRUE);
        return  ResponseEntity.ok().body(response);

    }
    public List<DiscountRespDto> getAll(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        DiscountRespDto discountRespDto = null;
        List list = new ArrayList();
        for(Discount discount: discountRepository.findAll(pageable)){
            discountRespDto = modelMapper.map(discount,DiscountRespDto.class);
            list.add(discountRespDto);
        }
        return list;
    }
    public DiscountRespDto getById(Integer discountId){
        Optional<Discount> dt = discountRepository.findById(discountId);
        if(!dt.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.valueOf(discountId));
        }
        DiscountRespDto discountRespDto = modelMapper.map(dt.get(), DiscountRespDto.class);
        return discountRespDto;
    }

    public ResponseEntity edit(Integer discountId, DiscountReqDto discountReqDto){
        Optional<Discount> dt = discountRepository.findById(discountId);
        if(dt.isPresent()){
            Discount discount = modelMapper.map(discountReqDto,Discount.class);
            discount.setDiscountId(discountId);
            discountRepository.save(discount);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.valueOf(discountId));
        }
        Map response=new HashMap();
        response.put("response",Boolean.TRUE);
        return  ResponseEntity.ok().body(response);

    }
}
