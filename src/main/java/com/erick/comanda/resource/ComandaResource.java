package com.erick.comanda.resource;

import java.net.URI;
import java.util.List;

import com.erick.comanda.domain.Comanda;
import com.erick.comanda.dto.ComandaItensDTO;
import com.erick.comanda.service.ComandaService;
import com.erick.comanda.service.exceptions.DataIntegrityException;
import com.erick.comanda.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/comandas")
public class ComandaResource {

    @Autowired
    private ComandaService service;

    @GetMapping
    public ResponseEntity<List<Comanda>> listar(){
        List<Comanda> comandas = service.listar();
        return (!comandas.isEmpty()) ? ResponseEntity.ok(comandas) : ResponseEntity.notFound().build();
    }

    @GetMapping(value="/{numeroMesa}")
    public ResponseEntity<ComandaItensDTO> buscarComandaPorNumeroMesa(@PathVariable Integer numeroMesa) throws ObjectNotFoundException {
        ComandaItensDTO comanda = service.buscarComandaPorNumeroMesa(numeroMesa);
        return (comanda != null) ? ResponseEntity.ok(comanda) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Void> salvarComanda(@RequestBody ComandaItensDTO comandaItens) throws DataIntegrityException, ObjectNotFoundException {
        Comanda comanda = service.salvarComanda(comandaItens);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand("/" + comanda.getNumero()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping
    public ResponseEntity<Void> editarComanda(@RequestBody ComandaItensDTO comanda) throws DataIntegrityException, ObjectNotFoundException {
        service.editarComanda(comanda);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value="/{numeroMesa}")
    public ResponseEntity<Void> removerComanda(@PathVariable Integer numeroMesa) throws ObjectNotFoundException {
    	service.excluirComanda(numeroMesa);
    	return ResponseEntity.noContent().build();
    }
}