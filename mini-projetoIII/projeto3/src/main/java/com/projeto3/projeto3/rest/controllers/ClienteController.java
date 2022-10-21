package com.projeto3.projeto3.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.projeto3.projeto3.model.Cliente;
import com.projeto3.projeto3.repository.ClienteRepository;
import com.projeto3.projeto3.rest.dto.ClientDTO;
import com.projeto3.projeto3.rest.dto.ClientePedidoDTO;
import com.projeto3.projeto3.service.ClienteService;


@RequestMapping("/api/clientes")
@RestController //anotação especializadas de controller - todos já anotados com response body!
public class ClienteController {

    @Autowired
    private ClienteRepository clientes;


    @Autowired
    private ClienteService service;

    @GetMapping("{id}")
    public Cliente getClienteById( @PathVariable Integer id ){
        return clientes
                .findById(id)
                .orElseThrow(() -> //se nao achar lança o erro!
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Cliente não encontrado"));
    }

    @GetMapping("/pedidos/{id}")
    public ClientePedidoDTO getPedidosDoCliente( @PathVariable Integer id ){
        return service.obterListPedidosDoCliente(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente save( @RequestBody Cliente cliente ){
        return clientes.save(cliente);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete( @PathVariable Integer id ){
        clientes.findById(id)
                .map( cliente -> {
                    clientes.delete(cliente);
                    return cliente;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Cliente não encontrado") );
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update( @PathVariable Integer id,
                        @RequestBody Cliente cliente ){
        clientes
                .findById(id)
                .map( clienteExistente -> {
                    cliente.setId(clienteExistente.getId());
                    clientes.save(cliente);
                    return clienteExistente;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Cliente não encontrado") );
    }

    @GetMapping
    public List<Cliente> find( Cliente filtro ){
        ExampleMatcher matcher = ExampleMatcher
                                    .matching()
                                    .withIgnoreCase()
                                    .withStringMatcher(
                                            ExampleMatcher.StringMatcher.CONTAINING );
        return clientes.findAll(Example.of(filtro, matcher));
    }


    @PatchMapping("attNome/{id}/{nome}")
    public void atualizaNome(   @PathVariable Integer id,
                                @PathVariable String nome) {
        clientes.findById(id)
                .map( cliente -> {
                    cliente.setNome(nome);
                    clientes.save(cliente);
                    return cliente;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Cliente não encontrado") );
    }

    @PatchMapping("attcpf/{id}/{cpf}")
    public void atualizacpf(   @PathVariable Integer id,
                            @PathVariable String cpf) { 
        clientes.findById(id)
                .map( cliente -> {
                    cliente.setCpf(cpf);
                    clientes.save(cliente);
                    return cliente;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Cliente não encontrado") );
    }


    @PatchMapping("att/")
    public void atualizaCliente( @RequestBody ClientDTO clientDTO) { 
        clientes.findById(clientDTO.getId())
                .map( cliente -> {
                    if( clientDTO.getCpf() != "") {
                        cliente.setCpf(clientDTO.getCpf());
                    }

                    if( clientDTO.getNome() != "") {
                        cliente.setNome(clientDTO.getNome());
                    }
                    
                    clientes.save(cliente);
                    return cliente;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Cliente não encontrado") );
    }
}
