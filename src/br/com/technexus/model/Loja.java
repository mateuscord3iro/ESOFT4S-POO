package br.com.technexus.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors; // Adicione este import

public class Loja {

    private List<Produto> catalogo = new ArrayList<>();

    public void cadastrar(Produto p) {
        this.catalogo.add(p);
    }

    public List<Produto> buscarPorCategoria(String catDesejada) {
        return this.catalogo.stream()
                .filter(p -> p.getCategoria().equalsIgnoreCase(catDesejada))
                .collect(Collectors.toList()); // Alterado aqui
    }

    public double calcularPatrimonioTotal() {
        return this.catalogo.stream()
                .mapToDouble(Produto::getPreco)
                .sum();
    }

    public double calcularTotalPorCategoria(String catDesejada) {
        return this.catalogo.stream()
                .filter(p -> p.getCategoria().equalsIgnoreCase(catDesejada))
                .mapToDouble(Produto::getPreco)
                .sum();
    }
}