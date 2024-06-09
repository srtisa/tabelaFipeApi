package br.com.alura.TabelaFipe.modelo;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties (ignoreUnknown = true)
public record VeiculoFinalizado(@JsonAlias ("Valor") String valor,
                                @JsonAlias ("Marca") String marca,
                                @JsonAlias ("Modelo") String modelo,
                                @JsonAlias ("MesReferencia") String mesReferencia,
                                @JsonAlias ("CodigoFipe") String codigoFipe) {
    @Override
    public String toString() {
        return "O seu veículo: [" +
                "Preço na Tabela: '" + valor + '\'' +
                ", Marca: '" + marca + '\'' +
                ", Modelo: '" + modelo + '\'' +
                ", Mês de Referência: '" + mesReferencia + '\'' +
                ", Código na Tabela Fipe: '" + codigoFipe + '\'' +
                ']';
    }
}
