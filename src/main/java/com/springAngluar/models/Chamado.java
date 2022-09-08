package com.springAngluar.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springAngluar.models.enums.Prioridade;
import com.springAngluar.models.enums.Status;
import lombok.Data;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
public class Chamado implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateAbertura = LocalDate.now();

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateFechamento;

    private Prioridade prioridade;
    private Status status;
    private String title;
    private String observacoes;

    @ManyToOne
    @JoinColumn(name = "tecnico_id")
    private Tecnico tecnico;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public Chamado() {
        super();
    }

    public Chamado(Long id, Prioridade prioridade, Status status, String title,
                   String observacoes, Tecnico tecnico, Cliente cliente) {
        super();
        this.id = id;
        this.prioridade = prioridade;
        this.status = status;
        this.title = title;
        this.observacoes = observacoes;
        this.tecnico = tecnico;
        this.cliente = cliente;
    }
}
