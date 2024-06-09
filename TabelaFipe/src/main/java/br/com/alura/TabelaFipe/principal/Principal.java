package br.com.alura.TabelaFipe.principal;

import br.com.alura.TabelaFipe.modelo.DadosDoVeiculo;
import br.com.alura.TabelaFipe.modelo.Modelos;
import br.com.alura.TabelaFipe.modelo.VeiculoFinalizado;
import br.com.alura.TabelaFipe.servico.ConsumoDeApi;
import br.com.alura.TabelaFipe.servico.ConverteDados;

import javax.xml.transform.Source;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    private final String ENDERECO = "https://parallelum.com.br/fipe/api/v1/";
    private final String MARCAS = "/marcas";
    private final String MODELOS = "/modelos";

    Scanner scanner = new Scanner(System.in);
    ConsumoDeApi consumoDeApi = new ConsumoDeApi();


    private ConverteDados converteDados = new ConverteDados();


    public void exibeMenu(){
        System.out.println("""
                Digite um tipo de veículo para busca:
                (1) Carro
                (2) Moto 
                (3) Caminhão 
                 """);
        var buscaDoMenu = scanner.nextLine();
        //menu
        String escolhaDoUsuario = "";
        if (buscaDoMenu.equalsIgnoreCase("1")){
            escolhaDoUsuario = "carros";
        }   else if (buscaDoMenu.equalsIgnoreCase("2")){
            escolhaDoUsuario = "motos";
        }   else {
            escolhaDoUsuario = "caminhoes";
        }
       //Define o endereço
        var enderecoMutavel = ENDERECO + escolhaDoUsuario + MARCAS;

        //Obtem o Json a partir do endereço
        var json = consumoDeApi.obterDados(enderecoMutavel);

        //Trasnforma p Json na classe
        var marca =  converteDados.obterLista(json, DadosDoVeiculo.class);

        //percorre a marca e imprime as marcas disponíveis
        marca.stream()
                .sorted(Comparator.comparing(DadosDoVeiculo::codigoDoVeiculo))
                .forEach(System.out::println);

        //pede o código para buscar o modelo
        System.out.println("Digite o código do seu modelo para a busca: ");
        int buscaDoCodigo = scanner.nextInt();
        enderecoMutavel = enderecoMutavel + "/" + buscaDoCodigo + MODELOS;

        //pega dados dos modelos disponíveis
        json = consumoDeApi.obterDados(enderecoMutavel);

       //mostra os modelos disponíveis
        var modelosLista = converteDados.obterDados(json, Modelos.class);
        System.out.println("\n Modelos dessa Marca: ");
        modelosLista.modelos().stream()
                .sorted(Comparator.comparing(DadosDoVeiculo::codigoDoVeiculo))
                .forEach(System.out::println);

        //busca um modelo e imprime
        System.out.println("\n Agora digite um modelo para buscar: "); //não está pegando o modelo
        var nomeVeiculo = scanner.nextLine();
        List <DadosDoVeiculo> modelosFiltrados = modelosLista.modelos().stream()
                        .filter(m -> m.nomeDoVeiculo().toLowerCase().contains(nomeVeiculo.toLowerCase()))
                                .collect(Collectors.toList());

        System.out.println("\n Modelos filtrados: ");
        modelosFiltrados.forEach(System.out::println);

        //pega o código dos modelos
        System.out.println("\n Digite o código do modelo agora: ");
        var codigoDoModeloParaBusca = scanner.nextLine();

        enderecoMutavel = enderecoMutavel + "/" + codigoDoModeloParaBusca + "/anos";
        json = consumoDeApi.obterDados(enderecoMutavel);


        //lista de carros por ano
        List <DadosDoVeiculo> anos = converteDados.obterLista(json, DadosDoVeiculo.class);
        List <VeiculoFinalizado> veiculos = new ArrayList<>();

        for (int i = 0; i < anos.size(); i++){
            var enderecoAnos = enderecoMutavel + "/" + anos.get(i).codigoDoVeiculo();
            json = consumoDeApi.obterDados(enderecoAnos);
            VeiculoFinalizado veiculoFinalizado = converteDados.obterDados(json, VeiculoFinalizado.class);
            veiculos.add(veiculoFinalizado);

        }

        System.out.println("Todos os veículos filtrados: ");
        veiculos.forEach(System.out::println);

    }
}
