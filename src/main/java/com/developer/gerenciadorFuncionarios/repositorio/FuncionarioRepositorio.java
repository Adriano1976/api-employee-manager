package com.developer.gerenciadorFuncionarios.repositorio;

import com.developer.gerenciadorFuncionarios.modelo.Funcionario;
import com.developer.gerenciadorFuncionarios.modelo.FuncionarioSetor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ‘interface’ responsável em fazer a ponte entre o banco de dados e a aplicação,
 * através da classe JpaRepository que possui diversos métodos de CRUD prontos para uso
 * por meio do Hibernate.
 *
 * @author Adriano Santos
 */
@Repository
public interface FuncionarioRepositorio extends JpaRepository<Funcionario, Long> {

    List<Funcionario> findBySetor(FuncionarioSetor funcionarioSetor);
}
