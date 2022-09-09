package com.springAngluar.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springAngluar.dtos.TecnicoDto;
import com.springAngluar.models.enums.Perfil;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity
public class Tecnico extends Pessoa{
    @Serial
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @OneToMany(mappedBy = "tecnico")
    private List<Chamado> chamados = new ArrayList<>();

    public Tecnico() {
        super();
        addPerfil(Perfil.CLIENTE);
    }

    public Tecnico(Long id, String name, String cpf, String email, String password) {
        super(id, name, cpf, email, password);
        addPerfil(Perfil.CLIENTE);
    }

    public Tecnico(TecnicoDto tecnico) {
        super();
        id = tecnico.getId();
        name = tecnico.getName();
        cpf = tecnico.getCpf();
        email = tecnico.getEmail();
        password = tecnico.getPassword();
        perfis = tecnico.getPerfis().stream().map(Perfil::getCodigo).collect(Collectors.toSet());
        dataCreate = tecnico.getDataCreate();
    }



}
