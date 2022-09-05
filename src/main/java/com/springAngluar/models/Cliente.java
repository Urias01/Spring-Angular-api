package com.springAngluar.models;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Pessoa{

    private List<Chamado> chamados = new ArrayList<>();

    public Cliente() {
        this.chamados = chamados;
    }

    public Cliente(Long id, String name, String cpf, String email, String password, List<Chamado> chamados) {
        super(id, name, cpf, email, password);
        this.chamados = chamados;
    }
}
