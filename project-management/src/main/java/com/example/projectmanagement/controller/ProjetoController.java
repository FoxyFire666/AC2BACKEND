package com.example.projectmanagement.controller;

import com.example.projectmanagement.model.Projeto;
import com.example.projectmanagement.service.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/projetos")
@CrossOrigin(origins = "*")
public class ProjetoController {

    @Autowired
    private ProjetoService projetoService;

    @GetMapping
    public List<Projeto> getAllProjetos() {
        return projetoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Projeto> getProjetoById(@PathVariable Long id) {
        return projetoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/with-funcionarios")
    public ResponseEntity<Projeto> getProjetoWithFuncionarios(@PathVariable Long id) {
        Projeto projeto = projetoService.findByIdWithFuncionarios(id);
        if (projeto != null) {
            return ResponseEntity.ok(projeto);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public Projeto createProjeto(@RequestBody Projeto projeto) {
        return projetoService.save(projeto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Projeto> updateProjeto(@PathVariable Long id, @RequestBody Projeto projeto) {
        if (!projetoService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        projeto.setId(id);
        return ResponseEntity.ok(projetoService.save(projeto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProjeto(@PathVariable Long id) {
        if (!projetoService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        projetoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-date-range")
    public List<Projeto> getProjetosByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return projetoService.findByDateRange(startDate, endDate);
    }

    @PostMapping("/{projetoId}/funcionarios/{funcionarioId}")
    public ResponseEntity<Projeto> vincularFuncionario(
            @PathVariable Long projetoId,
            @PathVariable Long funcionarioId) {
        Projeto projeto = projetoService.vincularFuncionario(projetoId, funcionarioId);
        return ResponseEntity.ok(projeto);
    }

    @DeleteMapping("/{projetoId}/funcionarios/{funcionarioId}")
    public ResponseEntity<Projeto> desvincullarFuncionario(
            @PathVariable Long projetoId,
            @PathVariable Long funcionarioId) {
        Projeto projeto = projetoService.desvincullarFuncionario(projetoId, funcionarioId);
        return ResponseEntity.ok(projeto);
    }

}