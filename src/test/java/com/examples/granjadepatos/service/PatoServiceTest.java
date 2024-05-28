package com.examples.granjadepatos.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.granjadepatos.model.PatoModel;
import com.granjadepatos.repository.PatoRepository;
import com.granjadepatos.service.PatoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class PatoServiceTest {

    @Mock
    private PatoRepository patoRepository;

    @InjectMocks
    private PatoService patoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetPato() {
        PatoModel pato = new PatoModel();
        pato.setId(1L);
        pato.setNome("Pato Teste");

        when(patoRepository.findById(1L)).thenReturn(Optional.of(pato));

        PatoModel result = patoService.getPato(1L);

        assertNotNull(result);
        assertEquals("Pato Teste", result.getNome());
        assertEquals(1L, result.getId());
    }

    @Test
    public void testSetPato() {
        PatoModel mae = new PatoModel();
        mae.setId(2L);
        mae.setNome("Pata Maria");

        PatoModel pato = new PatoModel();
        pato.setNome("Novo Pato");

        when(patoRepository.findById(2L)).thenReturn(Optional.of(mae));
        when(patoRepository.save(any(PatoModel.class))).thenReturn(pato);

        PatoModel result = patoService.setPato(pato, 2L);

        assertNotNull(result);
        assertEquals("Novo Pato", result.getNome());
        assertNotNull(result.getMae());
        assertEquals("Pata Maria", result.getMae().getNome());
    }
}
