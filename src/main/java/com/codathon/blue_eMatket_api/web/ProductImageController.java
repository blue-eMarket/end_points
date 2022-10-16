package com.codathon.blue_eMatket_api.web;

import com.codathon.blue_eMatket_api.config.FilesStorageService;
import com.codathon.blue_eMatket_api.dto.ProductImageReqDto;
import com.codathon.blue_eMatket_api.services.ProductImageService;
import com.codathon.blue_eMatket_api.web.api.ProductImageApi;
import lombok.Data;
import org.apache.tomcat.jni.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@Data
public class ProductImageController implements ProductImageApi {
    private final ProductImageService productImageService;
@Autowired
    FilesStorageService storageService;
    public ResponseEntity addProductImage(ProductImageReqDto productImageReqDto) {
        return ResponseEntity.ok().body(productImageService.add(productImageReqDto));
    }

    public ResponseEntity getProductImages(int page, int size) {
        return ResponseEntity.ok().body(productImageService.getAll(page, size));
    }
    @GetMapping("/files")
    public ResponseEntity<List> getListFiles() {
        List fileInfos = storageService.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder
                    .fromMethodName(ProductImageController.class, "getFile", path.getFileName().toString()).build().toString();
            Map<String,Object> response =new HashMap<>();
            response.put("filename",filename);
            response.put("url",url);
            return response;
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = storageService.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

}
