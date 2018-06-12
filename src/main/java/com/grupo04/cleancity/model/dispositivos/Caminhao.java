package com.grupo04.cleancity.model.dispositivos;

import com.grupo04.cleancity.data.Database;
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
        this.sensorVolume.setLeituraVolume(0);
        this.balanca.setLeituraPeso(0);
    }

    /**
     *
     * @return TRUE se disponível, FALSE caso contrário
     */
    public boolean isDisponivel() {
        return disponivel;
    }

    /**
     *
     * @return o valor medido pelo sensor de volume do caminhão
     */
    public float getLeituraSensor(){
        return this.sensorVolume.getLeituraVolume();
    }

    /**
     *
     * @return o valor medido pelo sensor de peso do caminhão
     */
    public float getLeituraBalanca(){
        return this.balanca.getLeituraPeso();
    }

    /**
     *  Zera a leitura do sensor de volume e balança do caminhão
     */
    public void esvaziar(){
        System.out.println("Caminhao realizou a coleta, e já esvaziou.");
        this.balanca.setLeituraPeso(0);
        this.sensorVolume.setLeituraVolume(0);
    }

    /**
     *
     * @param lix Lixeira a ser esvaziada no caminhão
     */
    public void virarDaLixeiraNoCaminhao(Lixeira lix){
        this.sensorVolume.setLeituraVolume(this.sensorVolume.getLeituraVolume()+lix.getVolume());
        this.balanca.setLeituraPeso(this.balanca.getLeituraPeso()+lix.getPeso());
    }

    /**
     *
     * @param disponivel TRUE se disponível, FALSE se ocupado
     */
    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    /**
     *
     * @return Capacidade do caminhão
     */
    public Capacidade getCapacidade() {
        return capacidade;
    }
}
