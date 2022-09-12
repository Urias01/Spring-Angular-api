package com.springAngluar.services;

import ch.qos.logback.core.net.server.Client;
import com.springAngluar.models.Chamado;
import com.springAngluar.models.Cliente;
import com.springAngluar.models.Tecnico;
import com.springAngluar.models.dtos.ChamadoDto;
import com.springAngluar.models.enums.Prioridade;
import com.springAngluar.models.enums.Status;
import com.springAngluar.repositories.ChamadoRepository;
import com.springAngluar.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository repository;

    @Autowired
    private TecnicoService tecnicoService;

    @Autowired
    private ClienteService clienteService;

    public Chamado findById(Long id){
        Optional<Chamado> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
    }

    public List<Chamado> findAll() {
        return repository.findAll();
    }

    public Chamado create(ChamadoDto chamadoDto) {
        return repository.save(newChamado(chamadoDto));
    }
    public Chamado update(Long id, ChamadoDto chamadoDto) {
        chamadoDto.setId(id);
        Chamado oldChamado = findById(id);
        oldChamado = newChamado(chamadoDto);
        return repository.save(oldChamado);
    }

    private Chamado newChamado(ChamadoDto chamadoDto){
        Tecnico tecnico = tecnicoService.findById(chamadoDto.getTecnico());
        Cliente cliente = clienteService.findById(chamadoDto.getCliente());

        Chamado chamado = new Chamado();
        if(chamado.getId() != null){
            chamado.setId(chamadoDto.getId());
        }

        if(chamadoDto.getStatus().equals(2)){
            chamado.setDateFechamento(LocalDate.now());
        }

        chamado.setTecnico(tecnico);
        chamado.setCliente(cliente);
        chamado.setPrioridade(Prioridade.toEnum(chamadoDto.getPrioridade()));
        chamado.setStatus(Status.toEnum(chamadoDto.getStatus()));
        chamado.setTitle(chamadoDto.getTitle());
        chamado.setObservacoes(chamadoDto.getObservacoes());
        return chamado;
    }


}
