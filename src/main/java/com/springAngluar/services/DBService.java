package com.springAngluar.services;

import com.springAngluar.models.Chamado;
import com.springAngluar.models.Cliente;
import com.springAngluar.models.Tecnico;
import com.springAngluar.models.enums.Perfil;
import com.springAngluar.models.enums.Prioridade;
import com.springAngluar.models.enums.Status;
import com.springAngluar.repositories.ChamadoRepository;
import com.springAngluar.repositories.ClienteRepository;
import com.springAngluar.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ChamadoRepository chamadoRepository;

    public void instaniciaDB(){
        Tecnico t1 = new Tecnico(null, "Valdir Cezar", "41012307301", "valcir@gmail.com", "123");
        t1.addPerfil(Perfil.ADMIN);

        Cliente c1 = new Cliente(null, "Linus Torvalds", "68841135298", "linus@gmail.com", "123");

        Chamado cha1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro Chamado", t1, c1);

        tecnicoRepository.saveAll(Arrays.asList(t1));
        clienteRepository.saveAll(Arrays.asList(c1));
        chamadoRepository.saveAll(Arrays.asList(cha1));
    }
}
