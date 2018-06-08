package modelagem.cleancity;

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

    public Equipe(Funcionario[] funcionarios, int id) {
        this.funcionarios = funcionarios;
        this.id = id;
    }
}
