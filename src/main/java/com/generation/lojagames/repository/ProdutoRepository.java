package com.generation.lojagames.repository;

import com.generation.lojagames.model.ProdutoGame;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<ProdutoGame, Long> {
}
