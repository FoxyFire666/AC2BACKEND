package com.example.projectmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VinculoProjetoFuncionarioDTO {
    private Long projetoId;
    private Long funcionarioId;
}
