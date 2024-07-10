package com.generation.lojagames.repository;

import com.generation.lojagames.model.ProdutoGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProdutoGameRepository extends JpaRepository<ProdutoGame, Long> {
    List<ProdutoGame> findAllByNomeContainingIgnoreCase(@Param("nome") String nome);
}
