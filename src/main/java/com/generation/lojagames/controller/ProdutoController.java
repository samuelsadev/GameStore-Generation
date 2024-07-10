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
    public Optional<ProdutoGame> getProdutoGameById(@PathVariable Long id) {
        return produtoGameRepository.findById(id);
    }

    @PostMapping
    public ProdutoGame createProdutoGame(@RequestBody ProdutoGame produtoGame) {
        return produtoGameRepository.save(produtoGame);
    }

//    @PutMapping("/{id}")
//    public ProdutoGame updateProdutoGame(@PathVariable Long id, @RequestBody ProdutoGame produtoGameDetails) {
//        ProdutoGame produtoGame = produtoGameRepository.findById(id).orElseThrow();
//        produtoGame.setNome(produtoGameDetails.getNome());
//        produtoGame.setPreco(produtoGameDetails.getPreco());
//        produtoGame.setCategoriaGame(produtoGameDetails.getCategoriaGame());
//        return produtoGameRepository.save(produtoGame);
//    }

    @PutMapping
    public ResponseEntity<ProdutoGame> put(@Valid @RequestBody ProdutoGame produtoGame) {
        if (produtoGameRepository.existsById(produtoGame.getId())) {

            if (categoriaGameRepository.existsById(produtoGame.getCategoriaGame().getId()))
                return ResponseEntity.status(HttpStatus.OK)
                        .body(produtoGameRepository.save(produtoGame));

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tema não existe!", null);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public void deleteProdutoGame(@PathVariable Long id) {
        produtoGameRepository.deleteById(id);
    }
}
