package com.grupo04.cleancity.model.dispositivos.sensor;

import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Henrique Goetz
 */
public class Balanca extends Sensor {

    private float leituraPeso;

    public Balanca() {
        this.setLeituraPeso(0);
    }

    public float getLeituraPeso() {
        return leituraPeso;
    }

    /**
     *
     * @param leituraPeso Setter do peso lido pela balança
     */
    public void setLeituraPeso(float leituraPeso) {
        this.leituraPeso = leituraPeso;
    }

    /**
     *
     * @param limite Valor máximo que pode ser lido pela balança
     */
    public void lerBalanca(float limite) {
        Random random = new Random();
        setLeituraPeso((float) ((random.nextInt() % 10) + this.leituraPeso));
        if(getLeituraPeso()>limite){
            setLeituraPeso(limite);
        }
    }

    /**
     *  Zera o valor da leitura da balança
     */
    public void zerarBalanca(){
        setLeituraPeso(0);
    }
}