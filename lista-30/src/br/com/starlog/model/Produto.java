package br.com.starlog.model;

import java.util.Locale;

public class Produto {
    private Long id;
    private final String nome;
    private double preco;
    private int estoque;

    public Produto(Long id, String nome, double preco, int estoque) {
        if (nome == null || nome.trim().length() < 3) {
            throw new IllegalArgumentException("O nome deve conter pelo menos 3 caracteres.");
        }
        validarPreco(preco);
        validarEstoque(estoque);
        this.id = id;
        this.nome = nome.trim();
        this.preco = preco;
        this.estoque = estoque;
    }

    public Produto(String nome, double preco, int estoque) {
        this(null, nome, preco, estoque);
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPreco(double preco) {
        validarPreco(preco);
        this.preco = preco;
    }

    public void setEstoque(int estoque) {
        validarEstoque(estoque);
        this.estoque = estoque;
    }

    private static void validarPreco(double preco) {
        if (!Double.isFinite(preco) || preco <= 0.0) {
            throw new IllegalArgumentException("O preco deve ser estritamente positivo.");
        }
    }

    private static void validarEstoque(int estoque) {
        if (estoque < 0) {
            throw new IllegalArgumentException("O estoque nao pode ser negativo.");
        }
    }

    @Override
    public String toString() {
        return String.format(Locale.forLanguageTag("pt-BR"),
                "Produto{id=%d, nome='%s', preco=R$ %.2f, estoque=%d}", id, nome, preco, estoque);
    }
}
