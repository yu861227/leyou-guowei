package com.leyou.upload.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author guo
 */
public interface UploadService {
    String uploadImage(MultipartFile file) throws IOException;
}
