package com.example.projectmanagement.service;

import com.example.projectmanagement.model.Projeto;
import com.example.projectmanagement.model.Funcionario;
import com.example.projectmanagement.repository.ProjetoRepository;
import com.example.projectmanagement.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProjetoService {

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public List<Projeto> findAll() {
        return projetoRepository.findAll();
    }

    public Optional<Projeto> findById(Long id) {
        return projetoRepository.findById(id);
    }

    public Projeto findByIdWithFuncionarios(Long id) {
        return projetoRepository.findByIdWithFuncionarios(id);
    }

    public Projeto save(Projeto projeto) {
        // Validações
        if (projeto.getNome() == null || projeto.getNome().isEmpty()) {
            throw new IllegalArgumentException("Nome do projeto é obrigatório");
        }
        if (projeto.getDataInicio() != null && projeto.getDataFim() != null &&
            projeto.getDataInicio().isAfter(projeto.getDataFim())) {
            throw new IllegalArgumentException("Data de início não pode ser após a data de fim");
        }
        return projetoRepository.save(projeto);
    }

    public void deleteById(Long id) {
        projetoRepository.deleteById(id);
    }

    public List<Projeto> findByDateRange(LocalDate startDate, LocalDate endDate) {
        return projetoRepository.findByDateRange(startDate, endDate);
    }

    public List<Projeto> findByFuncionarioId(Long funcionarioId) {
        return projetoRepository.findByFuncionarioId(funcionarioId);
    }

    public Projeto vincularFuncionario(Long projetoId, Long funcionarioId) {
        Projeto projeto = projetoRepository.findById(projetoId)
                .orElseThrow(() -> new IllegalArgumentException("Projeto não encontrado"));
        Funcionario funcionario = funcionarioRepository.findById(funcionarioId)
                .orElseThrow(() -> new IllegalArgumentException("Funcionário não encontrado"));

        if (!projeto.getFuncionarios().contains(funcionario)) {
            projeto.getFuncionarios().add(funcionario);
            return projetoRepository.save(projeto);
        }
        return projeto;
    }

    public Projeto desvincullarFuncionario(Long projetoId, Long funcionarioId) {
        Projeto projeto = projetoRepository.findById(projetoId)
                .orElseThrow(() -> new IllegalArgumentException("Projeto não encontrado"));
        Funcionario funcionario = funcionarioRepository.findById(funcionarioId)
                .orElseThrow(() -> new IllegalArgumentException("Funcionário não encontrado"));

        projeto.getFuncionarios().remove(funcionario);
        return projetoRepository.save(projeto);
    }

}