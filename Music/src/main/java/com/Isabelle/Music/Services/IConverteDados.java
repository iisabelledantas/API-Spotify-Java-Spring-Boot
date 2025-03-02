package com.Isabelle.Music.Services;

public interface IConverteDados {
    <T> T obterDadosConversao(String json, Class<T> classe);
}