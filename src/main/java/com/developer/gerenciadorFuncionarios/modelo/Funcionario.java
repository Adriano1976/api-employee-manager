package com.developer.gerenciadorFuncionarios.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Classe responsável em representar o modelo da base de dados.
 *
 * @author Adriano Santos
 */
@Entity
@NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class Funcionario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nome;
    private String sobrenome;
    private String email;
    private String cargo;
    private BigDecimal salario;
    private LocalDate dataContratacao = LocalDate.now();

    @Enumerated(EnumType.STRING)
    private FuncionarioSetor setor;


    public void setSetor(FuncionarioSetor setor) {
        this.setor = setor;
    }
}
