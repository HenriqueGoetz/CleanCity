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
public class Equipe {
    private Funcionario[] funcionarios;
    private int id;

    /**
     *
     * @param funcionarios array de funcionários que integrarão a equipe
     * @param id número identificador da equipe
     */
    public Equipe(Funcionario[] funcionarios, int id) {
        this.funcionarios = funcionarios;
        this.id = id;
    }
}
