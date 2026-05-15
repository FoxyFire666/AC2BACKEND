package com.example.petcare.controller;

import com.example.petcare.model.MedicalRecord;
import com.example.petcare.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/prontuarios")
@CrossOrigin(origins = "*")
public class MedicalRecordController {

    @Autowired
    private MedicalRecordService medicalRecordService;

    @GetMapping
    public List<MedicalRecord> getAllProntuarios() {
        return medicalRecordService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicalRecord> getProntuarioById(@PathVariable Long id) {
        return medicalRecordService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public MedicalRecord createProntuario(@RequestBody MedicalRecord medicalRecord) {
        return medicalRecordService.save(medicalRecord);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicalRecord> updateProntuario(@PathVariable Long id, @RequestBody MedicalRecord medicalRecord) {
        if (!medicalRecordService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        medicalRecord.setId(id);
        return ResponseEntity.ok(medicalRecordService.save(medicalRecord));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProntuario(@PathVariable Long id) {
        if (!medicalRecordService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        medicalRecordService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}