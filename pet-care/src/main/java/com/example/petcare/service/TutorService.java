package com.example.petcare.service;

import com.example.petcare.model.Tutor;
import com.example.petcare.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TutorService {

    @Autowired
    private TutorRepository tutorRepository;

    public List<Tutor> findAll() {
        return tutorRepository.findAll();
    }

    public Optional<Tutor> findById(Long id) {
        return tutorRepository.findById(id);
    }

    public Tutor save(Tutor tutor) {
        // Validações
        if (tutor.getNome() == null || tutor.getNome().isEmpty()) {
            throw new IllegalArgumentException("Nome do tutor é obrigatório");
        }
        if (tutor.getEmail() == null || tutor.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email do tutor é obrigatório");
        }
        return tutorRepository.save(tutor);
    }

    public void deleteById(Long id) {
        tutorRepository.deleteById(id);
    }

}