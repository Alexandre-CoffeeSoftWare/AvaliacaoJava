package com.badmovies.repository;

import com.badmovies.models.Filme;
import java.util.List;

public interface LeituraCsv {
    List<Filme> Executar(String caminhoArquivo);
}
