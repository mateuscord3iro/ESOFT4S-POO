package br.com.starlog.model;

import br.com.starlog.exception.CapacidadeExcedidaException;
import java.util.ArrayList;
import java.util.List;

public class ModuloCarga {

    private String idModulo;
    private int capacidadeMaxima;
    private List<Carga> cargas;

    public ModuloCarga(String idModulo, int capacidadeMaxima) {
        this.idModulo = idModulo;
        this.capacidadeMaxima = capacidadeMaxima;
        this.cargas = new ArrayList<>();
    }

    public String getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(String idModulo) {
        this.idModulo = idModulo;
    }

    public int getCapacidadeMaxima() {
        return capacidadeMaxima;
    }

    public void setCapacidadeMaxima(int capacidadeMaxima) {
        this.capacidadeMaxima = capacidadeMaxima;
    }

    public List<Carga> getCargas() {
        return cargas;
    }

    public void carregarCarga(Carga carga) throws CapacidadeExcedidaException {
        if (this.cargas.size() >= this.capacidadeMaxima) {
            throw new CapacidadeExcedidaException("Capacidade maxima de " + this.capacidadeMaxima + " atingida no modulo " + this.idModulo);
        }
        this.cargas.add(carga);
    }

    public double calcularSeguroTotal() {
        return this.cargas.stream()
                .mapToDouble(Carga::getValorSeguro)
                .sum();
    }

    public long contarPorCategoria(String categoria) {
        return this.cargas.stream()
                .filter(c -> c.getCategoria() != null && c.getCategoria().equalsIgnoreCase(categoria))
                .count();
    }

    public double calcularSeguroPesadas(double pesoCorte) {
        return this.cargas.stream()
                .filter(c -> c.getPesoKg() > pesoCorte)
                .mapToDouble(Carga::getValorSeguro)
                .sum();
    }
}