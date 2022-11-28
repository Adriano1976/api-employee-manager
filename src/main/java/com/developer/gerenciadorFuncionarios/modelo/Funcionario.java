package com.developer.gerenciadorFuncionarios.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class Funcionario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String sobrenome;
    private String email;
    private String cargo;
    private BigDecimal salario;

    @Enumerated(EnumType.STRING)
    private FuncionarioSetor setor;
}
