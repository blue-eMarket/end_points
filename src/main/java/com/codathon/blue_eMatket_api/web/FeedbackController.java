package com.codathon.blue_eMatket_api.web;

import com.codathon.blue_eMatket_api.dto.FeedbackReqDto;
import com.codathon.blue_eMatket_api.services.FeedbackService;
import com.codathon.blue_eMatket_api.web.api.FeedbackApi;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Data
public class FeedbackController implements FeedbackApi {
    @Autowired
    private final FeedbackService feedbackService;

    public ResponseEntity addFeedback(FeedbackReqDto feedbackReqDto) {
        return ResponseEntity.ok().body(feedbackService.add(feedbackReqDto));
    }

    public ResponseEntity getFeedback(int page, int size) {
        return ResponseEntity.ok().body(feedbackService.getAll(page, size));
    }

    public ResponseEntity getFeedbackById(Integer id) {
        return  ResponseEntity.ok().body(feedbackService.getById(id));
    }

    public ResponseEntity editFeedback(Integer id, FeedbackReqDto feedbackReqDto) {
        return  ResponseEntity.ok().body(feedbackService.edit(id, feedbackReqDto));
    }

}

