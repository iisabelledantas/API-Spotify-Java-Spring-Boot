package com.Isabelle.Music.Services;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class BuscaArtista {

    public static String BuscaArtistaNome(String nomeArtista, String token) throws Exception {
        String query = nomeArtista.replace(" ", "%20");
        String searchUrl = "https://api.spotify.com/v1/search?q=" + query + "&type=artist&limit=1";

        URL url = new URL(searchUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Authorization", "Bearer " + token);

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            response.append(line);
        }
        br.close();

        JSONObject jsonResponse = new JSONObject(response.toString());
        JSONArray artists = jsonResponse.getJSONObject("artists").getJSONArray("items");

        JSONObject result = new JSONObject();
        if (artists.length() > 0) {
            JSONObject artist = artists.getJSONObject(0);
            result.put("artists_name", artist.getString("name"));

            //result.put("genres", artist.getJSONArray("genres").getJSONObject(0).getString("genres"));

            JSONArray genresArray = artist.getJSONArray("genres");
            if (genresArray.length() > 0) {
                result.put("genres", genresArray.getString(0));
            } else {
                result.put("genres", "Desconhecido");
            }
            result.put("popularity", artist.getInt("popularity"));
        } else {
            result.put("error", "Nenhum artista encontrada.");
        }
        return result.toString();
    }

}
