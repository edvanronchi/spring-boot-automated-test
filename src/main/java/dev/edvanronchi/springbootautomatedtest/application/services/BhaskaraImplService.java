package dev.edvanronchi.springbootautomatedtest.application.services;

import dev.edvanronchi.springbootautomatedtest.domain.entities.Bhaskara;
import dev.edvanronchi.springbootautomatedtest.domain.exceptions.DeltaNegativoException;
import dev.edvanronchi.springbootautomatedtest.domain.repositories.BhaskaraRepository;
import dev.edvanronchi.springbootautomatedtest.infrastructure.dtos.EquacaoDto;
import dev.edvanronchi.springbootautomatedtest.infrastructure.dtos.ResultadoBhaskaraDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BhaskaraImplService implements BhaskaraService {

    private final BhaskaraRepository bhaskaraRepository;

    @Autowired
    public BhaskaraImplService(BhaskaraRepository bhaskaraRepository) {
        this.bhaskaraRepository = bhaskaraRepository;
    }

    @Override
    public ResultadoBhaskaraDto calcular(EquacaoDto equacao) throws DeltaNegativoException {
        double a = equacao.a();
        double b = equacao.b();
        double c = equacao.c();

        double delta = b * b - 4 * a * c;

        if (delta < 0) {
            throw new DeltaNegativoException("Delta é negativo, a equação não possui raízes reais.");
        }

        double sqrtDelta = Math.sqrt(delta);
        double x1 = (-b + sqrtDelta) / (2 * a);
        double x2 = (-b - sqrtDelta) / (2 * a);

        return new ResultadoBhaskaraDto(x1, x2);
    }

    @Override
    public Page<Bhaskara> buscarCalculos(Pageable pageable) {
        return bhaskaraRepository.findAll(pageable);
    }

    @Override
    public Bhaskara salvarResultado(EquacaoDto equacao, ResultadoBhaskaraDto resultado) {
        Bhaskara bhaskara = Bhaskara.builder()
                .a(equacao.a())
                .b(equacao.b())
                .c(equacao.c())
                .x1(resultado.x1())
                .x2(resultado.x2())
                .build();

        return bhaskaraRepository.save(bhaskara);
    }
}
