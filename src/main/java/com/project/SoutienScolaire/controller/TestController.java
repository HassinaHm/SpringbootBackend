package com.project.SoutienScolaire.controller;

import com.project.SoutienScolaire.modele.Test;
import com.project.SoutienScolaire.service.ImageService;
import com.project.SoutienScolaire.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;

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
    @Autowired
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
    public Test addTest(
            @RequestParam("nom") String nom,
            @RequestParam("prenom") String prenom,
            @RequestParam("email") String email,
            @RequestParam("file") MultipartFile file) throws IOException {

        // Create a new Test object
        Test test = new Test();
        test.setNom(nom);
        test.setPrenom(prenom);
        test.setEmail(email);

        // Save the image
        imageService.saveImage(file, test.getId());

        // Set the image name in the Test object
        test.setImage(file.getOriginalFilename());

        // Save the Test object
        return testService.saveTest(test);
    }

    @PutMapping("/{id}")
    public Test updateTest(@PathVariable Long id,
            @RequestParam("nom") String nom,
            @RequestParam("prenom") String prenom,
            @RequestParam("email") String email,
            @RequestParam("file") MultipartFile file) throws IOException {
        // Retrieve the existing test by ID
        Test existingTest = testService.getTestById(id);
        if (existingTest == null) {
            throw new RuntimeException("Test not found with ID: " + id);
        }

        // Update the test data
        existingTest.setNom(nom);
        existingTest.setPrenom(prenom);
        existingTest.setEmail(email);

        // Save the updated image
        imageService.saveImage(file, id);

        // Update the image name in the Test object
        existingTest.setImage(file.getOriginalFilename());

        // Save and return the updated Test object
        return testService.updateTest(id, existingTest);
    }

    @DeleteMapping("/{id}")
    public void deleteTest(@PathVariable Long id) {
        testService.deleteTest(id);
    }
}
