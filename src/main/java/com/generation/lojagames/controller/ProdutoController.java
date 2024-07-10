package com.generation.lojagames.controller;

import com.generation.lojagames.model.ProdutoGame;
import com.generation.lojagames.repository.CategoriaGameRepository;
import com.generation.lojagames.repository.ProdutoGameRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/games")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {

    @Autowired
    private ProdutoGameRepository produtoGameRepository;

    @Autowired
    private CategoriaGameRepository categoriaGameRepository;

    @GetMapping
    public List<ProdutoGame> getAllProdutosGame() {
        return produtoGameRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoGame> getById (@PathVariable Long id) {
        return produtoGameRepository.findById(id)
                //.map(reposta -> ResponseEntity.ok(reposta))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<ProdutoGame>> getByTitulo(@PathVariable String titulo) {
        return ResponseEntity.ok(produtoGameRepository
                .findAllByNomeContainingIgnoreCase(titulo));
    }

    @PostMapping
    public ResponseEntity<ProdutoGame> post(@Valid @RequestBody ProdutoGame produtoGame) {
        if (categoriaGameRepository.existsById(produtoGame.getCategoriaGame().getId()))
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(produtoGameRepository.save(produtoGame));
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria não existe!", null);
    }


    @PutMapping
    public ResponseEntity<ProdutoGame> put(@Valid @RequestBody ProdutoGame produtoGame) {
        if (produtoGameRepository.existsById(produtoGame.getId())) {

            if (categoriaGameRepository.existsById(produtoGame.getCategoriaGame().getId()))
                return ResponseEntity.status(HttpStatus.OK)
                        .body(produtoGameRepository.save(produtoGame));

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria não existe!", null);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Optional<ProdutoGame> produtoGame = produtoGameRepository.findById(id);

        if (produtoGame.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

       produtoGameRepository.deleteById(id);
    }
}
