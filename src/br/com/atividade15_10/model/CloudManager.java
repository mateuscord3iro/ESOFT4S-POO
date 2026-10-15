package br.com.atividade15_10.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Gerenciador principal responsavel pelo cadastro de clientes e mapeamento dos seus respectivos servidores. - RA: 25000304-2
 */
public class CloudManager {
    // Estrutura em Map para associar cada cliente a um conjunto unico de servidores - RA: 25000304-2
    private Map<Cliente, Set<Servidor>> bancoDeDados;

    // Construtor inicializando a estrutura de dados do banco de dados em memoria - RA: 25000304-2
    public CloudManager() {
        this.bancoDeDados = new HashMap<>();
    }

    // Adiciona o cliente ao sistema associando um conjunto vazio de servidores caso ainda nao exista - RA: 25000304-2
    public void registrarCliente(Cliente c) {
        bancoDeDados.putIfAbsent(c, new HashSet<>());
    }

    // Associa um novo servidor ao cliente correspondente garantindo que ele esteja cadastrado - RA: 25000304-2
    public void provisionarServidor(Cliente c, Servidor s) {
        registrarCliente(c);
        bancoDeDados.get(c).add(s);
    }

    // Calcula o valor total da fatura somando o preco por hora apenas dos servidores com status ligado - RA: 25000304-2
    public double calcularFaturaAtiva(Cliente c) {
        Set<Servidor> servidores = bancoDeDados.get(c);
        
        if (servidores == null) {
            return 0.0;
        }

        return servidores.stream()
                .filter(Servidor::isStatusLigado)
                .mapToDouble(Servidor::getPrecoPorHora)
                .sum();
    }
}