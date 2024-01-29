package com.project.SoutienScolaire.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.core.io.Resource;
import com.project.SoutienScolaire.service.ImageService;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@RestController
@RequestMapping("/api/images")
@CrossOrigin(origins = "http://localhost:4200")
public class ImageController {
    @Autowired
    private ImageService imageService;

    private static boolean hasWritePermission(String directoryPath) {
        try {
            Path testFilePath = Paths.get(directoryPath, "test.txt");
            Files.createFile(testFilePath);
            Files.deleteIfExists(testFilePath);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {

        try {

            String uploadPath = "C:\\Users\\HP\\Desktop\\Angular\\projet_SoutienScolaire-master\\SoutienScolaire\\src\\main\\resources\\static\\images\\";
            String fileName = file.getOriginalFilename();
            Path filePath = Paths.get(uploadPath + fileName);
            if (!hasWritePermission(uploadPath)) {
                throw new RuntimeException("Server does not have write permission to directory: " + uploadPath);
            }
            // Save the file to the specified directory
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // Return the URL to access the uploaded image
            String imageUrl = "/images/" + fileName;
            System.out.println("Upload Path: " + uploadPath);
            System.out.println("File Name: " + fileName);
            System.out.println("File Path: " + filePath);
            return ResponseEntity.ok().body(imageUrl);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to upload image");
        }

    }

    @GetMapping("/{imageName}")
    public ResponseEntity<Resource> getImage(@PathVariable String imageName) {
        Resource image = imageService.loadImage(imageName);

        try {
            // Get the content type based on the file extension
            String contentType = Files.probeContentType(Paths.get(image.getFile().getAbsolutePath()));

            // Set the content type in the response headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType(contentType));

            // Return the response entity with the image and headers
            return ResponseEntity.ok().headers(headers).body(image);
        } catch (IOException e) {
            throw new RuntimeException("Failed to determine content type for image: " + imageName, e);
        }
    }

}