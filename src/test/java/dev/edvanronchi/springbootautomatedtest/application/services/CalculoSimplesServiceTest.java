package dev.edvanronchi.springbootautomatedtest.application.services;

import dev.edvanronchi.springbootautomatedtest.domain.entities.CalculoSimples;
import dev.edvanronchi.springbootautomatedtest.domain.enums.Operacao;
import dev.edvanronchi.springbootautomatedtest.domain.exceptions.DivisaoPorZeroException;
import dev.edvanronchi.springbootautomatedtest.domain.repositories.CalculoSimplesRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class CalculoSimplesServiceTest {

    @InjectMocks
    private CalculoSimplesImplService calculoSimplesService;

    @Mock
    private CalculoSimplesRepository calculoSimplesRepository;

    @Test
    void deveSomar() {
        var resultado = calculoSimplesService.somar(2, 1);
        assertEquals(3, resultado);
    }

    @Test
    void deveDividir() throws DivisaoPorZeroException {
        var resultado = calculoSimplesService.dividir(6, 2);
        assertEquals(3, resultado);
    }

    @Test
    void deveSubtrair() {
        var resultado = calculoSimplesService.subtrair(5, 2);
        assertEquals(3, resultado);
    }

    @Test
    void deveMultiplicar() {
        var resultado = calculoSimplesService.multiplicar(3, 1);
        assertEquals(3, resultado);
    }

    @Test
    void deveLancarUmaExceptionQuandoDivisorForIgualZero() {
        DivisaoPorZeroException exception = assertThrows(DivisaoPorZeroException.class, () -> {
            calculoSimplesService.dividir(10, 0);
        });
        assertEquals("Não é possível dividir por zero.", exception.getMessage());
    }

    @Test
    void deveBuscarCalculos() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<CalculoSimples> calculos = new PageImpl<>(Collections.emptyList());
        when(calculoSimplesRepository.findAll(pageable)).thenReturn(calculos);

        Page<CalculoSimples> result = calculoSimplesService.buscarCalculos(pageable);

        assertEquals(calculos, result);
        verify(calculoSimplesRepository).findAll(pageable);
    }

    @Test
    void deveSalvarResultado() {
        double a = 2;
        double b = 3;
        double resultado = 5;
        Operacao operacao = Operacao.SOMA;

        CalculoSimples calculoSimplesMock = criarCalculoSimples(a, b, resultado, operacao);

        when(calculoSimplesRepository.save(any(CalculoSimples.class))).thenReturn(calculoSimplesMock);

        CalculoSimples calculoSimples = calculoSimplesService.salvarResultado(a, b, resultado, operacao);

        assertEquals(calculoSimplesMock, calculoSimples);
        verify(calculoSimplesRepository).save(any(CalculoSimples.class));
    }

    private CalculoSimples criarCalculoSimples(double a, double b, double resultado, Operacao operacao) {
        return CalculoSimples.builder()
                .a(a)
                .b(b)
                .operacao(operacao)
                .resultado(resultado)
                .build();
    }
}