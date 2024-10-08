package com.badmovies.models;

public class Produtor {

    private String nome;
    private int intervalo;

    public Produtor(String nome, double intervalo) {
        this.nome = nome;
        this.intervalo = (int) intervalo;
    }

    public String getNome() {
        return this.nome;
    };

    public int getintervalo() {
        return this.intervalo;
    };
}
