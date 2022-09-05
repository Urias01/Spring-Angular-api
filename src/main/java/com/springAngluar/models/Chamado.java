package com.springAngluar.models;

import com.springAngluar.models.enums.Prioridade;
import com.springAngluar.models.enums.Status;
import lombok.Data;

import java.time.LocalDate;

@Data
public class Chamado {

    private Long id;
    private LocalDate dateAbertura = LocalDate.now();
    private LocalDate dateFechamento;
    private Prioridade prioridade;
    private Status status;
    private String title;
    private String observacoes;
}
