package com.springAngluar.services;

import com.springAngluar.models.Tecnico;
import com.springAngluar.repositories.TecnicoRepository;
import com.springAngluar.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository repository;

    public Tecnico findById(Long id){
        Optional<Tecnico> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto Não encontrado! Id: " + id));
    }

}
