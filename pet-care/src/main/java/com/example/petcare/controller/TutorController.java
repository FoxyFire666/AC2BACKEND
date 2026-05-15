package com.example.petcare.controller;

import com.example.petcare.model.Tutor;
import com.example.petcare.service.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tutores")
@CrossOrigin(origins = "*")
public class TutorController {

    @Autowired
    private TutorService tutorService;

    @GetMapping
    public List<Tutor> getAllTutores() {
        return tutorService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tutor> getTutorById(@PathVariable Long id) {
        return tutorService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Tutor createTutor(@RequestBody Tutor tutor) {
        return tutorService.save(tutor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tutor> updateTutor(@PathVariable Long id, @RequestBody Tutor tutor) {
        if (!tutorService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        tutor.setId(id);
        return ResponseEntity.ok(tutorService.save(tutor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTutor(@PathVariable Long id) {
        if (!tutorService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        tutorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}