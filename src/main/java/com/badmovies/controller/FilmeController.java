package com.badmovies.controller;

import com.badmovies.models.Filme;
import com.badmovies.models.IntervaloPremio;
import com.badmovies.models.Produtor;
import com.badmovies.utils.Result;
import com.badmovies.service.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/filmes")
public class FilmeController {

    @Autowired
    private FilmeService filmeService;

    @GetMapping
    public List<Filme> listarFilmes() {
        return filmeService.listarFilmes();
    }

    @GetMapping("/vencedores")
    public List<Filme> listarVencedores() {
        return filmeService.listarVencedores();
    }

    @GetMapping("/filmeporano")
    public List<Filme> listarFilmePorAno(@RequestParam int ano) { return  filmeService.listarFilmePorAno(ano); }

    @GetMapping("/premiadodemaiorintervalo")
    public Produtor retornarPremiadoMaiorIntervalo() { return  filmeService.retornarPremiadoMaiorIntervalo(); }

    @GetMapping("/premiadomenorintervalo")
    public Produtor retornarPremiadoMenorIntervalo() { return  filmeService.retornarPremiadoMenorIntervalo(); }

    @GetMapping("/todosintervalos")
    public Map<String, List<IntervaloPremio>> retornarIntervalosMinEMax() { return filmeService.retornarIntervalosMinEMax(); }

    @PostMapping
    public Filme adicionarFilme(@RequestBody Filme filme) { return filmeService.adicionarFilme(filme); }

    @PutMapping("/{id}")
    public Result atualizarFilme(@PathVariable Long id, @RequestBody Filme filme) { return filmeService.atualizarFilme(id, filme); }

    @DeleteMapping("/{id}")
    public Result excluirFilme(@PathVariable Long id) { return filmeService.excluirFilme(id); }
}
