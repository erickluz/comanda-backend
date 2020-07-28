package com.erick.comanda.repository;

import java.util.List;

import com.erick.comanda.domain.ItensComanda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ItensComandaRepository extends JpaRepository<ItensComanda, Integer>{

    @Query("FROM ItensComanda ic WHERE ic.comanda.id = ?1")
    List<ItensComanda> buscarItensPorComanda(Integer idComanda);

    @Query("FROM ItensComanda ic WHERE ic.produto.id = ?1 AND ic.comanda.id = ?2")
    ItensComanda buscarItemPorProdutoEComanda(Integer idProduto, Integer idComanda);

    @Transactional
    @Modifying
    @Query(value="DELETE ic FROM itens_comanda ic, comanda c "
    		   + "WHERE c.id = ic.id_comanda "
    		   + "AND c.numero = ?1", nativeQuery = true)
    void excluirItensPorNumeroDeComanda(Integer numeroComanda);
}