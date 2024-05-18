package dev.edvanronchi.springbootautomatedtest.infrastructure.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.edvanronchi.springbootautomatedtest.infrastructure.dtos.EquacaoDto;
import dev.edvanronchi.springbootautomatedtest.infrastructure.dtos.ResultadoBhaskaraDto;
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
class BhaskaraControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveCalcular() throws Exception {
        EquacaoDto equacao = new EquacaoDto(1, 2, -15);
        ResultadoBhaskaraDto resposta = new ResultadoBhaskaraDto(3, -5);

        ResultActions result = mockMvc.perform(post("/bhaskara/calcular")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(equacao)));

        result.andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(resposta)));
    }

    @Test
    void deveRetornarErroQuandoDeltaForNegativo() throws Exception {
        EquacaoDto equacao = new EquacaoDto(1, -2, 4);
        Map<String, String> resposta = new HashMap<>();
        resposta.put("message", "Delta é negativo, a equação não possui raízes reais.");

        ResultActions result = mockMvc.perform(post("/bhaskara/calcular")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(equacao)));

        result.andExpect(status().isUnprocessableEntity())
                .andExpect(content().json(objectMapper.writeValueAsString(resposta)));
    }
}