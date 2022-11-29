package com.developer.gerenciadorFuncionarios.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class Funcionario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nome;
    private String email;
    private String cargo;
    private BigDecimal salario;
    private LocalDateTime dataContratacao = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private FuncionarioSetor setor;


    public void setSetor(FuncionarioSetor setor) {
        this.setor = setor;
    }
}
