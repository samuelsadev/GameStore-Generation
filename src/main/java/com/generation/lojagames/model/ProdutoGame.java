package com.generation.lojagames.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
}