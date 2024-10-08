package com.badmovies.repository;

import com.badmovies.models.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long> {
    List<Filme> findByVencedor(boolean vencedor);
    List<Filme> findByAno(int ano);
    @Query("SELECT f FROM Filme f WHERE f.vencedor = true ORDER BY f.produtor, f.ano")
    List<Filme> findVencedoresOrdenadosPorProdutorEAno();
}

