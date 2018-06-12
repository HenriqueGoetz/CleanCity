package com.grupo04.cleancity.model.dispositivos.sensor;

import java.util.Random;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author Henrique Goetz
 */
public class SensorPh extends Sensor {

    private float leituraPh = 7;

    /**
     *
     * @return leitura de pH do sensor
     */
    public float getLeituraPh() {
        return leituraPh;
    }

    /**
     *
     * @param leituraPh valor a ser setado para a leitura do pH do sensor
     */
    public void setLeituraPh(float leituraPh) {
        this.leituraPh = leituraPh;
    }

    /**
     * Realiza uma leitura de pH incrementando ou decrementando o pH, ajustando o valor para o intervalo de 0 a 14
     */
    public void lerPH() {
        Random random = new Random();
        int fator = random.nextInt(2);

        if (fator == 0)
            setLeituraPh((float)(-0.5*(random.nextInt(2))+this.leituraPh));
        else
            setLeituraPh((float)(0.5*(random.nextInt(2))+this.leituraPh));

        if(1 == random.nextInt(5)){
            setLeituraPh(7);
        }

        if (this.leituraPh < 0) {
            setLeituraPh(0);
        } else if (this.leituraPh > 14) {
            setLeituraPh(14);
        }
    }

}
