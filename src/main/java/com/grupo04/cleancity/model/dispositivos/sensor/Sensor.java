package com.grupo04.cleancity.model.dispositivos.sensor;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Henrique Goetz
 */
public class Sensor {

    /**
     *
     * @return id do sensor
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id identificador a ser setado para o sensor
     */
    public void setId(int id) {
        this.id = id;
    }
    
    private int id;
    private String enderecoIP;

    /**
     *
     * @return esderenco IP do sensor
     */
    public String getEnderecoIP() {
        return enderecoIP;
    }

    /**
     *
     * @param enderecoIP endereco IP a ser setado no sensor
     */
    public void setEnderecoIP(String enderecoIP) {
        this.enderecoIP = enderecoIP;
    }
    
    
}
