package br.com.atividade15_10.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import br.com.atividade15_10.exception.CapacidadeExcedidaException;
import br.com.atividade15_10.exception.ServidorOfflineException;

/**
 * Representa um servidor virtual responsável por hospedar containers e gerenciar a alocação de recursos. - RA: 25000304-2
 */
public class Servidor {
    private String ip;
    private double precoPorHora;
    private double ramTotal;
    private boolean statusLigado;
    
    // Instancia a lista direto no atributo para evitar erro de referência nula - RA: 25000304-2
    private List<Container> containers = new ArrayList<>();

    // Construtor principal (o servidor sempre inicia desligado por padrão) - RA: 25000304-2
    public Servidor(String ip, double ramTotal, double precoPorHora) {
        this.ip = ip;
        this.ramTotal = ramTotal;
        this.precoPorHora = precoPorHora;
        this.statusLigado = false;
    }

    // Métodos para controle de estado do servidor - RA: 25000304-2
    public void ligar() {
        this.statusLigado = true;
    }

    public void desligar() {
        this.statusLigado = false;
    }

    // Soma a memória RAM requerida por todos os containers usando a API de Streams - RA: 25000304-2
    public double calcularRamEmUso() {
        return containers.stream()
                .mapToDouble(Container::getRamNecessaria)
                .sum();
    }

    // Método para alocar um novo container validando se o servidor está ativo e com capacidade - RA: 25000304-2
    public void alocarContainer(Container c) throws CapacidadeExcedidaException {
        // Validação fail-fast: impede alocação em servidor desligado - RA: 25000304-2
        if (!statusLigado) {
            throw new ServidorOfflineException("Erro: Não é possível alocar containers em um servidor desligado.");
        }

        // Valida se a adição do container vai ultrapassar o limite da RAM total - RA: 25000304-2
        double ramEmUsoAtual = calcularRamEmUso();
        if ((ramEmUsoAtual + c.getRamNecessaria()) > ramTotal) {
            throw new CapacidadeExcedidaException("Falha Crítica: Memória RAM insuficiente no servidor " + ip + ".");
        }

        // Se passar nas duas validações, inclui o container na lista - RA: 25000304-2
        containers.add(c);
    }

    // Getters da classe - RA: 25000304-2
    public String getIp() {
        return ip;
    }

    public boolean isStatusLigado() {
        return statusLigado;
    }

    public double getPrecoPorHora() {
        return precoPorHora;
    }

    // Dois servidores são considerados o mesmo se tiverem o mesmo endereço IP - RA: 25000304-2
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Servidor servidor = (Servidor) o;
        return Objects.equals(ip, servidor.ip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ip);
    }
}