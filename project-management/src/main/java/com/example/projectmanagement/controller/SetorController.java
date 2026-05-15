package com.example.projectmanagement.controller;

import com.example.projectmanagement.model.Setor;
import com.example.projectmanagement.service.SetorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/setores")
@CrossOrigin(origins = "*")
public class SetorController {

    @Autowired
    private SetorService setorService;

    @GetMapping
    public List<Setor> getAllSetores() {
        return setorService.findAll();
    }

    @GetMapping("/with-funcionarios")
    public List<Setor> getAllSetoresWithFuncionarios() {
        return setorService.findAllWithFuncionarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Setor> getSetorById(@PathVariable Long id) {
        return setorService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Setor createSetor(@RequestBody Setor setor) {
        return setorService.save(setor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Setor> updateSetor(@PathVariable Long id, @RequestBody Setor setor) {
        if (!setorService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        setor.setId(id);
        return ResponseEntity.ok(setorService.save(setor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSetor(@PathVariable Long id) {
        if (!setorService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        setorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}