package com.example.petcare.service;

import com.example.petcare.model.Animal;
import com.example.petcare.model.Appointment;
import com.example.petcare.repository.AnimalRepository;
import com.example.petcare.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private AnimalRepository animalRepository;

    public List<Appointment> findAll() {
        return appointmentRepository.findAll();
    }

    public Optional<Appointment> findById(Long id) {
        return appointmentRepository.findById(id);
    }

    public Appointment save(Appointment appointment) {
        // Validações
        if (appointment.getDataHora() == null) {
            throw new IllegalArgumentException("Data e hora da consulta são obrigatórias");
        }
        if (appointment.getVeterinario() == null || appointment.getVeterinario().isEmpty()) {
            throw new IllegalArgumentException("Veterinário é obrigatório");
        }
        if (appointment.getEspecialidade() == null || appointment.getEspecialidade().isEmpty()) {
            throw new IllegalArgumentException("Especialidade é obrigatória");
        }
        if (appointment.getAnimal() == null) {
            throw new IllegalArgumentException("Animal é obrigatório");
        }

        // Verificar conflito de agenda
        LocalDateTime start = appointment.getDataHora();
        LocalDateTime end = start.plusHours(1); // Assumindo 1 hora por consulta
        LocalDateTime startMinusOneHour = start.minusHours(1);
        List<Appointment> conflicting = appointmentRepository.findConflictingAppointments(
            appointment.getVeterinario(), startMinusOneHour, end);
        for (Appointment conflict : conflicting) {
            if (appointment.getId() == null || !conflict.getId().equals(appointment.getId())) {
                throw new IllegalArgumentException("Conflito de agenda para o veterinário");
            }
        }

        // Verificar especialidade do veterinário (simplificado - em produção, teria tabela de veterinários)
        // Aqui assumimos que a especialidade deve corresponder à espécie do animal
        Animal animal = animalRepository.findById(appointment.getAnimal().getId())
            .orElseThrow(() -> new IllegalArgumentException("Animal não encontrado"));
        if (!appointment.getEspecialidade().equalsIgnoreCase(animal.getEspecie())) {
            throw new IllegalArgumentException("Veterinário não atende esta especialidade");
        }
        appointment.setAnimal(animal);

        return appointmentRepository.save(appointment);
    }

    public void deleteById(Long id) {
        appointmentRepository.deleteById(id);
    }

}