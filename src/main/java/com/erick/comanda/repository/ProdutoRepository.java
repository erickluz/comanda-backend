package com.erick.comanda.repository;

import org.springframework.stereotype.Repository;

import com.erick.comanda.domain.Produto;

import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    
}