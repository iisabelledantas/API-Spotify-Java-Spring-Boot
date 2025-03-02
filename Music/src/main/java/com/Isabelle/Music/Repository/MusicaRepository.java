package com.Isabelle.Music.Repository;

import com.Isabelle.Music.Modelos.Musica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MusicaRepository extends JpaRepository<Musica, Long> {
    Musica findByTitulo(String titulo);

    @Query("SELECT m FROM Musica m WHERE m.artista ILIKE %:nomeCantor%")
    List<Musica> musicaPorCantor(String nomeCantor);
}

