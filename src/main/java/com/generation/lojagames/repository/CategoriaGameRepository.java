package com.generation.lojagames.repository;

import com.generation.lojagames.model.CategoriaGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoriaGameRepository extends JpaRepository<CategoriaGame, Long> {
    List<CategoriaGame> findAllByTipoGameContainingIgnoreCase(@Param("descricaoCategoria") String descricaoCategoria);
}
