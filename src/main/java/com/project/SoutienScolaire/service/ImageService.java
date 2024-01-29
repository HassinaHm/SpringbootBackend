package com.project.SoutienScolaire.service;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.lang.NonNull;
import org.springframework.core.io.FileSystemResource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageService {

    private static final String UPLOAD_DIR = "C:\\Users\\HP\\Desktop\\Angular\\projet_SoutienScolaire-master\\SoutienScolaire\\src\\main\\resources\\static\\images\\";

    public void saveImage(MultipartFile file, Long testId) throws IOException {
        byte[] bytes = file.getBytes();
        Path path = Paths.get(UPLOAD_DIR + file.getOriginalFilename());
        Files.write(path, bytes);
    }

    public Resource loadImage(@NonNull String imageName) {
        try {
            Path imagePath = Paths.get(UPLOAD_DIR, imageName);
            Resource resource = new FileSystemResource(imagePath.toFile());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Failed to load image: " + imageName);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load image: " + imageName, e);
        }
    }

}
