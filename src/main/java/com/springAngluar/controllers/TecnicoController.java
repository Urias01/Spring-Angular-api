package com.springAngluar.controllers;

import com.springAngluar.dtos.TecnicoDto;
import com.springAngluar.models.Tecnico;
import com.springAngluar.services.TecnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoController {

    @Autowired
    private TecnicoService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<TecnicoDto> findById(@PathVariable Long id){
        Tecnico obj = service.findById(id);
        return ResponseEntity.ok().body(new TecnicoDto(obj));
    }
}