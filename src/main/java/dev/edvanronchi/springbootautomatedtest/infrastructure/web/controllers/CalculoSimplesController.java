package dev.edvanronchi.springbootautomatedtest.infrastructure.web.controllers;

import dev.edvanronchi.springbootautomatedtest.application.services.CalculoSimplesService;
import dev.edvanronchi.springbootautomatedtest.domain.entities.CalculoSimples;
import dev.edvanronchi.springbootautomatedtest.domain.enums.Operacao;
import dev.edvanronchi.springbootautomatedtest.infrastructure.dtos.CalculoSimplesDto;
import dev.edvanronchi.springbootautomatedtest.infrastructure.dtos.ResultadoCalculoSimplesDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/calculo-simples")
public class CalculoSimplesController {

    private final CalculoSimplesService calculoSimplesService;

    @Autowired
    public CalculoSimplesController(CalculoSimplesService calculoSimplesService) {
        this.calculoSimplesService = calculoSimplesService;
    }

    @GetMapping("/calculos")
    public ResponseEntity<Page<CalculoSimples>> calculos(@RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "20") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CalculoSimples> calculos = calculoSimplesService.buscarCalculos(pageable);
        return new ResponseEntity<>(calculos, HttpStatus.OK);
    }

    @PostMapping("/somar")
    public ResponseEntity<ResultadoCalculoSimplesDto> somar(@RequestBody CalculoSimplesDto calculoSimples) {
        double resultado = calculoSimplesService.somar(calculoSimples.a(), calculoSimples.b());
        calculoSimplesService.salvarResultado(calculoSimples.a(), calculoSimples.b(), resultado, Operacao.SOMA);
        ResultadoCalculoSimplesDto dto = new ResultadoCalculoSimplesDto(resultado);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("/subtrair")
    public ResponseEntity<ResultadoCalculoSimplesDto> subtrair(@RequestBody CalculoSimplesDto calculoSimples) {
        double resultado = calculoSimplesService.subtrair(calculoSimples.a(), calculoSimples.b());
        calculoSimplesService.salvarResultado(calculoSimples.a(), calculoSimples.b(), resultado, Operacao.SUBTRACAO);
        ResultadoCalculoSimplesDto dto = new ResultadoCalculoSimplesDto(resultado);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("/dividir")
    public ResponseEntity<ResultadoCalculoSimplesDto> dividir(@RequestBody CalculoSimplesDto calculoSimples) {
        double resultado = calculoSimplesService.dividir(calculoSimples.a(), calculoSimples.b());
        calculoSimplesService.salvarResultado(calculoSimples.a(), calculoSimples.b(), resultado, Operacao.DIVISAO);
        ResultadoCalculoSimplesDto dto = new ResultadoCalculoSimplesDto(resultado);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("/multiplicar")
    public ResponseEntity<ResultadoCalculoSimplesDto> multiplicar(@RequestBody CalculoSimplesDto calculoSimples) {
        double resultado = calculoSimplesService.multiplicar(calculoSimples.a(), calculoSimples.b());
        calculoSimplesService.salvarResultado(calculoSimples.a(), calculoSimples.b(), resultado, Operacao.MULTIPLICACAO);
        ResultadoCalculoSimplesDto dto = new ResultadoCalculoSimplesDto(resultado);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
