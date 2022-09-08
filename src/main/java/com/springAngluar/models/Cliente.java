package com.springAngluar.models;

import com.springAngluar.models.enums.Perfil;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Cliente extends Pessoa{
    @Serial
    private static final long serialVersionUID = 1L;

    @OneToMany(mappedBy = "cliente")
    private List<Chamado> chamados = new ArrayList<>();

    public Cliente() {
        super();
        addPerfil(Perfil.CLIENTE);
    }

    public Cliente(Long id, String name, String cpf, String email, String password) {
        super(id, name, cpf, email, password);
        addPerfil(Perfil.CLIENTE);
    }
}
