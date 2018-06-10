package com.grupo04.cleancity.model.dispositivos;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.grupo04.cleancity.data.Database;
import com.grupo04.cleancity.model.mapa.Coordenada;
import com.grupo04.cleancity.model.dispositivos.sensor.SensorPh;
import javafx.scene.control.Alert;

import javax.swing.*;

/**
 * @author Henrique Goetz
 */
public class ReguladorPh {

    private SensorPh sensorPh = new SensorPh();
    private Coordenada coord;
    private int id;

    public ReguladorPh(double lati, double longi) {
        this.coord = new Coordenada(lati, longi);
    }

    // Overload do construtor de ReguladorPh
    public ReguladorPh(double lati, double longi, int id) {
        this.coord = new Coordenada(lati, longi);
        this.id = id;
    }


    public Coordenada getCoord() {
        return coord;
    }

    private void elevarPH() {
        sensorPh.setLeituraPh(7);
    }

    private void reduzirPH() {
        sensorPh.setLeituraPh(7);
    }

    public int getId() {
        return id;
    }

    public void setId() {
        this.id = id;
    }

    public void verificaPH() {
        sensorPh.lerPH();
    }

    // TODO: Verificar RN para nível onde eleva/reduz o PH e nível para notificar
    public void testarPH() {
        float phAgora = sensorPh.getLeituraPh();
        if (phAgora > 10) {
            this.notificarPrefeitura(phAgora);
            this.elevarPH();
        } else if (phAgora < 4) {
            notificarPrefeitura(phAgora);
            this.reduzirPH();
        }
    }

    public void notificarPrefeitura(float ph) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Notificação");
        if (ph > 7)
            alerta.setContentText("Prefeitura, o ph nas coordenadas: " + this.getCoord().getLatitude() + ", " + this.getCoord().getLongitude() + " estava muito alto.");
        else
            alerta.setContentText("Prefeitura, o ph nas coordenadas: " + this.getCoord().getLatitude() + ", " + this.getCoord().getLongitude() + " estava muito baixo.");

        alerta.show();
    }

}
