package com.developer.gerenciadorFuncionarios.controle;

import com.developer.gerenciadorFuncionarios.modelo.Funcionario;
import com.developer.gerenciadorFuncionarios.repositorio.FuncionarioRepositorio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class FuncionarioControle {

    @Autowired
    private FuncionarioRepositorio funcRepository;

    // Acessa o formulario
    @GetMapping("/form")
    public String funcionariosForm(Funcionario funcionario) {

        return "addFuncionariosForm";
    }

    // Adiciona novo funcionario
    @PostMapping("/add")
    public String novo(@Valid Funcionario funcionario, BindingResult result) {

        if (result.hasFieldErrors()) {
            return "redirect:/form";
        }

        funcRepository.save(funcionario);

        return "redirect:/home";
    }

    // Acessa o formulario de edição.
    @GetMapping("form/{id}")
    public String updateForm(Model model, @PathVariable(name = "id") long id) {

        Funcionario funcionario = funcRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("funcionario", funcionario);
        return "atualizaForm";
    }

    // Atualiza funcionario
    @PostMapping("update/{id}")
    public String alterarProduto(@Valid Funcionario funcionario, BindingResult result, @PathVariable int id) {

        if (result.hasErrors()) {
            return "redirect:/form";
        }

        funcRepository.save(funcionario);
        return "redirect:/home";
    }

    @GetMapping("delete/{id}")
    @CacheEvict(value = "funcionarios", allEntries = true)
    public String delete(@PathVariable(name = "id") long id, Model model) {

        Funcionario funcionario = funcRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        funcRepository.delete(funcionario);
        return "redirect:/home";
    }
}
