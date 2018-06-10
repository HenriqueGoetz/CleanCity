package modelagem.cleancity.dispositivos;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import modelagem.cleancity.dispositivos.sensor.Capacidade;
import modelagem.cleancity.dispositivos.sensor.Balanca;
import modelagem.cleancity.dispositivos.sensor.SensorSonar;

/**
 *
 * @author Henrique Goetz
 */
public class Caminhao {

    private boolean disponibilidade;
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
