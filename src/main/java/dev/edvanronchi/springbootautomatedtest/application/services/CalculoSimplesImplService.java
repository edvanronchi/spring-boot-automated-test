package dev.edvanronchi.springbootautomatedtest.application.services;

import dev.edvanronchi.springbootautomatedtest.domain.entities.CalculoSimples;
import dev.edvanronchi.springbootautomatedtest.domain.enums.Operacao;
import dev.edvanronchi.springbootautomatedtest.domain.exceptions.DivisaoPorZeroException;
import dev.edvanronchi.springbootautomatedtest.domain.repositories.CalculoSimplesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CalculoSimplesImplService implements CalculoSimplesService {

    private final CalculoSimplesRepository calculoSimplesRepository;

    @Autowired
    public CalculoSimplesImplService(CalculoSimplesRepository calculoSimplesRepository) {
        this.calculoSimplesRepository = calculoSimplesRepository;
    }

    @Override
    public double somar(double a, double b) {
        return a + b;
    }

    @Override
    public double subtrair(double a, double b) {
        return a - b;
    }

    @Override
    public double dividir(double a, double b) throws DivisaoPorZeroException {
        if (b == 0) {
            throw new DivisaoPorZeroException("Não é possível dividir por zero.");
        }
        return a / b;
    }

    @Override
    public double multiplicar(double a, double b) {
        return a * b;
    }

    @Override
    public Page<CalculoSimples> buscarCalculos(Pageable pageable) {
        return calculoSimplesRepository.findAll(pageable);
    }

    @Override
    public CalculoSimples salvarResultado(double a, double b, double resultado, Operacao operacao) {
        CalculoSimples calculoSimples = CalculoSimples.builder()
                .a(a)
                .b(b)
                .resultado(resultado)
                .operacao(operacao)
                .build();

        return calculoSimplesRepository.save(calculoSimples);
    }
}
