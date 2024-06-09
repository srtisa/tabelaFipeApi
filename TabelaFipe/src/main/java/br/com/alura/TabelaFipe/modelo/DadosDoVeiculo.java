package br.com.alura.TabelaFipe.modelo;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public record DadosDoVeiculo( @JsonAlias ("codigo")  String codigoDoVeiculo,
                             @JsonAlias ("nome")String nomeDoVeiculo) {
    @Override
    public String toString() {
        return "DadosDoVeiculo [" +
                "Código do Veículo: '" + codigoDoVeiculo + '\'' +
                ", Nome Do Veiculo: '" + nomeDoVeiculo + '\'' +
                ']';
    }
}
