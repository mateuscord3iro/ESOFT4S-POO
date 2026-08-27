package br.com.banco.main;

import br.com.banco.model.Agencia;
import br.com.banco.model.Cliente;
import br.com.banco.model.ContaBancaria;

public class MainLista06 {
    public static void main(String[] args) {
        System.out.println("=== CAMPO DE PROVAS - CONFIRMACAO DE ARQUITETURA ===");

        Cliente cliente1 = new Cliente("123.456.789-00", "Carlos Silva", "carlos@email.com");
        Cliente cliente2 = new Cliente("123.456.789-00", "Carlos S. Souza", "carlos.souza@email.com");

        if (cliente1.equals(cliente2)) {
            System.out.println("[SUCESSO RN02]: Os clientes sao considerados IGUAIS pelo CPF!");
        } else {
            System.out.println("[FALHA RN02]: Os clientes deveriam ser iguais.");
        }

        System.out.println(cliente1.toString());

        ContaBancaria conta = new ContaBancaria("0001-9", cliente1, 50.0);

        boolean saqueEfetuado = conta.sacar(50.0);
        System.out.println("Resultado do saque de R$ 50.0 (Saldo: 50.0 | Taxa: 5.0): " + saqueEfetuado);

        if (!saqueEfetuado) {
            System.out.println("[SUCESSO RN03]: Saque negado corretamente por falta de saldo para cobrir a taxa!");
        }

        System.out.println("Total de contas abertas na Agencia: " + Agencia.getTotalContasAbertas());
    }
}