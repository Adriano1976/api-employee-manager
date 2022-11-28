package com.developer.gerenciadorFuncionarios.controle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeControle {

    @GetMapping("/home")
    public String home() {
        return "home";
    }
}
