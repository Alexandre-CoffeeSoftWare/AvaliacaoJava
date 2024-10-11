package com.badmovies.repository;

import com.badmovies.models.Filme;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class LeituraCsvBufferReader implements LeituraCsv {

    @Override
    public List<Filme> Executar(String caminhoArquivo) {
        List<Filme> filmes = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(getClass().getResourceAsStream(caminhoArquivo)), StandardCharsets.UTF_8))) {

            String linha = reader.readLine();
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";", 5);
                var produtores = dados[3].replace(" and ",",").split(",");
                for(var produtor: produtores) {
                    Filme filme = new Filme();
                    filme.setAno(Integer.parseInt(dados[0]));
                    filme.setTitulo(dados[1]);
                    filme.setEstudio(dados[2]);
                    filme.setProdutor(produtor.trim());
                    filme.setVencedor(Boolean.parseBoolean(String.valueOf(dados[4].equals("yes"))));
                    filmes.add(filme);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filmes;
    }
}
