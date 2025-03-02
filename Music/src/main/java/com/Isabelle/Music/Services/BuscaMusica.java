package com.Isabelle.Music.Services;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class BuscaMusica {

    public static String BuscaMusicaNome(String nomeMusica, String token) throws Exception {
        String query = nomeMusica.replace(" ", "%20");
        String searchUrl = "https://api.spotify.com/v1/search?q=" + query + "&type=track&limit=1";

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
        JSONArray tracks = jsonResponse.getJSONObject("tracks").getJSONArray("items");

        JSONObject result = new JSONObject();
        if (tracks.length() > 0) {
            JSONObject track = tracks.getJSONObject(0);
            result.put("track_name", track.getString("name"));
            result.put("artist_name", track.getJSONArray("artists").getJSONObject(0).getString("name"));
            result.put("popularity", track.getInt("popularity"));
        } else {
            result.put("error", "Nenhuma música encontrada.");
        }
        return result.toString();
    }

}
