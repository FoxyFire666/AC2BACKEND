package com.example.petcare.service;

import com.example.petcare.model.Vaccination;
import com.example.petcare.repository.VaccinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class VaccinationService {

    @Autowired
    private VaccinationRepository vaccinationRepository;

    public List<Vaccination> findAll() {
        return vaccinationRepository.findAll();
    }

    public Optional<Vaccination> findById(Long id) {
        return vaccinationRepository.findById(id);
    }

    public Vaccination save(Vaccination vaccination) {
        // Validações
        if (vaccination.getNomeVacina() == null || vaccination.getNomeVacina().isEmpty()) {
            throw new IllegalArgumentException("Nome da vacina é obrigatório");
        }
        if (vaccination.getDataAplicacao() == null) {
            throw new IllegalArgumentException("Data de aplicação é obrigatória");
        }
        if (vaccination.getAnimal() == null) {
            throw new IllegalArgumentException("Animal é obrigatório");
        }
        return vaccinationRepository.save(vaccination);
    }

    public void deleteById(Long id) {
        vaccinationRepository.deleteById(id);
    }

}