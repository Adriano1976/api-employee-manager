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

/**
 * Classe responsável em controlar e recuperar as informações da base de dados para
 * enviar ao modelo html home e listar todas as informações ao usuário.
 *
 * @author Adriano Santos
 */
@Controller
public class HomeControle {

    @Autowired
    private FuncionarioRepositorio funcRepository;


    /**
     * Método responsável em controlar a recuperação e visualização das informações do funcionáro
     * da base de dados.
     *
     * @param model Recebe as informações da base de dados.
     * @return Retorna as informações da base de dados e envia a lista do modelo ao html home
     * com os dados dos funcionários.
     */
    @GetMapping("/home")
    public String home(Model model) {
        List<Funcionario> funcionarios = funcRepository.findAll();

        model.addAttribute("funcionarios", funcionarios);
        return "home";
    }


    /**
     * @param setor Recebe as informações do setor da base de dados.
     * @param model Recebe as informações do funcionario da base de dados.
     * @return Retorna as informações da base de dados e envia a lista do modelo ao html home
     * com os dados do funcionário e do setor a qual ele pertence.
     * A anotação {@link RequestParam} indica que será passado o valor dessa variável no seguinte
     * formato: *** <a href="http://localhost:8080/?setor=tecnologia">...</a> ***
     */
    @GetMapping
    public String setor(@RequestParam String setor, Model model) {
        FuncionarioSetor funcionarioSetor = FuncionarioSetor.valueOf(setor.toUpperCase());
        List<Funcionario> funcionarios = funcRepository.findBySetor(funcionarioSetor);

        model.addAttribute("funcionarios", funcionarios);
        return "home";
    }
}
