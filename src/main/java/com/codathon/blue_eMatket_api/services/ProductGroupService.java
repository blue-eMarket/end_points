package com.codathon.blue_eMatket_api.services;

import com.codathon.blue_eMatket_api.dto.ProductGroupReqDto;
import com.codathon.blue_eMatket_api.dto.ProductGroupRespDto;
import com.codathon.blue_eMatket_api.model.ProductGroup;
import com.codathon.blue_eMatket_api.repo.ProductGroupRepository;
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
public class ProductGroupService {
    private final ModelMapper modelMapper;
    private final ProductGroupRepository productGroupRepository;

    private ProductGroupService(ModelMapper modelMapper, ProductGroupRepository productGroupRepository){
        this.modelMapper = modelMapper;
        this.productGroupRepository = productGroupRepository;
    }
    public ResponseEntity add(ProductGroupReqDto groupReqDto){
        ProductGroup group = modelMapper.map(groupReqDto, ProductGroup.class);
        productGroupRepository.save(group);

        Map response=new HashMap();
        response.put("response",Boolean.TRUE);
        return  ResponseEntity.ok().body(response);
    }
    public List<ProductGroupRespDto> getAll(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        ProductGroupRespDto groupRespDto = null;
        List list = new ArrayList();
        for(ProductGroup group: productGroupRepository.findAll(pageable)){
            groupRespDto = modelMapper.map(group,ProductGroupRespDto.class);
            list.add(group);
        }
        return list;
    }
    public ProductGroupRespDto getById(Integer groupId){
        Optional<ProductGroup> pg = productGroupRepository.findById(groupId);
        if(!pg.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.valueOf(groupId));
        }
        ProductGroupRespDto groupRespDto = modelMapper.map(pg.get(), ProductGroupRespDto.class);
        return groupRespDto;
    }
    public ResponseEntity edit(Integer groupId, ProductGroupReqDto groupReqDto){
        Optional<ProductGroup> pg = productGroupRepository.findById(groupId);
        if(pg.isPresent()){
            ProductGroup group = modelMapper.map(groupReqDto,ProductGroup.class);
            group.setPrGroupId(groupId);
            productGroupRepository.save(group);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.valueOf(groupId));
        }
        Map response=new HashMap();
        response.put("response",Boolean.TRUE);
        return  ResponseEntity.ok().body(response);

    }
}
