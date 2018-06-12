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

    @Deprecated
    public ReguladorPh(double lati, double longi) {
        this.coord = new Coordenada(lati, longi);
    }

    /**
     * Overload do construtor de ReguladorPh
     * @param lati Latitude do regulador
     * @param longi Longitude do regulador
     * @param id número identificador do regulador
     */
    public ReguladorPh(double lati, double longi, int id) {
        this.coord = new Coordenada(lati, longi);
        this.id = id;
    }

    /**
     *
     * @return estrutura de coordenada do regulador
     */
    public Coordenada getCoord() {
        return coord;
    }

    /**
     * Realiza o aumeto de pH a partir do pH neutro
     */
    private void elevarPH() {
        sensorPh.setLeituraPh(7);
    }

    /**
     * Realiza a diminuição de pH a partir do pH neutro
     */
    private void reduzirPH() {
        sensorPh.setLeituraPh(7);
    }

    /**
     *
     * @return número identificador do regulador
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id valor a ser setado como número identificador do regulador
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Solicita a leitura de pH ao sensor
     */
    public void verificaPH() {
        sensorPh.lerPH();
    }

    // TODO: Verificar RN para nível onde eleva/reduz o PH e nível para notificar

    /**
     * Confere se o pH está dentro dos limites de normalidade
     */
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

    /**
     * Emite alerta sobre a anormalidade do pH
     * @param ph valor do pH a ser informado na notificação para a prefeitura
     */
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
