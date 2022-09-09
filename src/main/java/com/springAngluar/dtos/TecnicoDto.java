package com.springAngluar.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.springAngluar.models.Tecnico;
import com.springAngluar.models.enums.Perfil;
import com.sun.istack.NotNull;
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
        addPerfil(Perfil.CLIENTE);
    }


    public TecnicoDto(Tecnico obj) {
        super();
        this.id = obj.getId();
        this.name = obj.getName();
        this.cpf = obj.getCpf();
        this.email = obj.getEmail();
        this.password = obj.getPassword();
        this.perfis = obj.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
        this.dataCreate = obj.getDataCreate();
        addPerfil(Perfil.CLIENTE);
    }

    public Set<Perfil> getPerfis(){
        return perfis.stream().map(Perfil::toEnum).collect(Collectors.toSet());
    }

    public void setPerfis(Perfil perfis){
        this.perfis.add(perfis.getCodigo());
    }

    public void addPerfil(Perfil perfil) {
        this.perfis.add(perfil.getCodigo());
    }
}
