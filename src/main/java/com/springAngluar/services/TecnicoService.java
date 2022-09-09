package com.springAngluar.services;

import com.springAngluar.dtos.TecnicoDto;
import com.springAngluar.models.Tecnico;
import com.springAngluar.repositories.TecnicoRepository;
import com.springAngluar.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository repository;

    public List<Tecnico> findAll(){
        return repository.findAll();
    }

    public Tecnico findById(Long id){
        Optional<Tecnico> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto Não encontrado! Id: " + id));
    }

    public Tecnico create(TecnicoDto objDto) {
        objDto.setId(null);
        Tecnico newObj = new Tecnico(objDto);
        return repository.save(newObj);
    }
}
