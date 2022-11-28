package com.developer.gerenciadorFuncionarios.modelo;

public enum FuncionarioSetor {

    TECNOLOGIA("tecnologia"),
    RH("rh"),
    DIRETORIA("diretoria");

    private final String value;

    private FuncionarioSetor(String value) {
        this.value = value;
    }

    public String getSetor() {
        return value;
    }

}
