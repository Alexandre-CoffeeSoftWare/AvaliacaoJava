package com.badmovies.service;

import com.badmovies.models.*;
import com.badmovies.utils.Result;
import com.badmovies.repository.FilmeRepository;
import org.springframework.beans.BeanUtils;

import java.util.*;

public class FilmeUtilsService {

    private FilmeRepository filmeRepository;

    public FilmeUtilsService(FilmeRepository filmeRepository) {
        this.filmeRepository = filmeRepository;
    }

    public Result atualizarSalvarFilme(Long id, Filme filmeAtualizado) {
        Optional<Filme> filmeOptional = filmeRepository.findById(id);
        if (filmeOptional.isPresent()) {
            Filme filmeExistente = filmeOptional.get();
            BeanUtils.copyProperties(filmeAtualizado, filmeExistente, "id");
            filmeRepository.save(filmeExistente);
            return new Result("OK", "Filme alterado com sucesso!");
        } else {
            return new Result("Erro", "Filme não encontrado");
        }
    }

    public Result excluirFilme(Long id) {
        return filmeRepository.findById(id)
            .map(filme -> {
                filmeRepository.delete(filme);
                    return new Result("OK", "Filme excluído com sucesso!");
                })
                .orElse(new Result("Erro", "O Filme já foi excluído ou não encontrado."));
    }

    public Produtor retornarPremiadoMaiorIntervalo() {
        List<Filme> filmes = filmeRepository.findVencedoresOrdenadosPorProdutorEAno();
        Map<String, Integer> ultimoAnoPorProdutor = new HashMap<>();
        String produtorMaiorIntervalo = "";
        int maiorIntervalo = 0;

        for (Filme filme : filmes) {
            String produtor = filme.getProdutor();
            int anoAtual = filme.getAno();

            if (ultimoAnoPorProdutor.containsKey(produtor)) {
                int anoAnterior = ultimoAnoPorProdutor.get(produtor);
                int intervaloAtual = anoAtual - anoAnterior;

                if (intervaloAtual > maiorIntervalo) {
                    maiorIntervalo = intervaloAtual;
                    produtorMaiorIntervalo = produtor;
                }
            }
            ultimoAnoPorProdutor.put(produtor, anoAtual);
        }
        return new Produtor(produtorMaiorIntervalo, maiorIntervalo);
    }


    public Produtor retornarPremiadoMenorIntervalo() {
        List<Filme> filmes = filmeRepository.findVencedoresOrdenadosPorProdutorEAno();
        Map<String, Integer> ultimoAnoPorProdutor = new HashMap<>();
        String produtorMenorIntervalo = "";
        int menorIntervalo = Integer.MAX_VALUE;

        for (Filme filme : filmes) {
            String produtor = filme.getProdutor();
            int anoAtual = filme.getAno();

            if (ultimoAnoPorProdutor.containsKey(produtor)) {
                int anoAnterior = ultimoAnoPorProdutor.get(produtor);
                int intervaloAtual = anoAtual - anoAnterior;

                if (intervaloAtual < menorIntervalo) {
                    menorIntervalo = intervaloAtual;
                    produtorMenorIntervalo = produtor;
                }
            }
            ultimoAnoPorProdutor.put(produtor, anoAtual);
        }
        if (!produtorMenorIntervalo.isEmpty()) {
            return new Produtor(produtorMenorIntervalo, menorIntervalo);
        } else {
            return null;
        }
    }

    public Map<String, List<IntervaloPremio>> retornarIntervalosMinEMax() {
        List<Filme> filmes = filmeRepository.findVencedoresOrdenadosPorProdutorEAno();
        Map<String, List<IntervaloPremio>> resultado = new HashMap<>();
        List<IntervaloPremio> intervalosMin = new ArrayList<>();
        List<IntervaloPremio> intervalosMax = new ArrayList<>();
        Map<String, Integer> ultimoAnoPremiado = new HashMap<>();

        int menorIntervalo = Integer.MAX_VALUE;
        int maiorIntervalo = Integer.MIN_VALUE;

        for (Filme filme : filmes) {
            String produtor = filme.getProdutor();
            int anoAtual = filme.getAno();

            if (ultimoAnoPremiado.containsKey(produtor)) {
                int anoAnterior = ultimoAnoPremiado.get(produtor);
                int intervalo = anoAtual - anoAnterior;

                if (intervalo < menorIntervalo) {
                    intervalosMin.clear();
                    intervalosMin.add(new IntervaloPremio(produtor, intervalo, anoAnterior, anoAtual));
                    menorIntervalo = intervalo;
                } else if (intervalo == menorIntervalo) {
                    intervalosMin.add(new IntervaloPremio(produtor, intervalo, anoAnterior, anoAtual));
                }

                if (intervalo > maiorIntervalo) {
                    intervalosMax.clear();
                    intervalosMax.add(new IntervaloPremio(produtor, intervalo, anoAnterior, anoAtual));
                    maiorIntervalo = intervalo;
                } else if (intervalo == maiorIntervalo) {
                    intervalosMax.add(new IntervaloPremio(produtor, intervalo, anoAnterior, anoAtual));
                }
            }

            ultimoAnoPremiado.put(produtor, anoAtual);
        }

        resultado.put("min", intervalosMin);
        resultado.put("max", intervalosMax);
        return resultado;
    }
}
