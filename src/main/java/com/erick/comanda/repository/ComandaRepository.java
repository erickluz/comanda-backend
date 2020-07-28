package com.erick.comanda.repository;

import com.erick.comanda.domain.Comanda;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ComandaRepository extends JpaRepository<Comanda, Integer>{

    @Query("FROM Comanda c WHERE c.numero = ?1")
    Comanda buscarComandaPorNumeroMesa(Integer numeroMesa);
    
    @Transactional
    @Modifying
    @Query("DELETE FROM Comanda c WHERE c.numero = ?1")
    void excluirComandaPorNumeroMesa(Integer numeroMesa);
}