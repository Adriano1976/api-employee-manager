package com.developer.gerenciadorFuncionarios.repositorio;

import com.developer.gerenciadorFuncionarios.modelo.Funcionario;
import com.developer.gerenciadorFuncionarios.modelo.FuncionarioSetor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuncionarioRepositorio extends JpaRepository<Funcionario, Long> {

    List<Funcionario> findBySetor(FuncionarioSetor funcionarioSetor);
}
