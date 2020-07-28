package com.erick.comanda.resource;

import java.net.URI;
import com.erick.comanda.domain.Produto;
import com.erick.comanda.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {
 
    @Autowired
    private ProdutoService service;
    
    @GetMapping
    public ResponseEntity<?> listar(){
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<Produto> buscar(@PathVariable Integer id){
        return ResponseEntity.ok(service.buscar(id));
    }

    @PostMapping
    public ResponseEntity<Produto> criarProduto(@RequestBody Produto obj) {
        obj = service.salvar(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<Void> editarProduto(@PathVariable Integer id, @RequestBody Produto obj) {
        service.salvar(obj);
        return ResponseEntity.noContent().build();
    }   

    @DeleteMapping(value="/{id}")
    public ResponseEntity<Void> excluirProduto(@PathVariable Integer id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}