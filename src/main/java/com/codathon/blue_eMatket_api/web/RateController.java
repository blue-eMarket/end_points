package com.codathon.blue_eMatket_api.web;

import com.codathon.blue_eMatket_api.dto.RateReqDto;
import com.codathon.blue_eMatket_api.services.RateService;
import com.codathon.blue_eMatket_api.web.api.RateApi;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Data
public class RateController implements RateApi {
    @Autowired
    private final RateService rateService;

    public ResponseEntity addRate(RateReqDto rateReqDto){
        return ResponseEntity.ok().body(rateService.add(rateReqDto));
    }
    public ResponseEntity getRates(int page, int size){
        return ResponseEntity.ok().body(rateService.getAll(page,size));
    }
    public ResponseEntity getRateById(Integer id) {
        return  ResponseEntity.ok().body(rateService.getById(id));
    }
    public ResponseEntity editRate(Integer id, RateReqDto rateReqDto) {
        return  ResponseEntity.ok().body(rateService.edit(id, rateReqDto));
    }
}
