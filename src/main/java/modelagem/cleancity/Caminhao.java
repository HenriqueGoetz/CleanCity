package modelagem.cleancity;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
        this.capacidade.setPeso(0);
        this.capacidade.setVolume(0);
    }

    public boolean isDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

}
