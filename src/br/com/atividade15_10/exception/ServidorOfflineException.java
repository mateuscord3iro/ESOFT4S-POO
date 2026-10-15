package br.com.atividade15_10.exception;

/**
 * Exceção customizada (Unchecked Exception) lançada ao tentar alocar um container em um servidor com status desligado. - RA: 25000304-2
 */
public class ServidorOfflineException extends RuntimeException {
    
    // Construtor que envia a mensagem de erro detalhada para a superclasse RuntimeException - RA: 25000304-2
    public ServidorOfflineException(String msg) {
        super(msg);
    }
}