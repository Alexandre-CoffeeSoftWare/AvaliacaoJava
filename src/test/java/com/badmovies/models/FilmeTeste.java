package com.badmovies.models;

public class FilmeTeste extends Filme {
    public FilmeTeste(Long id, String produtor, String titulo, int ano, Boolean vencedor) {
      this.setId(id);
      this.setAno(ano);
      this.setTitulo(titulo);
      this.setProdutor(produtor);
      this.setVencedor(vencedor);
    }
}
