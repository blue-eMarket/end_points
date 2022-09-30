package com.codathon.blue_eMatket_api.web;

import com.codathon.blue_eMatket_api.dto.FeedbackTypeReqDto;
import com.codathon.blue_eMatket_api.services.FeedbackTypeService;
import com.codathon.blue_eMatket_api.web.api.FeedbackTypeApi;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Data
public class FeedbackTypeController implements FeedbackTypeApi {
    @Autowired
    private final FeedbackTypeService feedbackTypeService;

    public ResponseEntity addFeedbackType(FeedbackTypeReqDto feedbackTypeReqDto) {
        return ResponseEntity.ok().body(feedbackTypeService.add(feedbackTypeReqDto));
    }

    public ResponseEntity getFeedbackTypes(int page, int size) {
        return ResponseEntity.ok().body(feedbackTypeService.getAll(page, size));
    }

    public ResponseEntity getFeedbackTypeById(Integer id) {
        return  ResponseEntity.ok().body(feedbackTypeService.getById(id));
    }

    public ResponseEntity editFeedbackType(Integer id, FeedbackTypeReqDto feedbackTypeReqDto) {
        return  ResponseEntity.ok().body(feedbackTypeService.edit(id, feedbackTypeReqDto));
    }

}


