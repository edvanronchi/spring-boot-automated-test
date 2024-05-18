package dev.edvanronchi.springbootautomatedtest.application.services;

import dev.edvanronchi.springbootautomatedtest.domain.entities.Bhaskara;
import dev.edvanronchi.springbootautomatedtest.domain.exceptions.DeltaNegativoException;
import dev.edvanronchi.springbootautomatedtest.infrastructure.dtos.EquacaoDto;
import dev.edvanronchi.springbootautomatedtest.infrastructure.dtos.ResultadoBhaskaraDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public interface BhaskaraService {
    ResultadoBhaskaraDto calcular(EquacaoDto equacao) throws DeltaNegativoException;
    Page<Bhaskara> buscarCalculos(Pageable pageable);
    Bhaskara salvarResultado(EquacaoDto equacao, ResultadoBhaskaraDto resultado);
}

