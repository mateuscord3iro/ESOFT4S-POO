package br.com.atividade15_10.model;

import java.util.Objects;

/**
 * Representa um cliente no sistema. A identidade única do cliente é definida pelo seu CPF. - RA: 25000304-2
 */
public class Cliente {
    private String cpf;
    private String nome;

    // Construtor para inicialização dos dados obrigatórios do cliente - RA: 25000304-2
    public Cliente(String cpf, String nome) {
        this.cpf = cpf;
        this.nome = nome;
    }

    // Getters e Setters dos atributos - RA: 25000304-2
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // Dois clientes são considerados iguais se tiverem o mesmo CPF - RA: 25000304-2
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(cpf, cliente.cpf);
    }

    // Gerador de hashCode estruturado a partir do CPF do cliente - RA: 25000304-2
    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }
}