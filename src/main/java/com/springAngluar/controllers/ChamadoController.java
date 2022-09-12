package com.springAngluar.controllers;

import com.springAngluar.models.Chamado;
import com.springAngluar.models.dtos.ChamadoDto;
import com.springAngluar.services.ChamadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping
    public ResponseEntity<List<ChamadoDto>> findAll(){
        List<Chamado> list = service.findAll();
        List<ChamadoDto> listDto = list.stream().map(ChamadoDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @PostMapping
    public ResponseEntity<ChamadoDto> create(@Valid
                                                 @RequestBody ChamadoDto chamadoDto){
        Chamado chamado = service.create(chamadoDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(chamado.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ChamadoDto> update(
            @Valid @RequestBody ChamadoDto chamadoDto,
            @PathVariable Long id){
        Chamado newChamado = service.update(id, chamadoDto);
        return ResponseEntity.ok().body(new ChamadoDto(newChamado));
    }


}
