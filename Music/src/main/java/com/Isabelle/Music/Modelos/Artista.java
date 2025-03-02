package com.Isabelle.Music.Modelos;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Artista")
public class Artista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String nome;
    private String genero;
    private int popularidade;

    public Artista(DadosArtista dadosArtista) {
        this.nome = dadosArtista.nome();
        this.genero = dadosArtista.genero();
        this.popularidade = dadosArtista.popularidade();
    }

    public Artista(){}

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getPopularidade() {
        return popularidade;
    }

    public void setPopularidade(int popularidade) {
        this.popularidade = popularidade;
    }

    @Override
    public String toString() {
        return "\n" + " ** Artista **" +
                "\n" + "Nome: " + nome +
                "\n" + "Genero: " + genero +
                "\n" + "Popularidade: " + popularidade;
    }

}


