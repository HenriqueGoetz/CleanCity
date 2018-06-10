package com.grupo04.cleancity.model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Henrique Goetz
 */
public enum DiaDaSemana {

    DOM(1),
    SEG(2),
    TER(3),
    QUA(4),
    QUI(5),
    SEX(6),
    SAB(7);

    private int value;

    DiaDaSemana(int i) {
        this.value = i;
    }

    private static DiaDaSemana[] ids;

    static {
        ids = new DiaDaSemana[values().length + 1];
        for(DiaDaSemana dia : values()) {
            ids[dia.value] = dia;
        }
    }

    public static DiaDaSemana fromInteger(int value) {
        if(value >= 1 && value <= 7)
            return ids[value];
        else
            return null;
    }
}
