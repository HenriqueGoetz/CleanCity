package com.grupo04.cleancity.model.equipe;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Henrique Goetz
 */
public class Funcionario {

    /**
     *
     * @param nome nome do funcionário a ser cadastrado
     */
    public Funcionario(String nome) {
        this.nome = nome;
    }

    private String nome;

    /**
     *
     * @return nome do funcionário
     */
    public String getNome() {
        return nome;
    }

    /**
     *
     * @param nome nome a ser cadastrado para o funcionário
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
    
}
