package br.com.alura.TabelaFipe.servico;

import java.util.List;

public interface IConverteDados {
    <T> T obterDados (String json, Class<T> classe);
    //converte listas
    <T> List<T> obterLista (String json, Class <T> classe);
}
