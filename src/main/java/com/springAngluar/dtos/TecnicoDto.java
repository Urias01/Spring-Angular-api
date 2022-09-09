package com.springAngluar.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.springAngluar.models.Tecnico;
import com.springAngluar.models.enums.Perfil;
import lombok.Data;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class TecnicoDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    protected Long id;
    protected String name;
    protected String cpf;
    protected String email;
    protected String password;
    protected Set<Integer> perfis = new HashSet<>();

    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate dataCreate = LocalDate.now();

    public TecnicoDto() {
        super();
    }

    public TecnicoDto(Tecnico tecnico) {
        id = tecnico.getId();
        name = tecnico.getName();
        cpf = tecnico.getCpf();
        email = tecnico.getEmail();
        password = tecnico.getPassword();
        perfis = tecnico.getPerfis().stream().map(Perfil::getCodigo).collect(Collectors.toSet());
        dataCreate = tecnico.getDataCreate();
    }

    public Set<Perfil> getPerfis(){
        return perfis.stream().map(Perfil::toEnum).collect(Collectors.toSet());
    }

    public void setPerfis(Perfil perfis){
        this.perfis.add(perfis.getCodigo());
    }
}
