package com.leyou.upload.controller;

import com.leyou.upload.service.UploadService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author guo
 */
@Controller
@RequestMapping("upload")
public class UploadController {

    @Autowired
    private UploadService uploadService;


    @PostMapping("image")
    public ResponseEntity<String> uploadImage(@RequestParam("file")MultipartFile file) throws IOException {
         String url=this.uploadService.uploadImage(file);
         //非空判断
        if(StringUtils.isBlank(url)){
        //文件不合法 参数不合法400
         return ResponseEntity.badRequest().build();

        }
        return ResponseEntity.status(HttpStatus.CREATED).body(url);
    }






}
