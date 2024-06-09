package br.com.alura.TabelaFipe.modelo;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties (ignoreUnknown = true)
public record VeiculoFinalizado(@JsonAlias ("Valor") String valor,
                                @JsonAlias ("Marca") String marca,
                                @JsonAlias ("Modelo") String modelo,
                                @JsonAlias ("MesReferencia") String mesReferencia,
                                @JsonAlias ("CodigoFipe") String codigoFipe) {
}
