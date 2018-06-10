package com.grupo04.cleancity.model.dispositivos;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.grupo04.cleancity.model.dispositivos.sensor.Balanca;
import com.grupo04.cleancity.model.dispositivos.sensor.Capacidade;
import com.grupo04.cleancity.model.dispositivos.sensor.SensorSonar;

/**
 *
 * @author Henrique Goetz
 */
public class Caminhao {

    private boolean disponibilidade = true;
    private SensorSonar sensorVolume;
    private Balanca balanca;
    private Capacidade capacidade;

    public Caminhao() {
        this.capacidade = new Capacidade();
    }

    public boolean isDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

}
