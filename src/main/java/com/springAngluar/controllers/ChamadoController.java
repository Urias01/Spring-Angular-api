package com.springAngluar.controllers;

import com.springAngluar.models.Chamado;
import com.springAngluar.models.dtos.ChamadoDto;
import com.springAngluar.services.ChamadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/chamados")
public class ChamadoController {

    @Autowired
    private ChamadoService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ChamadoDto> findById(@PathVariable  Long id){
        Chamado obj = service.findById(id);
        return ResponseEntity.ok().body(new ChamadoDto(obj));
    }
    
}
