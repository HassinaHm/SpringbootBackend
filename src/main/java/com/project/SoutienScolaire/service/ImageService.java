package com.project.SoutienScolaire.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageService {

    private static final String UPLOAD_DIR = "static/images/";

    @Autowired
    private ResourceLoader resourceLoader;

    public void saveImage(MultipartFile file, Long testId) throws IOException {
        byte[] bytes = file.getBytes();
        Path path = Paths.get(UPLOAD_DIR + file.getOriginalFilename());
        Files.write(path, bytes);
    }

    public Resource loadImage(Long testId, String imageName) {
        return resourceLoader.getResource("classpath:" + UPLOAD_DIR + imageName);
    }
}
