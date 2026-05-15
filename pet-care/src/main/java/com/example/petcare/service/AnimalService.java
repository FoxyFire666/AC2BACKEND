package com.example.petcare.service;

import com.example.petcare.model.Animal;
import com.example.petcare.repository.AnimalRepository;
import com.example.petcare.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private TutorRepository tutorRepository;

    public List<Animal> findAll() {
        return animalRepository.findAll();
    }

    public Optional<Animal> findById(Long id) {
        return animalRepository.findById(id);
    }

    public Animal save(Animal animal) {
        // Validações
        if (animal.getNome() == null || animal.getNome().isEmpty()) {
            throw new IllegalArgumentException("Nome do animal é obrigatório");
        }
        if (animal.getEspecie() == null || animal.getEspecie().isEmpty()) {
            throw new IllegalArgumentException("Espécie do animal é obrigatória");
        }
        if (animal.getTutor() == null || animal.getTutor().getId() == null) {
            throw new IllegalArgumentException("Tutor do animal é obrigatório e deve ser um tutor existente");
        }
        var tutor = tutorRepository.findById(animal.getTutor().getId())
                .orElseThrow(() -> new IllegalArgumentException("Tutor não encontrado"));
        animal.setTutor(tutor);

        return animalRepository.save(animal);
    }

    public void deleteById(Long id) {
        animalRepository.deleteById(id);
    }

}