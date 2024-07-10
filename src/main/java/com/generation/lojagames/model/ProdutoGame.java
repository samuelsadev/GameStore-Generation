package com.generation.lojagames.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table (name = "tb_games")
public class ProdutoGame {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O atributo nome é obrigatório!")
    @Size(max = 255, message = "O atributo deve ter até 255 caracteres")
    private String nome;

    @NotNull (message = "O preço é obrigatório")
    private double preco;

    @NotBlank(message = "A descrição é obrigatória!")
    @Size(max = 255, message = "O atributo deve ter até 255 caracteres")
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "categoria_game_id")
    @JsonIgnoreProperties("produtoGame")
    private CategoriaGame categoriaGame;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public CategoriaGame getCategoriaGame() {
        return categoriaGame;
    }

    public void setCategoriaGame(CategoriaGame categoriaGame) {
        this.categoriaGame = categoriaGame;
    }
}