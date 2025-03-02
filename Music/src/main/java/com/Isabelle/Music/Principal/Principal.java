package com.Isabelle.Music.Principal;

import com.Isabelle.Music.Modelos.Artista;
import com.Isabelle.Music.Modelos.DadosArtista;
import com.Isabelle.Music.Modelos.DadosMusica;
import com.Isabelle.Music.Modelos.Musica;
import com.Isabelle.Music.Repository.ArtistRepository;
import com.Isabelle.Music.Repository.MusicaRepository;
import com.Isabelle.Music.Services.BuscaArtista;
import com.Isabelle.Music.Services.ConsultaGemini;
import com.Isabelle.Music.Services.ConverteDados;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

import static com.Isabelle.Music.Services.BuscaArtista.BuscaArtistaNome;
import static com.Isabelle.Music.Services.BuscaMusica.BuscaMusicaNome;
import static com.Isabelle.Music.Services.ObterToken.getAccessToken;

public class Principal {

    private Scanner reader = new Scanner(System.in);
    private ConverteDados converteDados= new ConverteDados();
    private ConsultaGemini gemini = new ConsultaGemini();

    private List<DadosMusica> dadosMusicas = new ArrayList<>();
    private List<DadosArtista> dadosArtistas = new ArrayList<>();

    private List<Musica> musicas = new ArrayList<>();
    private List<Artista> artistas = new ArrayList<>();

    private MusicaRepository MusicaRepository;
    private ArtistRepository ArtistRepository;

    public Principal(MusicaRepository MusicaRepository, ArtistRepository ArtistRepository) {
        this.MusicaRepository = MusicaRepository;
        this.ArtistRepository = ArtistRepository;
    }

    public void inicializador(){
        var opcao = -1;
        while (opcao != 0){
            String menu = """
                \n
                1 - Buscar musica
                2 - Listar musicas
                3 - Listar artistas
                4 - Buscar musicas por artista
                5 - Buscar dados sobre o artista
                0 - Sair     
                \n                            
                """;

            System.out.println(menu);
            opcao = reader.nextInt();
            reader.nextLine();

            switch (opcao) {
                case 1:
                    buscaMusica();
                    break;
                case 2:
                    listaMusica();
                    break;
                case 3:
                    listaArtista();
                    break;
                case 4:
                    buscaMusicaPorArtista();
                    break;
                case 5:
                    buscaArtistaGemini();
            }
        }
    }

    private void buscaArtistaGemini() {

        System.out.println("Qual artista deseja pesquisar na IA Gemini");
        String artistaPesquisar = reader.nextLine();

        String descricao = gemini.pesquisa(artistaPesquisar);
        System.out.println(descricao);
    }

    private void buscaMusicaPorArtista() {
        System.out.println("Desaja pesquisa a musica de qual cantor?");
        String nomeArtista = reader.nextLine();

        List<Musica> musicaArtista = MusicaRepository.musicaPorCantor(nomeArtista);

        if (musicaArtista.isEmpty()) {
            System.out.println("Nenhuma música encontrada para o artista " + nomeArtista);
        } else {
            musicaArtista.stream().forEach(System.out::println);
        }


    }

    private void listaArtista() {
        artistas = ArtistRepository.findAll();
        artistas.stream()
                .sorted(Comparator.comparing(Artista::getPopularidade).reversed())
                .forEach(System.out::println);
    }

    private void listaMusica() {
        musicas = MusicaRepository.findAll();
        musicas.stream()
                .sorted(Comparator.comparing(Musica::getPopularidade).reversed())
                .forEach(System.out::println);
    }

    private void buscaArtista(String nome) {

        String nomeArtista = nome;

        try {

            String token = getAccessToken();
            String dadosArtistaJson = BuscaArtistaNome(nomeArtista, token);

            // Converter JSON para DadosMusica
            DadosArtista dados = converteDados.obterDadosConversao(dadosArtistaJson, DadosArtista.class);

            Optional<Artista> artistaExistente = ArtistRepository.findByNome(nome);

            if (artistaExistente.isPresent()) {
                System.out.println("Artista já cadastrado!");

            } else {
                // Converter DadosMusica para Musica
                Artista artista = new Artista(dados);
                ArtistRepository.save(artista);
                System.out.println(artista.toString());
                System.out.println("Artista salvo no banco com sucesso!");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void buscaMusica() {

        System.out.println("Qual musica deseja buscar?");
        String nomeMusica = reader.nextLine();

        try {

            String token = getAccessToken();
            String dadosMusicaJson = BuscaMusicaNome(nomeMusica, token);

            // Converter JSON para DadosMusica
            DadosMusica dados = converteDados.obterDadosConversao(dadosMusicaJson, DadosMusica.class);

            // Converter DadosMusica para Musica
            Musica musica = new Musica(dados);

            String nomeArtista = musica.getArtista();

            buscaArtista(nomeArtista);

            // Salvar no banco de dados
            MusicaRepository.save(musica);

            System.out.println(musica.toString());
            System.out.println("Música salva no banco com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
