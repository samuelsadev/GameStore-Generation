package com.generation.lojagames.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table (name = "tb_categoria")
public class CategoriaGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O atributo categoria é obrigatório!")
    @Size(max = 255, message = "O atributo deve ter até 255 caracteres")
    private String tipoGame;
}
