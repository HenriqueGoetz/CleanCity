package com.grupo04.cleancity.model.dispositivos.sensor;

import com.grupo04.cleancity.model.Capacidade;

import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author Henrique Goetz
 */
public class SensorSonar extends Sensor {

    private float leituraVolume = 0;

    /**
     *
     * @return leitura de volume do sensor
     */
    public float getLeituraVolume() {
        return leituraVolume;
    }

    /**
     *
     * @param leituraVolume valor a ser setado como leitura de volume do pH
     */
    public void setLeituraVolume(float leituraVolume) {
        this.leituraVolume = leituraVolume;
    }

    /**
     * Realiza a leitura do volume incrementando o volume original
     * @param limite valor máximo suportado como volume lido
     */
    public void lerVolume(float limite) {
        Random random = new Random();
        setLeituraVolume((float) (this.leituraVolume + random.nextFloat() * 0.5));
        if (this.getLeituraVolume() > limite) {
            setLeituraVolume(limite);

        }
    }

    /**
     * Zera o valor da leitura do sensor
     */
    public void zerarVolume() {
        setLeituraVolume(0);
    }
}
