package dev.edvanronchi.springbootautomatedtest.application.services;

import dev.edvanronchi.springbootautomatedtest.domain.entities.Bhaskara;
import dev.edvanronchi.springbootautomatedtest.domain.exceptions.DeltaNegativoException;
import dev.edvanronchi.springbootautomatedtest.domain.repositories.BhaskaraRepository;
import dev.edvanronchi.springbootautomatedtest.infrastructure.dtos.EquacaoDto;
import dev.edvanronchi.springbootautomatedtest.infrastructure.dtos.ResultadoBhaskaraDto;
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
class BhaskaraServiceTest {

    @InjectMocks
    private BhaskaraImplService bhaskaraService;

    @Mock
    private BhaskaraRepository bhaskaraRepository;

    @Test
    void deveCalcular() {
        EquacaoDto equacao = new EquacaoDto(1, 2, -15);
        var resultado = bhaskaraService.calcular(equacao);
        assertEquals(new ResultadoBhaskaraDto(3, -5), resultado);
    }

    @Test
    void deveLancarUmaExceptionQuandoDeltaForNegativo() {
        DeltaNegativoException exception = assertThrows(DeltaNegativoException.class, () -> {
            EquacaoDto equacao = new EquacaoDto(1, -2, 4);
            bhaskaraService.calcular(equacao);
        });
        assertEquals("Delta é negativo, a equação não possui raízes reais.", exception.getMessage());
    }

    @Test
    void deveBuscarCalculos() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Bhaskara> calculos = new PageImpl<>(Collections.emptyList());
        when(bhaskaraRepository.findAll(pageable)).thenReturn(calculos);

        Page<Bhaskara> result = bhaskaraService.buscarCalculos(pageable);

        assertEquals(calculos, result);
        verify(bhaskaraRepository).findAll(pageable);
    }

    @Test
    void deveSalvarResultado() {
        EquacaoDto equacaoDto = new EquacaoDto(1, 2, -15);
        ResultadoBhaskaraDto resultado = new ResultadoBhaskaraDto(3, -5);

        Bhaskara bhaskaraMock = criarBhaskara(equacaoDto, resultado);

        when(bhaskaraRepository.save(any(Bhaskara.class))).thenReturn(bhaskaraMock);

        Bhaskara bhaskara = bhaskaraService.salvarResultado(equacaoDto, resultado);

        assertEquals(bhaskaraMock, bhaskara);
        verify(bhaskaraRepository).save(any(Bhaskara.class));
    }

    private Bhaskara criarBhaskara(EquacaoDto equacao, ResultadoBhaskaraDto resultado) {
        return Bhaskara.builder()
                .a(equacao.a())
                .b(equacao.b())
                .c(equacao.c())
                .x1(resultado.x1())
                .x2(resultado.x2())
                .build();
    }
}