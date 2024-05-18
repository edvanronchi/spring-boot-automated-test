package dev.edvanronchi.springbootautomatedtest.infrastructure.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.edvanronchi.springbootautomatedtest.infrastructure.dtos.CalculoSimplesDto;
import dev.edvanronchi.springbootautomatedtest.infrastructure.dtos.ResultadoCalculoSimplesDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CalculoSimplesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveSomar() throws Exception {
        CalculoSimplesDto calculoSimples = new CalculoSimplesDto(2, 1);
        ResultadoCalculoSimplesDto resposta = new ResultadoCalculoSimplesDto(3);

        ResultActions result = mockMvc.perform(post("/calculo-simples/somar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(calculoSimples)));

        result.andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(resposta)));
    }

    @Test
    void deveSubtrair() throws Exception {
        CalculoSimplesDto calculoSimples = new CalculoSimplesDto(5, 2);
        ResultadoCalculoSimplesDto resposta = new ResultadoCalculoSimplesDto(3);

        ResultActions result = mockMvc.perform(post("/calculo-simples/subtrair")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(calculoSimples)));

        result.andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(resposta)));
    }

    @Test
    void deveMultiplicar() throws Exception {
        CalculoSimplesDto calculoSimples = new CalculoSimplesDto(3, 1);
        ResultadoCalculoSimplesDto resposta = new ResultadoCalculoSimplesDto(3);

        ResultActions result = mockMvc.perform(post("/calculo-simples/multiplicar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(calculoSimples)));

        result.andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(resposta)));
    }

    @Test
    void deveDividir() throws Exception {
        CalculoSimplesDto calculoSimples = new CalculoSimplesDto(6, 2);
        ResultadoCalculoSimplesDto resposta = new ResultadoCalculoSimplesDto(3);

        ResultActions result = mockMvc.perform(post("/calculo-simples/dividir")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(calculoSimples)));

        result.andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(resposta)));
    }

    @Test
    void deveRetornarErroQuandoDivisorForIgualZero() throws Exception {
        CalculoSimplesDto calculoSimples = new CalculoSimplesDto(6, 0);
        Map<String, String> resposta = new HashMap<>();
        resposta.put("message", "Não é possível dividir por zero.");

        ResultActions result = mockMvc.perform(post("/calculo-simples/dividir")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(calculoSimples)));

        result.andExpect(status().isUnprocessableEntity())
                .andExpect(content().json(objectMapper.writeValueAsString(resposta)));
    }
}
