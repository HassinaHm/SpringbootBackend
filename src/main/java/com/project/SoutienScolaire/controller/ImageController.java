package com.project.SoutienScolaire.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@RestController
@RequestMapping("/api/images")
@CrossOrigin(origins = "http://localhost:4200")
public class ImageController {

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

            String uploadPath = "/static/images/";
            String fileName = file.getOriginalFilename();
            Path filePath = Paths.get(uploadPath + fileName);
            if (!hasWritePermission(uploadPath)) {
                throw new RuntimeException("Server does not have write permission to directory: " + uploadPath);
            }
            // Save the file to the specified directory
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // Return the URL to access the uploaded image
            String imageUrl = "/images/" + fileName;
            return ResponseEntity.ok().body(imageUrl);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to upload image");
        }
    }
}
