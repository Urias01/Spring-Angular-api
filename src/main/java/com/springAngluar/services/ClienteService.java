package com.springAngluar.services;

import com.springAngluar.models.dtos.ClienteDto;
import com.springAngluar.models.Pessoa;
import com.springAngluar.models.Cliente;
import com.springAngluar.repositories.ClienteRepository;
import com.springAngluar.repositories.PessoaRepository;
import com.springAngluar.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public List<Cliente> findAll(){
        return repository.findAll();
    }

    public Cliente findById(Long id){
        Optional<Cliente> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto Não encontrado! Id: " + id));
    }

    public Cliente create(ClienteDto objDto) {
        objDto.setId(null);
        objDto.setPassword(encoder.encode(objDto.getPassword()));
        validateForCpfAndEmail(objDto);
        Cliente newObj = new Cliente(objDto);
        return repository.save(newObj);
    }

    public Cliente update(Long id, ClienteDto objDto) {
        objDto.setId(id);
        Cliente oldObj = findById(id);
        if(!objDto.getPassword().equals(oldObj.getPassword())) {
            objDto.setPassword(encoder.encode(objDto.getPassword()));
        }
        validateForCpfAndEmail(objDto);
        oldObj = new Cliente(objDto);
        return  repository.save(oldObj);
    }

    public void delete(Long id){
        Cliente obj = findById(id);
        if(obj.getChamados().size() > 0){
            throw  new DataIntegrityViolationException("Cliente possui " +
                    " ordens de Serviço e não pode ser deletado");
        } else {
            repository.deleteById(id);
        }
    }

    private void validateForCpfAndEmail(ClienteDto objDto) {
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
