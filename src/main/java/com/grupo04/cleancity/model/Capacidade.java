package com.grupo04.cleancity.model;

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

    /**
     *
     * @param volume valor do volume que fará parte da estrutura de capacidade
     * @param peso valor do peso que fará parte da estrutura de capacidade
     */
    public Capacidade(float volume, float peso) {
        this.volume = volume;
        this.peso = peso;
    }

    /**
     *
     * @return volume da estrutura de capacidade
     */
    public float getVolume() {
        return volume;
    }

    /**
     *
     * @param volume valor de volume a ser setado na estrutura
     */
    public void setVolume(float volume) {
        this.volume = volume;
    }

    /**
     *
     * @return peso contido na estrutura de capacidade
     */
    public float getPeso() {
        return peso;
    }

    /**
     *
     * @param peso valor de peso a ser setado na estrutura
     */
    public void setPeso(float peso) {
        this.peso = peso;
    }
    
    
    
}
