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

/**
 * Classe responsável em controlar os formularios e a manipulação dos dados.
 *
 * @author Adriano Santos
 */
@Controller
public class FuncionarioControle {

    /**
     * Objeto responsável pela manipulação das informações entre a base de dados e o formulário.
     */
    @Autowired
    private FuncionarioRepositorio funcRepository;

    /**
     * Método responsável pelo controle de aceso ao formulário.
     *
     * @param funcionario — Recebe os dados do funcionário.
     * @return Retorna um novo formulário.
     */
    @GetMapping("/form")
    public String funcionariosForm(Funcionario funcionario) {

        return "addFuncionariosForm";
    }

    /**
     * Método responsável em controlar a entrada de informações na base de dados.
     *
     * @param funcionario  Recebe as novas informações a ser inserido na base de dados.
     * @param result Recebe o status de validação dos campos do formulário.
     * @return Retorna um novo formulário se as informações inseridas forem validadas.
     */
    @PostMapping("/add")
    public String novo(@Valid Funcionario funcionario, BindingResult result) {

        if (result.hasFieldErrors()) {
            return "redirect:/form";
        }

        funcRepository.save(funcionario);

        return "redirect:/home";
    }

    /**
     * Método responsável em controlar o acesso ao formulário de edição as informações do funcionario.
     *
     * @param model Recebe as informações do formulário a ser editado.
     * @param id Recebe o número de identificação do funcionario a ser editado.
     * @return Retorna um novo formulário com as informações editadas.
     */
    @GetMapping("form/{id}")
    public String updateForm(Model model, @PathVariable(name = "id") long id) {

        Funcionario funcionario = funcRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("funcionario", funcionario);
        return "atualizaForm";
    }

    /**
     * Método responsável em controlar a atualização das informações do funcionario.
     *
     * @param funcionario Recebe as informações do funcionario a ser atualizado.
     * @param result Recebe uma mensagem de erro e redireciona ao formulario vazio se houver erro.
     * @param id Recebe o código de identificação do funcionario.
     * @return Retorna a statos de validação e redireciona o formulário para a página principal.
     */
    @PostMapping("update/{id}")
    public String alterarProduto(@Valid Funcionario funcionario, BindingResult result, @PathVariable int id) {

        if (result.hasErrors()) {
            return "redirect:/form";
        }

        funcRepository.save(funcionario);
        return "redirect:/home";
    }

    /**
     * Método responsável em controlar a remoção dos dados de um funcionário.
     *
     * @param id Recebe o código de identificação do funcionário.
     * @param model Recebe as informações do funcionario.
     * @return Retorna o resultado da validação da ação requerida e redireciona o formulário para a página principal.
     */
    @GetMapping("delete/{id}")
    @CacheEvict(value = "funcionarios", allEntries = true)
    public String delete(@PathVariable(name = "id") long id, Model model) {

        Funcionario funcionario = funcRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        funcRepository.delete(funcionario);
        return "redirect:/home";
    }
}
