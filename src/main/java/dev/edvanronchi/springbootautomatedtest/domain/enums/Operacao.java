package dev.edvanronchi.springbootautomatedtest.domain.enums;

import lombok.Getter;

@Getter
public enum Operacao {

    MULTIPLICACAO("Multiplicação"),
    DIVISAO("Divisão"),
    SOMA("Soma"),
    SUBTRACAO("Subtração");

    private final String descricao;

    Operacao(String descricao) {
        this.descricao = descricao;
    }
}
