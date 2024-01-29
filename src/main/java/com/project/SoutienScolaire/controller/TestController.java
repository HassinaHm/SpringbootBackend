package com.project.SoutienScolaire.controller;

import com.project.SoutienScolaire.modele.Test;
import com.project.SoutienScolaire.service.ImageService;
import com.project.SoutienScolaire.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/tests")
public class TestController {

    @Autowired
    private TestService testService;

    private ImageService imageService;

    @GetMapping
    public List<Test> getAllTests() {
        return testService.getAllTests();
    }

    @GetMapping("/{id}")
    public Test getTestById(@PathVariable Long id) {
        return testService.getTestById(id);
    }

    @PostMapping
    public Test saveTest(@RequestPart("test") Test test, @RequestPart("file") MultipartFile file) throws IOException {
        // Save the image
        imageService.saveImage(file, test.getId());

        // Set the image name in the Test object
        test.setImage(file.getOriginalFilename());

        // Save the Test object
        return testService.saveTest(test);
    }

    @PutMapping("/{id}")
    public Test updateTest(@PathVariable Long id, @RequestBody Test test) {
        return testService.updateTest(id, test);
    }

    @DeleteMapping("/{id}")
    public void deleteTest(@PathVariable Long id) {
        testService.deleteTest(id);
    }
}
