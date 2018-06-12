package com.grupo04.cleancity.model.dispositivos;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.grupo04.cleancity.model.dispositivos.sensor.Balanca;
import com.grupo04.cleancity.model.Capacidade;
import com.grupo04.cleancity.model.dispositivos.sensor.SensorSonar;
import com.grupo04.cleancity.model.mapa.Coordenada;

/**
 * @author Henrique Goetz
 */
public class Lixeira {

    private Capacidade capacidade = new Capacidade();
    private Coordenada coord;
    private SensorSonar sensorVolume = new SensorSonar();
    private Balanca balanca = new Balanca();
    private int id;

    /**
     *
     * @param lati Latitude da lixeira
     * @param longi Longitude da lixeira
     */
    public Lixeira(double lati, double longi) {
        this.capacidade.setPeso(100);
        this.capacidade.setVolume(100);
        this.coord = new Coordenada(lati, longi);
    }

    /**
     * Overload do construtor
     * @param lati Latitude da lixeira
     * @param longi Longitude da lixeira
     * @param id número identificador da lixeira
     */
    public Lixeira(double lati, double longi, int id) {
        this.capacidade.setPeso(100);
        this.capacidade.setVolume(100);
        this.coord = new Coordenada(lati, longi);
        this.id = id;
    }

    /**
     *
     * @return  estrutura de coordenada da lixeira
     */
    public Coordenada getCoord() {
        return coord;
    }

    /**
     *
     * @return peso lido da lixeira
     */
    public float getPeso() {
        return balanca.getLeituraPeso();
    }

    /**
     *
     * @return volume lido da lixeira
     */
    public float getVolume() {
        return sensorVolume.getLeituraVolume();
    }

    /**
     * Incrementa peso e volume da lixeira, simulando adição de lixo
     */
    public void jogarNaLixeira() {
        this.sensorVolume.lerVolume(this.capacidade.getVolume());
        this.balanca.lerBalanca(this.capacidade.getPeso());
    }

    /**
     *
     * @return TRUE, se a lixeira estiver cheia (capacidade de 90 kg e 90 litros), FALSE caso contrário
     */
    public boolean verificarLixeira() {
        if (this.sensorVolume.getLeituraVolume() > 90 || this.balanca.getLeituraPeso() > 90) {
            return true;
        }
        return false;
    }

    /**
     *  Zera peso e volume da lixeira, simulando o esvaziamento
     */
    public void esvaziarLixeira() {
        this.sensorVolume.zerarVolume();
        this.balanca.zerarBalanca();
    }

    /**
     *
     * @param id valor a ser setado como número identificador da lixeira
     */
    public void setId(int id){
        this.id = id;
    }

    /**
     *
     * @return número identificador da lixeira
     */
    public int getId(){
        return this.id;
    }

}
