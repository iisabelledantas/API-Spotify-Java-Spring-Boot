package com.Isabelle.Music;

import com.Isabelle.Music.Modelos.DadosMusica;
import com.Isabelle.Music.Modelos.Musica;
import com.Isabelle.Music.Principal.Principal;
import com.Isabelle.Music.Repository.ArtistRepository;
import com.Isabelle.Music.Repository.MusicaRepository;
import com.Isabelle.Music.Services.ObterToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MusicApplication implements CommandLineRunner {

	@Autowired
	private MusicaRepository musicaRepository;

	@Autowired
	private ArtistRepository artistaRepository;

	public static void main(String[] args) {
		SpringApplication.run(MusicApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		System.out.println("Hello word!");

		Principal principal = new Principal(musicaRepository, artistaRepository);

		principal.inicializador();

	}

}
