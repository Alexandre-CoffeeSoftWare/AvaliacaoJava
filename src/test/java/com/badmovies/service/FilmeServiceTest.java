package com.badmovies.service;

import com.badmovies.models.Filme;
import com.badmovies.models.FilmeTeste;
import com.badmovies.models.IntervaloPremio;
import com.badmovies.repository.FilmeRepository;
import com.badmovies.utils.Result;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

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

    @Test
    @Order(4)
    void retornarIntervalosMinEMax_sucesso() {
        List<Filme> filmes = List.of(
                new FilmeTeste(1L, "Produtor 1", "Titulo 1", 1900, true),  // Prêmio em 1900
                new FilmeTeste(2L, "Produtor 1", "Titulo 2", 1999, true),  // Prêmio em 1999
                new FilmeTeste(3L, "Produtor 2", "Titulo 3", 2018, true),  // Prêmio em 2018
                new FilmeTeste(4L, "Produtor 2", "Titulo 4", 2019, true)   // Prêmio em 2019
        );

        when(filmeRepository.findVencedoresOrdenadosPorProdutorEAno()).thenReturn(filmes);
        Map<String, List<IntervaloPremio>> intervalos = filmeService.retornarIntervalosMinEMax();

        assertNotNull(intervalos, "O mapa de intervalos não deve ser nulo.");
        assertNotNull(intervalos.get("min"), "A lista de intervalos mínimos não deve ser nula.");
        assertNotNull(intervalos.get("max"), "A lista de intervalos máximos não deve ser nula.");

        IntervaloPremio minIntervalo = intervalos.get("min").get(0);
        assertEquals("Produtor 2", minIntervalo.getProducer(), "O produtor deve ser 'Produtor 2' para o menor intervalo.");
        assertEquals(1, minIntervalo.getInterval(), "O intervalo deve ser 1 ano para o menor intervalo.");
        assertEquals(2018, minIntervalo.getPreviousWin(), "O ano anterior ao prêmio deve ser 2018.");
        assertEquals(2019, minIntervalo.getFollowingWin(), "O ano seguinte ao prêmio deve ser 2019.");

        IntervaloPremio maxIntervalo = intervalos.get("max").get(0);
        assertEquals("Produtor 1", maxIntervalo.getProducer(), "O produtor deve ser 'Produtor 1' para o maior intervalo.");
        assertEquals(99, maxIntervalo.getInterval(), "O intervalo deve ser 99 anos para o maior intervalo.");
        assertEquals(1900, maxIntervalo.getPreviousWin(), "O ano anterior ao prêmio deve ser 1900.");
        assertEquals(1999, maxIntervalo.getFollowingWin(), "O ano seguinte ao prêmio deve ser 1999.");

        verify(filmeRepository, times(1)).findVencedoresOrdenadosPorProdutorEAno();
    }

}