package br.com.atividade15_10.main;

import br.com.atividade15_10.model.Cliente;
import br.com.atividade15_10.model.CloudManager;
import br.com.atividade15_10.model.Container;
import br.com.atividade15_10.model.Servidor;
import br.com.atividade15_10.exception.CapacidadeExcedidaException;

/**
 * Classe executável MainCloud responsável por rodar o fluxo principal de testes da aplicação. - RA: 25000304-2
 */
public class MainCloud {
    public static void main(String[] args) {
        // Instancia o gerenciador de recursos em nuvem - RA: 25000304-2
        CloudManager manager = new CloudManager();

        // Instancia o cliente para teste - RA: 25000304-2
        Cliente cliente = new Cliente("111.222.333-44", "Wayne Enterprises");

        // Instancia o servidor virtual com IP, limite de RAM e preço por hora - RA: 25000304-2
        Servidor servidor = new Servidor("192.168.0.1", 16.0, 5.50);

        // Registra o cliente e vincula o servidor provisionado a ele - RA: 25000304-2
        manager.registrarCliente(cliente);
        manager.provisionarServidor(cliente, servidor);

        // Instancia os dois containers com demandas de memória distintas - RA: 25000304-2
        Container c1 = new Container("C-01", "App-Web", 10.0);
        Container c2 = new Container("C-02", "App-API", 8.0);

        // Teste de exceção Unchecked: tentar alocar com o servidor desligado lança ServidorOfflineException - RA: 25000304-2
        // servidor.alocarContainer(c1);

        // Liga o servidor para prosseguir com os testes de alocação - RA: 25000304-2
        servidor.ligar();

        try {
            // Aloca o primeiro container dentro da capacidade permitida - RA: 25000304-2
            servidor.alocarContainer(c1);
            System.out.println("Container 1 alocado com sucesso!");

            // Teste de exceção Checked: tentar alocar C2 (8.0 GB) estoura os 16.0 GB totais (10.0 + 8.0 = 18.0 GB) - RA: 25000304-2
            servidor.alocarContainer(c2);
            
        } catch (CapacidadeExcedidaException e) {
            // Trata e exibe a mensagem amigável de erro no console - RA: 25000304-2
            System.err.println("Erro capturado com elegância: " + e.getMessage());
        }

        // Calcula e exibe o valor da fatura para os servidores ativos do cliente - RA: 25000304-2
        double fatura = manager.calcularFaturaAtiva(cliente);
        System.out.println("Fatura Ativa do Cliente: R$ " + fatura);
    }
}