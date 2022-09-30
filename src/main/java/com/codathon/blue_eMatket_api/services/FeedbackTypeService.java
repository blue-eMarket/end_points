package com.codathon.blue_eMatket_api.services;

import com.codathon.blue_eMatket_api.dto.FeedbackTypeReqDto;
import com.codathon.blue_eMatket_api.dto.FeedbackTypeRespDto;
import com.codathon.blue_eMatket_api.model.FeedbackTypes;
import com.codathon.blue_eMatket_api.repo.FeedbackTypeRepository;
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
public class FeedbackTypeService {
    private final ModelMapper modelMapper;
    private final FeedbackTypeRepository feedbackTypeRepository;

    public FeedbackTypeService(ModelMapper modelMapper, FeedbackTypeRepository feedbackTypeRepository){
        this.modelMapper = modelMapper;
        this.feedbackTypeRepository = feedbackTypeRepository;
    }
    public ResponseEntity add (FeedbackTypeReqDto feedbackTypeReqDto){
        FeedbackTypes feedbackTypes = modelMapper.map(feedbackTypeReqDto, FeedbackTypes.class);
        feedbackTypes.setStatus(1);
        feedbackTypeRepository.save(feedbackTypes);

        Map response=new HashMap();
        response.put("response",Boolean.TRUE);
        return  ResponseEntity.ok().body(response);
    }
    public List<FeedbackTypeRespDto> getAll(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        FeedbackTypeRespDto feedbackTypeRespDto = null;
        List list = new ArrayList();
        for(FeedbackTypes feedbackTypes: feedbackTypeRepository.findAll(pageable)){
            feedbackTypeRespDto = modelMapper.map(feedbackTypes,FeedbackTypeRespDto.class);
            list.add(feedbackTypeRespDto);
        }
        return list;
    }
    public FeedbackTypeRespDto getById(Integer feedbackTypeId){
        Optional<FeedbackTypes> ft = feedbackTypeRepository.findById(feedbackTypeId);
        if(!ft.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.valueOf(feedbackTypeId));
        }
        FeedbackTypeRespDto feedbackTypeRespDto = modelMapper.map(ft.get(), FeedbackTypeRespDto.class);
        return feedbackTypeRespDto;
    }
    public ResponseEntity edit(Integer feedbackTypeId, FeedbackTypeReqDto feedbackTypeReqDto){
        Optional<FeedbackTypes> ft = feedbackTypeRepository.findById(feedbackTypeId);
        if(ft.isPresent()){
            FeedbackTypes feedbackTypes = modelMapper.map(feedbackTypeReqDto,FeedbackTypes.class);
            feedbackTypes.setFeedbackTypeId(feedbackTypeId);
            feedbackTypeRepository.save(feedbackTypes);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.valueOf(feedbackTypeId));
        }
        Map response=new HashMap();
        response.put("response",Boolean.TRUE);
        return  ResponseEntity.ok().body(response);

    }
}
