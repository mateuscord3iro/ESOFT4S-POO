package br.com.atividade15_10.exception;

/**
 * Exceção customizada (Checked Exception) lançada quando a alocação de containers excede a memória RAM total do servidor. - RA: 25000304-2
 */
public class CapacidadeExcedidaException extends Exception {
    
    // Construtor que repassa a mensagem de erro detalhada para a superclasse Exception - RA: 25000304-2
    public CapacidadeExcedidaException(String msg) {
        super(msg);
    }
}