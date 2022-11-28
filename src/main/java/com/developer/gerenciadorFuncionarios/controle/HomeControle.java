package com.developer.gerenciadorFuncionarios.controle;

import com.developer.gerenciadorFuncionarios.modelo.Funcionario;
import com.developer.gerenciadorFuncionarios.modelo.FuncionarioSetor;
import com.developer.gerenciadorFuncionarios.repositorio.FuncionarioRepositorio;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Locale;

@Controller
public class HomeControle {

    private FuncionarioRepositorio funcionarioRepositorio;

    public HomeControle(FuncionarioRepositorio funcionarioRepositorio) {
        this.funcionarioRepositorio = funcionarioRepositorio;
    }

    @GetMapping("/home")
    public String home(Model model) {

        List<Funcionario> funcionarios = funcionarioRepositorio.findAll();

        model.addAttribute("funcionarios", funcionarios);
        return "home";
    }

    // Método responsável em trazer todos os funcionários por setor.
    @GetMapping
    public String setor(@RequestParam String setor, Model model) {

        FuncionarioSetor funcionarioSetor = FuncionarioSetor.valueOf(setor.toUpperCase());
        List<Funcionario> funcionarios = funcionarioRepositorio.findBySetor(funcionarioSetor);

        model.addAttribute("funcionarios", funcionarios);
        return "home";
    }
}
