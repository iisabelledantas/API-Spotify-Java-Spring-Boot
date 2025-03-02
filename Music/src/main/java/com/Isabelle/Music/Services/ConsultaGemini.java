package com.Isabelle.Music.Services;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;

public class ConsultaGemini {

    public static String pesquisa(String texto) {
        ChatLanguageModel gemini = GoogleAiGeminiChatModel.builder()
                .apiKey(System.getenv("GEMINI_KEY"))
                .modelName("gemini-1.5-flash")
                .build();

        String response = gemini.generate("Forneça uma breve descrição sobre o seguinte artista: " + texto);
        return response;
    }
}
