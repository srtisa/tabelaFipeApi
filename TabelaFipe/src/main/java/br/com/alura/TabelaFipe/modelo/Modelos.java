package br.com.alura.TabelaFipe.modelo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties (ignoreUnknown = true)
public record Modelos (List <DadosDoVeiculo> modelos){
}
