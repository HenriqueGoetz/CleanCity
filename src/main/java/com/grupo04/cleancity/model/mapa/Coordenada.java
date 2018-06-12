package com.grupo04.cleancity.model.mapa;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Henrique Goetz
 */
public class Coordenada {
    
    private double latitude;
    private double longitude;

    /**
     *
     * @return latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     *
     * @param latitude valor da coordenada de latitudinal
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     *
     * @return longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     *
     * @param longitude valor da coordenada longitudinal
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * Cria a estrutura do conjunto latitude e longitude
     * @param latitude valor latitudinal
     * @param longitude valor longitudinal
     */
    public Coordenada(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
