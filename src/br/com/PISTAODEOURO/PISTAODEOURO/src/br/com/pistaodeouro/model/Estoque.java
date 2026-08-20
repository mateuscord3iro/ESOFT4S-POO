package br.com.pistaodeouro.model;

import java.util.HashMap;
import java.util.Map;

public class Estoque {
Map<String, Integer> itens;
    
    public Estoque(String itens){
        this.itens = new HashMap<>();

    }


    public void darEntrada (String sku, int quantidade){
         if(sku == null || quantidade <= 0) {
            return;
        }
        int qtdAtual = itens.getOrDefault(sku, 0);
        itens.put(sku, qtdAtual + quantidade);
    }

    public boolean darBaixa(String sku, int quantidade) {
        if (sku == null || quantidade <= 0) {
            return false;
        }

        int qtdAtual = consultarQuantidade(sku);

        if (qtdAtual >= quantidade) {
            itens.put(sku, qtdAtual - quantidade);
            return true;
        }

        return false;
    }

    public int consultarQuantidade(String sku) {
        if (sku == null) {
            return 0;
        }
        return itens.getOrDefault(sku, 0);
    }
}



