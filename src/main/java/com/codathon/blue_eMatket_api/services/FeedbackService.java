package com.codathon.blue_eMatket_api.services;

import com.codathon.blue_eMatket_api.dto.FeedbackReqDto;
import com.codathon.blue_eMatket_api.dto.FeedbackRespDto;
import com.codathon.blue_eMatket_api.model.Feedback;
import com.codathon.blue_eMatket_api.model.FeedbackTypes;
import com.codathon.blue_eMatket_api.repo.FeedbackRepository;
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

@Service
@Data
public class FeedbackService {
    private final ModelMapper modelMapper;
    private final FeedbackRepository feedbackRepository;
    private final FeedbackTypeRepository feedbackTypeRepository;

    public FeedbackService(ModelMapper modelMapper, FeedbackRepository feedbackRepository, FeedbackTypeRepository feedbackTypeRepository){
        this.modelMapper = modelMapper;
        this.feedbackRepository = feedbackRepository;
        this.feedbackTypeRepository = feedbackTypeRepository;
    }
    public ResponseEntity add (FeedbackReqDto feedbackReqDto){
        Optional<FeedbackTypes> ft = feedbackTypeRepository.findById(feedbackReqDto.getFeedbackTypeId());
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        if(!ft.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Feedback type with id"+" "+ feedbackReqDto.getFeedbackTypeId()+" "+"doesn't exist");
        }

        Feedback feedback = modelMapper.map(feedbackReqDto, Feedback.class);
        FeedbackTypes feedbackTypes = ft.get();
        feedback.setFeedbackTypes(feedbackTypes);
        feedbackRepository.save(feedback);

        Map response=new HashMap();
        response.put("response",Boolean.TRUE);
        return  ResponseEntity.ok().body(response);

    }
    public List<FeedbackRespDto> getAll(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        FeedbackRespDto feedbackRespDto = null;
        List list = new ArrayList();
        for(Feedback feedback: feedbackRepository.findAll(pageable)){
            feedbackRespDto = modelMapper.map(feedback,FeedbackRespDto.class);
            list.add(feedbackRespDto);
        }
        return list;
    }
    public FeedbackRespDto getById(Integer feedbackId){
        Optional<Feedback> dt = feedbackRepository.findById(feedbackId);
        if(!dt.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.valueOf(feedbackId));
        }
        FeedbackRespDto feedbackRespDto = modelMapper.map(dt.get(), FeedbackRespDto.class);
        return feedbackRespDto;
    }
    public ResponseEntity edit(Integer feedbackId, FeedbackReqDto feedbackReqDto){
        Optional<Feedback> dt = feedbackRepository.findById(feedbackId);
        if(dt.isPresent()){
            Feedback feedback = modelMapper.map(feedbackReqDto,Feedback.class);
            feedback.setFeedbackId(feedbackId);
            feedbackRepository.save(feedback);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.valueOf(feedbackId));
        }
        Map response=new HashMap();
        response.put("response",Boolean.TRUE);
        return  ResponseEntity.ok().body(response);

    }
}
