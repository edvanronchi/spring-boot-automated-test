package dev.edvanronchi.springbootautomatedtest.application.services;

import dev.edvanronchi.springbootautomatedtest.domain.entities.CalculoSimples;
import dev.edvanronchi.springbootautomatedtest.domain.enums.Operacao;
import dev.edvanronchi.springbootautomatedtest.domain.exceptions.DivisaoPorZeroException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public interface CalculoSimplesService {
    double somar(double a, double b);
    double dividir(double a, double b) throws DivisaoPorZeroException;
    double subtrair(double a, double b);
    double multiplicar(double a, double b);

    Page<CalculoSimples> buscarCalculos(Pageable pageable);
    CalculoSimples salvarResultado(double a, double b, double resultado, Operacao operacao);
}
