package com.springAngluar.services;

import com.springAngluar.dtos.TecnicoDto;
import com.springAngluar.models.Pessoa;
import com.springAngluar.models.Tecnico;
import com.springAngluar.repositories.PessoaRepository;
import com.springAngluar.repositories.TecnicoRepository;
import com.springAngluar.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository repository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public List<Tecnico> findAll(){
        return repository.findAll();
    }

    public Tecnico findById(Long id){
        Optional<Tecnico> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto Não encontrado! Id: " + id));
    }

    public Tecnico create(TecnicoDto objDto) {
        objDto.setId(null);
        validateForCpfAndEmail(objDto);
        Tecnico newObj = new Tecnico(objDto);
        return repository.save(newObj);
    }

    public Tecnico update(Long id, TecnicoDto objDto) {
        objDto.setId(id);
        Tecnico oldObj = findById(id);
        validateForCpfAndEmail(objDto);
        oldObj = new Tecnico(objDto);
        return  repository.save(oldObj);
    }

    public void delete(Long id){
        Tecnico obj = findById(id);
        if(obj.getChamados().size() > 0){
            throw  new DataIntegrityViolationException("Tecnico possui " +
                    " ordens de Serviço e não pode ser deletado");
        } else {
            repository.deleteById(id);
        }
    }

    private void validateForCpfAndEmail(TecnicoDto objDto) {
        Optional<Pessoa> obj = pessoaRepository.findByCpf(objDto.getCpf());
        if(obj.isPresent() && obj.get().getId() != objDto.getId()){
            throw new DataIntegrityViolationException("CPF Já Cadastrado no Sistema");
        }

        obj = pessoaRepository.findByEmail(objDto.getEmail());
        if(obj.isPresent() && obj.get().getId() != objDto.getId()){
            throw new DataIntegrityViolationException("E-mail Já Cadastrado no Sistema");
        }
    }

}
