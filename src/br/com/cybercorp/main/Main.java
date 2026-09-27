package br.com.cybercorp.main;

import br.com.cybercorp.model.Credencial;
import br.com.cybercorp.model.Departamento;
import br.com.cybercorp.model.Funcionario;
import br.com.cybercorp.model.SistemaSeguranca;
import br.com.cybercorp.model.Veiculo;

public class Main{

    public static void main(String[] args) {
        Departamento depto = new Departamento("TI", "Tecnologia da Informação", 3);
        Funcionario func = new Funcionario("F123", "Matheus", depto);
        Veiculo veiculo = new Veiculo("ABC-1234", "Civic", func);

        SistemaSeguranca sistema = new SistemaSeguranca(2);

        Credencial c1 = new Credencial("FFF-999", true, func);
        Credencial clone = new Credencial("FFF-999", true, func);

        sistema.registrarCatraca(func);
        sistema.registrarCatraca(func);

        sistema.acessarCofre(c1);
        sistema.acessarCofre(clone);

        sistema.estacionarVeiculo(veiculo, 0);
        sistema.estacionarVeiculo(veiculo, 5);
    }
}