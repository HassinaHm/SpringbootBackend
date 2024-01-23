package com.project.SoutienScolaire.service;

import com.project.SoutienScolaire.modele.Test;
import com.project.SoutienScolaire.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {
    @Autowired
    private TestRepository testRepository;

    public List<Test> getAllTests() {
        return testRepository.findAll();
    }

    public Test getTestById(Long id) {
        return testRepository.findById(id).orElse(null);
    }

    public Test saveTest(Test test) {
        return testRepository.save(test);
    }

    public Test updateTest(Long id, Test test) {
        Test existingTest = testRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Test non trouvé avec l'ID : " + id));

        existingTest.setNom(test.getNom());
        existingTest.setPrenom(test.getPrenom());
        existingTest.setEmail(test.getEmail());
        existingTest.setImage(test.getImage());

        return testRepository.save(existingTest);
    }

    public void deleteTest(Long id) {
        testRepository.deleteById(id);
    }
}
