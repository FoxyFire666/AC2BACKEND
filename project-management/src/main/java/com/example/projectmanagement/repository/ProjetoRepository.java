package com.example.projectmanagement.repository;

import com.example.projectmanagement.model.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Long> {

    @Query("SELECT p FROM Projeto p JOIN FETCH p.funcionarios WHERE p.id = :id")
    Projeto findByIdWithFuncionarios(@Param("id") Long id);

    @Query("SELECT p FROM Projeto p WHERE p.dataInicio >= :startDate AND p.dataFim <= :endDate")
    List<Projeto> findByDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT p FROM Projeto p JOIN p.funcionarios f WHERE f.id = :funcionarioId")
    List<Projeto> findByFuncionarioId(@Param("funcionarioId") Long funcionarioId);

}