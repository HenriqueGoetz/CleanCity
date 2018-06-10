package com.grupo04.cleancity.model.dispositivos;

import com.grupo04.cleancity.model.dispositivos.sensor.Balanca;
import com.grupo04.cleancity.model.Capacidade;
import com.grupo04.cleancity.model.dispositivos.sensor.SensorSonar;

/**
 *
 * @author Henrique Goetz
 */
public class Caminhao {

    private boolean disponivel = true;
    private SensorSonar sensorVolume = new SensorSonar();
    private Balanca balanca = new Balanca();
    private Capacidade capacidade;

    @Deprecated
    public Caminhao() {
        this(0, 0);
    }

    /**
     * Cria uma instancia de um caminhão
     * @param volume Volume maximo que o caminhão consegue carregar em LITROS
     * @param pesoMax Peso máximo que o caminhão consegue carregar em KILOGRAMAS
     */
    public Caminhao(float volume, float pesoMax) {
        this.capacidade = new Capacidade(volume, pesoMax);
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public float getLeituraVolume(){
        return this.sensorVolume.getLeituraVolume();
    }

    public float getLeituraBalanca(){
        return this.balanca.getLeituraPeso();
    }
    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public Capacidade getCapacidade() {
        return capacidade;
    }
}
