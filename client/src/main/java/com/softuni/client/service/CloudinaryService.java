package com.softuni.client.service;

import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryService {

    String uploadPhoto(MultipartFile photo,String folderName);

    void deletePhoto(String publicId);
}
