package com.Isabelle.Music.Modelos;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

public record DadosMusica( @JsonAlias("track_name") String titulo,
                           @JsonAlias("artist_name") String artista,
                           @JsonAlias("popularity") Integer popularidade){
}
