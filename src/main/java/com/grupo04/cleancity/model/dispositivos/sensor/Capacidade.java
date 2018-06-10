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
public class Capacidade {
    
    private float volume;
    private float peso;

    public Capacidade() {
        this(0, 0);
    }

    public Capacidade(float volume, float peso) {
        this.volume = volume;
        this.peso = peso;
    }

    public float getVolume() {
        return volume;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }
    
    
    
}
