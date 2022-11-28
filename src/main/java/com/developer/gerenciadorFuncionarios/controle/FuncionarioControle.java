package com.developer.gerenciadorFuncionarios.controle;

import com.developer.gerenciadorFuncionarios.modelo.Funcionario;
import com.developer.gerenciadorFuncionarios.repositorio.FuncionarioRepositorio;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FuncionarioControle {

    FuncionarioRepositorio funcionarioRepositorio;

    public FuncionarioControle(FuncionarioRepositorio funcionarioRepositorio) {
        this.funcionarioRepositorio = funcionarioRepositorio;
    }

    // Método responsável por acessar o formulario.
    @GetMapping("/form")
    public String funcionariosForm(Funcionario funcionario) {
        return "addFuncionariosForm";
    }

    // Método responsável por adicionar um novo funcionario.
    @PostMapping("/add")
    public String novo(@Valid Funcionario funcionario, BindingResult result) {

        if(result.hasFieldErrors()) {
            return "redirect:/form";
        }

        funcionarioRepositorio.save(funcionario);
        return "redirect:/home";
    }

    // Método responsável por acessar o formulário de edição dos dados do funcionário.
    @GetMapping("form/{id}")
    public String updateForm(Model model, @PathVariable(name = "id") Long id) {

        Funcionario funcionario = funcionarioRepositorio
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id: " + id));

        model.addAttribute("funcionario", funcionario);
        return "atualizaForm";
    }

    // Método responsável por atualizar os dados do funcionário.
    @PostMapping("update/{id}")
    public String alterarFuncionario(@Valid Funcionario funcionario, BindingResult result, @PathVariable Long id) {

        if(result.hasErrors()) {
            return "redirect:/form";
        }

        funcionarioRepositorio.save(funcionario);
        return "redirect:/home";
    }

    // Método responsável por apagar os dados do funcionário.
    @GetMapping("delete/{id}")
    public String delete(@PathVariable(name = "id") Long id, Model model) {

        Funcionario funcionario = funcionarioRepositorio
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id: " + id));

        funcionarioRepositorio.delete(funcionario);
        return "redirect:/home";
    }
}
