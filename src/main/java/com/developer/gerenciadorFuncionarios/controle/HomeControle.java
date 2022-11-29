package com.developer.gerenciadorFuncionarios.controle;

import com.developer.gerenciadorFuncionarios.modelo.Funcionario;
import com.developer.gerenciadorFuncionarios.modelo.FuncionarioSetor;
import com.developer.gerenciadorFuncionarios.repositorio.FuncionarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeControle {

    @Autowired
    private FuncionarioRepositorio funcRepository;



    @GetMapping("/home")
    public String home(Model model) {
        List<Funcionario> funcionarios = funcRepository.findAll();

        model.addAttribute("funcionarios", funcionarios);
        return "home";
    }


    @GetMapping
    public String setor(@RequestParam String setor, Model model) {
        FuncionarioSetor funcionarioSetor = FuncionarioSetor.valueOf(setor.toUpperCase());
        List<Funcionario> funcionarios = funcRepository.findBySetor(funcionarioSetor);

        model.addAttribute("funcionarios", funcionarios);
        return "home";
    }
}
