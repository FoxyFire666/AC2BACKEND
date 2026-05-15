package com.example.petcare.controller;

import com.example.petcare.model.Vaccination;
import com.example.petcare.service.VaccinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/vacinacoes")
@CrossOrigin(origins = "*")
public class VaccinationController {

    @Autowired
    private VaccinationService vaccinationService;

    @GetMapping
    public List<Vaccination> getAllVacinacoes() {
        return vaccinationService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vaccination> getVacinacaoById(@PathVariable Long id) {
        return vaccinationService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Vaccination createVacinacao(@RequestBody Vaccination vaccination) {
        return vaccinationService.save(vaccination);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vaccination> updateVacinacao(@PathVariable Long id, @RequestBody Vaccination vaccination) {
        if (!vaccinationService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        vaccination.setId(id);
        return ResponseEntity.ok(vaccinationService.save(vaccination));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVacinacao(@PathVariable Long id) {
        if (!vaccinationService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        vaccinationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}