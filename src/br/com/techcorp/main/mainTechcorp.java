package br.com.techcorp.main;

import br.com.techcorp.model.ControleDeAcesso;
import br.com.techcorp.model.Funcionario;

public class mainTechcorp {
    public static void main(String[] args) {
        ControleDeAcesso controle = new ControleDeAcesso();

        Funcionario f1 = new Funcionario("T-001", "Alice", "Desenvolvedora");
        Funcionario f2 = new Funcionario("T-001", "Alice Duplicada", "Desenvolvedora");

        System.out.println("--- TESTE DA CATRACA (ArrayList) ---");
        controle.registrarPassagem(f1);
        controle.registrarPassagem(f2); 

        System.out.println("\n--- TESTE DA SALA SEGURA (HashSet) ---");
        controle.concederAcessoSala(f1); 
        controle.concederAcessoSala(f2); 
    }
}