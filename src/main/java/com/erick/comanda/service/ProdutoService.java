package com.erick.comanda.service;

import java.util.List;

import com.erick.comanda.domain.Produto;
import com.erick.comanda.repository.ProdutoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {
    
    @Autowired
    private ProdutoRepository dao;

    public Produto buscar(Integer id){
        return dao.findById(id).orElse(null);
    }

    public List<Produto> listar(){
        return dao.findAll();
    }

    public Produto salvar(Produto produto) {
        return dao.save(produto);
    }

    public void excluir(Integer id) {
        dao.deleteById(id);
    }

}