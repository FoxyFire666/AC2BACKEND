package com.example.petcare.service;

import com.example.petcare.model.MedicalRecord;
import com.example.petcare.repository.MedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MedicalRecordService {

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    public List<MedicalRecord> findAll() {
        return medicalRecordRepository.findAll();
    }

    public Optional<MedicalRecord> findById(Long id) {
        return medicalRecordRepository.findById(id);
    }

    public MedicalRecord save(MedicalRecord medicalRecord) {
        // Validações
        if (medicalRecord.getDataConsulta() == null) {
            throw new IllegalArgumentException("Data da consulta é obrigatória");
        }
        if (medicalRecord.getDiagnostico() == null || medicalRecord.getDiagnostico().isEmpty()) {
            throw new IllegalArgumentException("Diagnóstico é obrigatório");
        }
        if (medicalRecord.getVeterinario() == null || medicalRecord.getVeterinario().isEmpty()) {
            throw new IllegalArgumentException("Veterinário é obrigatório");
        }
        if (medicalRecord.getAnimal() == null) {
            throw new IllegalArgumentException("Animal é obrigatório");
        }
        return medicalRecordRepository.save(medicalRecord);
    }

    public void deleteById(Long id) {
        medicalRecordRepository.deleteById(id);
    }

}