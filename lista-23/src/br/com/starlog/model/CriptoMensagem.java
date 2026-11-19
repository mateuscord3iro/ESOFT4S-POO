package br.com.starlog.model;

import java.util.Locale;

/**
 * Representa uma mensagem validada e suas regras de criptografia.
 * Esta classe nao depende da interface grafica.
 */
public class CriptoMensagem {
    private final String remetente;
    private final String mensagem;
    private final int chaveDeslocamento;

    public CriptoMensagem(String remetente, String mensagem, int chaveDeslocamento) {
        if (remetente == null || remetente.trim().isEmpty()) {
            throw new IllegalArgumentException("Remetente não pode ser nulo ou vazio.");
        }
        if (mensagem == null || mensagem.trim().isEmpty()) {
            throw new IllegalArgumentException("Mensagem não pode ser nula ou vazia.");
        }
        if (chaveDeslocamento < 1 || chaveDeslocamento > 25) {
            throw new IllegalArgumentException("Chave deve estar entre 1 e 25.");
        }

        this.remetente = remetente.trim().toUpperCase(Locale.ROOT);
        this.mensagem = mensagem.trim().toUpperCase(Locale.ROOT);
        this.chaveDeslocamento = chaveDeslocamento;
    }

    public String getRemetente() {
        return remetente;
    }

    public String getMensagem() {
        return mensagem;
    }

    public int getChaveDeslocamento() {
        return chaveDeslocamento;
    }

    /** Rotaciona as letras de A a Z e preserva os demais caracteres. */
    public String cifrar() {
        StringBuilder cifrado = new StringBuilder();
        for (int i = 0; i < mensagem.length(); i++) {
            char letra = mensagem.charAt(i);
            if (letra >= 'A' && letra <= 'Z') {
                letra = (char) ('A' + (letra - 'A' + chaveDeslocamento) % 26);
            }
            cifrado.append(letra);
        }
        return cifrado.toString();
    }

    /** Soma os valores numericos dos caracteres da mensagem normalizada. */
    public int calcularChecksum() {
        int checksum = 0;
        for (int i = 0; i < mensagem.length(); i++) {
            checksum += mensagem.charAt(i);
        }
        return checksum;
    }
}
