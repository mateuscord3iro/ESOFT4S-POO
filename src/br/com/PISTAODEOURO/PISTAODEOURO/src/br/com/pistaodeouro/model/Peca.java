package br.com.pistaodeouro.model;

    public class Peca {
        private String nome;
        private String codigoSku;
        private double preco;

        public Peca(String nome, String codigoSku, double preco) {
            this.nome = nome;
            this.codigoSku = codigoSku;
            this.preco = preco;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getCodigoSku() {
            return codigoSku;
        }

        public void setCodigoSku(String codigoSku) {
            this.codigoSku = codigoSku;
        }

        public double getPreco() {
            return preco;
        }

        public void setPreco(double preco) {
            this.preco = preco;
        }

        
}
