package dev.edvanronchi.springbootautomatedtest.infrastructure.web.controllers;

import dev.edvanronchi.springbootautomatedtest.application.services.BhaskaraService;
import dev.edvanronchi.springbootautomatedtest.domain.entities.Bhaskara;
import dev.edvanronchi.springbootautomatedtest.infrastructure.dtos.EquacaoDto;
import dev.edvanronchi.springbootautomatedtest.infrastructure.dtos.ResultadoBhaskaraDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bhaskara")
public class BhaskaraController {

    private final BhaskaraService bhaskaraService;

    @Autowired
    public BhaskaraController(BhaskaraService bhaskaraService) {
        this.bhaskaraService = bhaskaraService;
    }

    @GetMapping("/calculos")
    public ResponseEntity<Page<Bhaskara>> calculos(@RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "20") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Bhaskara> calculos = bhaskaraService.buscarCalculos(pageable);
        return new ResponseEntity<>(calculos, HttpStatus.OK);
    }

    @PostMapping("/calcular")
    public ResponseEntity<ResultadoBhaskaraDto> calcular(@RequestBody EquacaoDto equacao) {
        ResultadoBhaskaraDto resultado = bhaskaraService.calcular(equacao);
        bhaskaraService.salvarResultado(equacao, resultado);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }
}
