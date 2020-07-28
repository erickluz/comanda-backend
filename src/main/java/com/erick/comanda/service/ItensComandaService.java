package com.erick.comanda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erick.comanda.domain.ItensComanda;
import com.erick.comanda.repository.ItensComandaRepository;
import com.erick.comanda.service.exceptions.ObjectNotFoundException;

@Service
public class ItensComandaService {

    @Autowired
    private ItensComandaRepository dao;

    public List<ItensComanda> buscarItensPorComanda(Integer idComanda){
        return dao.buscarItensPorComanda(idComanda);
    }

    public ItensComanda salvar(ItensComanda itemComanda) {
        return dao.save(itemComanda);
    }

    public void salvarItensComanda(List<ItensComanda> itensComandas) {
        dao.saveAll(itensComandas);
    }

    public void remover(Integer id) {
        dao.deleteById(id);
    }

    public ItensComanda buscar(Integer id) throws ObjectNotFoundException {
    	return dao.findById(id).orElse(null);
    }

    public ItensComanda buscarItemPorProdutoEComanda(Integer idProduto, Integer idComanda) {
        return dao.buscarItemPorProdutoEComanda(idProduto, idComanda);
    }

    public void excluirItensPorNumeroDeComanda(Integer idComanda) throws ObjectNotFoundException {
        List<ItensComanda> itens = buscarItensPorComanda(idComanda);
        dao.deleteAll(itens);
    }
}