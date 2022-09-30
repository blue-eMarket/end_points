package com.codathon.blue_eMatket_api.web.api;

import com.codathon.blue_eMatket_api.dto.FeedbackTypeReqDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping("/feedbackTypes")
public interface FeedbackTypeApi {
    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")

    public ResponseEntity addFeedbackType(@RequestBody FeedbackTypeReqDto feedbackTypeReqDto );

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getFeedbackTypes(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size);

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getFeedbackTypeById(@PathVariable("id") Integer id);

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
    public ResponseEntity editFeedbackType(@PathVariable("id") Integer id, @RequestBody FeedbackTypeReqDto feedbackTypeReqDto);

}
