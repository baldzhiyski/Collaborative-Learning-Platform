package com.softuni.client.service;

import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryService {

    String uploadFile(MultipartFile photo, String folderName);

    void deleteFile(String publicId);
}
