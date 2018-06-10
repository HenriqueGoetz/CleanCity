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
public class SensorSonar extends Sensor {

    private float leituraVolume = 0;

    public float getLeituraVolume() {
        return leituraVolume;
    }

    public void setLeituraVolume(float leituraVolume) {
        this.leituraVolume = leituraVolume;
    }

    public void lerVolume() {
        Random random = new Random();
        setLeituraVolume((float) (this.leituraVolume + random.nextFloat() * 0.5));
    }

    public void zerarVolume() {
        setLeituraVolume(0);
    }
}
