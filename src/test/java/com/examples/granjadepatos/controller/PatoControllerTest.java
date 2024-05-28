package com.examples.granjadepatos.controller;

import com.granjadepatos.controller.PatoController;
import com.granjadepatos.model.PatoModel;
import com.granjadepatos.service.PatoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(PatoController.class)
public class PatoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatoService patoService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSetPato() throws Exception {
        PatoModel mae = new PatoModel();
        mae.setId(2L);
        mae.setNome("Pata Maria");

        PatoModel pato = new PatoModel();
        pato.setNome("Novo Pato");
        pato.setMae(mae);

        when(patoService.setPato(any(PatoModel.class), any(Long.class))).thenReturn(pato);

        mockMvc.perform(post("/patos")
                        .param("maeId", "2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pato)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Novo Pato"))
                .andExpect(jsonPath("$.mae.nome").value("Pata Maria"));
    }
}