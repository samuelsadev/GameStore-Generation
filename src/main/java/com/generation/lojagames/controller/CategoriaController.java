package com.generation.lojagames.controller;

import com.generation.lojagames.model.CategoriaGame;
import com.generation.lojagames.repository.CategoriaGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaGameRepository categoriaGameRepository;

    @GetMapping
    public List<CategoriaGame> getAllCategoriasGame() {
        return categoriaGameRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<CategoriaGame> getCategoriaGameById(@PathVariable Long id) {
        return categoriaGameRepository.findById(id);
    }

    @PostMapping
    public CategoriaGame createCategoriaGame(@RequestBody CategoriaGame categoriaGame) {
        return categoriaGameRepository.save(categoriaGame);
    }

    @PutMapping("/{id}")
    public CategoriaGame updateCategoriaGame(@PathVariable Long id, @RequestBody CategoriaGame categoriaGameDetails) {
        CategoriaGame categoriaGame = categoriaGameRepository.findById(id).orElseThrow();
        categoriaGame.setTipoGame(categoriaGameDetails.getTipoGame());
        return categoriaGameRepository.save(categoriaGame);
    }

    @DeleteMapping("/{id}")
    public void deletCategoriaGame(@PathVariable Long id) {
        categoriaGameRepository.deleteById(id);
    }
}