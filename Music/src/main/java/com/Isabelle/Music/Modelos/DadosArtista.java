package com.Isabelle.Music.Modelos;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DadosArtista(@JsonAlias("artists_name") String nome,
                           @JsonAlias("genres") String genero,
                           @JsonAlias("popularity") Integer popularidade){
}
