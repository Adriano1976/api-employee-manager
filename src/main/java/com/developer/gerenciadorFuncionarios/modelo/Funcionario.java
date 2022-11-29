package com.developer.gerenciadorFuncionarios.modelo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

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
