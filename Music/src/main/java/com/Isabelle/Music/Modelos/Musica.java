package com.Isabelle.Music.Modelos;
import jakarta.persistence.*;
import java.util.OptionalDouble;

@Entity
@Table(name = "Musica")
public class Musica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(unique = true)
    private String titulo;
    private String artista;
    private int popularidade;

    public Musica( ) {
    }

    public Musica(DadosMusica dadosMusica) {
        this.titulo = dadosMusica.titulo();
        this.artista = dadosMusica.artista();
        this.popularidade = dadosMusica.popularidade();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public int getPopularidade() {
        return popularidade;
    }

    public void setPopularidade(int popularidade) {
        this.popularidade = popularidade;
    }

    @Override
    public String toString() {
        return "\n" + "** Musica **" + "\n" +
                "Nome: " + titulo + "\n" +
                "Popularidade (0/100): " + popularidade + "\n";
    }

}


