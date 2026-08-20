package br.com.techcorp.model;

import java.util.Objects;

public class Funcionario {

    private String matricula;
    private String nome;
    private String cargo;

//construtor
    public Funcionario(String matricula, String nome, String cargo){
        this.matricula = matricula;
        this.nome = nome;
        this.cargo = cargo;

    }

    //getters
    public String getMatricula(){
        return matricula;
    }

     public String getNome(){
        return nome;
    }

     public String getCargo(){
        return cargo;
    }

    // REGRA DE IDENTIDADE: Sobrescrevendo equals e hashCode comparando APENAS a matricula
    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Funcionario that = (Funcionario) o;
            return Objects.equals(matricula, that.matricula);

    }

    @Override
    public int hashCode(){
        return Objects.hash(matricula);

    }

    @Override
    public String toString(){
        return "Funcionario{" +
                "matricula='" + matricula + '\'' +
                ", nome='" + nome + '\'' +
                ", cargo='" + cargo + '\'' +
                '}';
    }
}

