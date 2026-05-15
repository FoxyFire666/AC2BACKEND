package com.example.petcare.repository;

import com.example.petcare.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query("SELECT a FROM Appointment a WHERE a.veterinario = :veterinario " +
           "AND a.dataHora < :end AND a.dataHora > :startMinusOneHour")
    List<Appointment> findConflictingAppointments(@Param("veterinario") String veterinario,
                                                  @Param("startMinusOneHour") LocalDateTime startMinusOneHour,
                                                  @Param("end") LocalDateTime end);

}