package br.com.atividade15_10.model;

import java.util.Objects;

/**
 * Representa uma aplicação ou container em execução na infraestrutura. A identidade única é baseada no ID. - RA: 25000304-2
 */
public class Container {
    private String id;
    private String nomeApp;
    private double ramNecessaria;

    // Construtor para inicialização dos dados do container - RA: 25000304-2
    public Container(String id, String nomeApp, double ramNecessaria) {
        this.id = id;
        this.nomeApp = nomeApp;
        this.ramNecessaria = ramNecessaria;
    }

    // Getters dos atributos - RA: 25000304-2
    public String getId() {
        return id;
    }

    public String getNomeApp() {
        return nomeApp;
    }

    public double getRamNecessaria() {
        return ramNecessaria;
    }

    // Dois containers são considerados iguais se possuírem o mesmo ID - RA: 25000304-2
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Container container = (Container) o;
        return Objects.equals(id, container.id);
    }

    // Gerador de hashCode estruturado a partir do ID do container - RA: 25000304-2
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}