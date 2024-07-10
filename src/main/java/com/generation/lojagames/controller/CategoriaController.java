package com.generation.lojagames.controller;

import com.generation.lojagames.model.CategoriaGame;
import com.generation.lojagames.repository.CategoriaGameRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

    @GetMapping("/tipoGame/{tipoGame}")
    public ResponseEntity<List<CategoriaGame>> getByTitle(@PathVariable String categoria) {
        return ResponseEntity.ok(categoriaGameRepository
                .findAllByTipoGameContainingIgnoreCase(categoria));
    }

    @PostMapping
    public ResponseEntity<CategoriaGame> post(@Valid @RequestBody CategoriaGame categoriaGame) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categoriaGameRepository.save(categoriaGame));
    }

    @PutMapping
    public ResponseEntity<CategoriaGame> put(@Valid @RequestBody CategoriaGame categoriaGame){
        return categoriaGameRepository.findById(categoriaGame.getId())
                .map(resposta -> ResponseEntity.status(HttpStatus.CREATED)
                        .body(categoriaGameRepository.save(categoriaGame)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Optional<CategoriaGame> categoriaGame = categoriaGameRepository.findById(id);

        if(categoriaGame.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        categoriaGameRepository.deleteById(id);
    }
}