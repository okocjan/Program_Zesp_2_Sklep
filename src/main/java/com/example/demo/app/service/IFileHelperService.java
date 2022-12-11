package com.example.demo.app.service;

import com.example.demo.app.model.dto.ImgurResponse;
import org.springframework.web.multipart.MultipartFile;

public interface IFileHelperService {

    ImgurResponse uploadFile(MultipartFile fileToUpload, String productName);

    boolean deleteFile(String deleteHash);
}
