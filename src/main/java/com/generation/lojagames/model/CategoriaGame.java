package com.generation.lojagames.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;


@Entity
@Table (name = "tb_categoria")
public class CategoriaGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O atributo categoria é obrigatório!")
    @Size(max = 255, message = "O atributo deve ter até 255 caracteres")
    private String tipoGame;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "categoriaGame", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<ProdutoGame> produtosGame;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoGame() {
        return tipoGame;
    }

    public void setTipoGame(String tipoGame) {
        this.tipoGame = tipoGame;
    }

    public List<ProdutoGame> getProdutosGame() {
        return produtosGame;
    }

    public void setProdutosGame(List<ProdutoGame> produtosGame) {
        this.produtosGame = produtosGame;
    }

}