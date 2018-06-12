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
public class SensorOxigenio extends Sensor {
    
    private float leituraOxigenioDissolvido;

    public float getLeituraOxigenioDissolvido() {
        return leituraOxigenioDissolvido;
    }

    /**
     *
     * @param leituraOxigenioDissolvido valor a ser setado para a leitura do sensor
     */
    public void setLeituraOxigenioDissolvido(float leituraOxigenioDissolvido) {
        this.leituraOxigenioDissolvido = leituraOxigenioDissolvido;
    }

    /**
     * Realiza uma leitura de oxigênio, reajandando o valor lido no intervalo de 0 a 100 caso esteja fora do mesmo
     */
    private void lerOxigenioDissolvido() {
        Random random = new Random();
        setLeituraOxigenioDissolvido((float) (random.nextGaussian() + this.leituraOxigenioDissolvido));
        if (this.leituraOxigenioDissolvido < 0) {
            setLeituraOxigenioDissolvido(0);
        } else if (this.leituraOxigenioDissolvido > 100) {
            setLeituraOxigenioDissolvido(100);
        }
    }
}
