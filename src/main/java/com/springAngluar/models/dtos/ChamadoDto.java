package com.springAngluar.models.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.springAngluar.models.Chamado;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class ChamadoDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateAbertura = LocalDate.now();
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateFechamento;
    private Integer prioridade;
    private Integer status;
    private String title;
    private String observacoes;
    private Long tecnico;
    private Long cliente;
    private String nomeTecnico;
    private String nomeCliente;

    public ChamadoDto() {
        super();
    }

    public ChamadoDto(Chamado chamado) {
        id = chamado.getId();
        dateAbertura = chamado.getDateAbertura();
        dateFechamento = chamado.getDateFechamento();
        prioridade = chamado.getPrioridade().getCodigo();
        status = chamado.getStatus().getCodigo();
        title = chamado.getTitle();
        observacoes = chamado.getObservacoes();
        tecnico = chamado.getTecnico().getId();
        cliente = chamado.getCliente().getId();
        nomeTecnico = chamado.getTecnico().getName();
        nomeCliente = chamado.getCliente().getName();
    }
}
