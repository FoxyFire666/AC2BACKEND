package com.example.projectmanagement.service;

import com.example.projectmanagement.model.Setor;
import com.example.projectmanagement.repository.SetorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SetorService {

    @Autowired
    private SetorRepository setorRepository;

    public List<Setor> findAll() {
        return setorRepository.findAll();
    }

    public List<Setor> findAllWithFuncionarios() {
        return setorRepository.findAllWithFuncionarios();
    }

    public Optional<Setor> findById(Long id) {
        return setorRepository.findById(id);
    }

    public Setor save(Setor setor) {
        // Validações
        if (setor.getNome() == null || setor.getNome().isEmpty()) {
            throw new IllegalArgumentException("Nome do setor é obrigatório");
        }
        return setorRepository.save(setor);
    }

    public void deleteById(Long id) {
        setorRepository.deleteById(id);
    }

}