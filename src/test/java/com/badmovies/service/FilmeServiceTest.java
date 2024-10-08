package com.badmovies.service;

import com.badmovies.models.Filme;
import com.badmovies.repository.FilmeRepository;
import com.badmovies.utils.Result;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class FilmeServiceTest {

    @Mock
    private FilmeRepository filmeRepository;

    @InjectMocks
    private FilmeService filmeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @Order(1)
    void incluirFilme_sucesso() {
        Filme filme = new Filme();
        filme.setId(206L);
        filme.setTitulo("Filme teste");
        filmeRepository.save(filme);
        assertNotNull(filme.getId(), "O ID do filme não deveria ser nulo após salvar.");
        assertEquals("Filme teste", filme.getTitulo(), "O título do filme salvo está incorreto.");
    }

    @Test
    @Order(2)
    void excluirFilme_sucesso() {
        Long id = 208L;
        Filme filme = new Filme();
        when(filmeRepository.findById(id)).thenReturn(Optional.of(filme));
        Result result = filmeService.excluirFilme(id);
        assertEquals("OK", result.getStatus());
        assertEquals("Filme excluído com sucesso!", result.getMessage());
        verify(filmeRepository, times(1)).delete(filme);
    }

    @Test
    @Order(3)
    void excluirFilme_naoEncontrado() {
        Long id = 207L;
        when(filmeRepository.findById(id)).thenReturn(Optional.empty());
        Result result = filmeService.excluirFilme(id);
        assertEquals("Erro", result.getStatus());
        assertEquals("O Filme já foi excluído ou não encontrado.", result.getMessage());
        verify(filmeRepository, never()).deleteById(id);
    }
}