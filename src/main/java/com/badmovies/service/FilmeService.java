package com.badmovies.service;

import com.badmovies.models.Filme;
import com.badmovies.models.Produtor;
import com.badmovies.models.IntervaloPremio;
import com.badmovies.utils.Result;
import com.badmovies.repository.FilmeRepository;
import com.badmovies.repository.LeituraCsv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import jakarta.annotation.PostConstruct;

import java.util.List;
import java.util.Map;

@Service
public class FilmeService {

    private final FilmeRepository filmeRepository;
    private final LeituraCsv leituraCsv;
    private final FilmeUtilsService filmeUtilsService;

    @Value("${filme.csv.path}")
    private String caminhoArquivoCsv;

    @Autowired
    public FilmeService(FilmeRepository filmeRepository, LeituraCsv leituraCsv) {
        this.filmeRepository = filmeRepository;
        this.leituraCsv = leituraCsv;
        this.filmeUtilsService = new FilmeUtilsService(filmeRepository);
    }

    @PostConstruct
    public void carregar() {
        List<Filme> filmes = leituraCsv.Executar(caminhoArquivoCsv);
        for (Filme filme : filmes) {
            filmeRepository.save(filme);
        }
    }

    public List<Filme> listarFilmes() {
        return filmeRepository.findAll();
    }

    public List<Filme> listarVencedores() { return filmeRepository.findByVencedor(true); }

    public Produtor retornarPremiadoMaiorIntervalo() { return filmeUtilsService.retornarPremiadoMaiorIntervalo(); }

    public Produtor retornarPremiadoMenorIntervalo() { return filmeUtilsService.retornarPremiadoMenorIntervalo(); }

    public Map<String, List<IntervaloPremio>> retornarIntervalosMinEMax() { return filmeUtilsService.retornarIntervalosMinEMax(); }

    public List<Filme> listarFilmePorAno(int ano) {
        return filmeRepository.findByAno(ano);
    }

    public Filme adicionarFilme(Filme filme) {
        return filmeRepository.save(filme);
    }

    public Result atualizarFilme(Long id, Filme filmeAtualizado) { return filmeUtilsService.atualizarSalvarFilme(id, filmeAtualizado); }

    public Result excluirFilme(Long id) { return filmeUtilsService.excluirFilme(id); }
}
