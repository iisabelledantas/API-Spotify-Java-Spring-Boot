package com.Isabelle.Music.Repository;

import com.Isabelle.Music.Modelos.Artista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArtistRepository extends JpaRepository<Artista, Long> {
    Optional<Artista> findByNome(String nome);
}

