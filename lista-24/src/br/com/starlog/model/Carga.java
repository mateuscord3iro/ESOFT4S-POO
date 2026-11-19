package br.com.starlog.model;

/**
 * CAMADA MODEL (Modelo de Domínio)
 * Representa uma carga a ser despachada pela estação StarLog.
 *
 * Esta classe não depende de componentes visuais do Swing.
 */
public class Carga {
    private final String codigoRastreio;
    private final String categoria;
    private final double peso;
    private final double valorDeclarado;

    public Carga(String codigoRastreio, String categoria, double peso, double valorDeclarado) {
        // Barreira fail-fast: impede a criação de cargas com dados inválidos.
        if (codigoRastreio == null || codigoRastreio.trim().isEmpty()) {
            throw new IllegalArgumentException("Código de rastreio não pode ser nulo ou vazio.");
        }
        if (categoria == null || categoria.trim().isEmpty()) {
            throw new IllegalArgumentException("Categoria não pode ser nula ou vazia.");
        }
        if (!Double.isFinite(peso) || peso <= 0) {
            throw new IllegalArgumentException("Peso deve ser estritamente positivo (maior que zero).");
        }
        if (!Double.isFinite(valorDeclarado) || valorDeclarado <= 0) {
            throw new IllegalArgumentException("Valor declarado deve ser maior que zero.");
        }

        this.codigoRastreio = codigoRastreio.trim().toUpperCase();
        this.categoria = categoria.trim().toUpperCase();
        this.peso = peso;
        this.valorDeclarado = valorDeclarado;
    }

    public String getCodigoRastreio() {
        return codigoRastreio;
    }

    public String getCategoria() {
        return categoria;
    }

    public double getPeso() {
        return peso;
    }

    public double getValorDeclarado() {
        return valorDeclarado;
    }

    /**
     * Seguro: 2% do valor declarado, com sobretaxa única de R$ 150,00
     * se a carga for perigosa OU pesar mais de 100 kg.
     */
    public double calcularSeguro() {
        double seguro = valorDeclarado * 0.02;
        if ("PERIGOSA".equals(categoria) || peso > 100.0) {
            seguro += 150.0;
        }
        return seguro;
    }

    @Override
    public String toString() {
        return String.format("[%s] Categoria: %s | Peso: %.2f kg | Valor: R$ %.2f | Seguro: R$ %.2f",
                codigoRastreio, categoria, peso, valorDeclarado, calcularSeguro());
    }
}
