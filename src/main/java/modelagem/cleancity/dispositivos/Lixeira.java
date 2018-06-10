package modelagem.cleancity.dispositivos;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import modelagem.cleancity.dispositivos.sensor.Balanca;
import modelagem.cleancity.dispositivos.sensor.Capacidade;
import modelagem.cleancity.mapa.Coordenada;
import modelagem.cleancity.dispositivos.sensor.SensorSonar;

/**
 * @author Henrique Goetz
 */
public class Lixeira {

    private Capacidade capacidade = new Capacidade();
    private Coordenada coord;
    private SensorSonar sensorVolume = new SensorSonar();
    private Balanca balanca = new Balanca();
    private int id;

    public Lixeira(double lati, double longi) {
        this.capacidade.setPeso(0);
        this.capacidade.setVolume(0);
        this.coord = new Coordenada(lati, longi);
    }

    // Overload do construtor
    public Lixeira(double lati, double longi, int id) {
        this.capacidade.setPeso(0);
        this.capacidade.setVolume(0);
        this.coord = new Coordenada(lati, longi);
        this.id = id;
    }

    public Coordenada getCoord() {
        return coord;
    }

    public float getPeso() {
        return balanca.getLeituraPeso();
    }

    public float getVolume() {
        return sensorVolume.getLeituraVolume();
    }

    public void jogarNaLixeira() {
        this.sensorVolume.lerVolume();
        this.balanca.lerBalanca();
    }

    public boolean verificarLixeira() {
        if (this.sensorVolume.getLeituraVolume() > 90 || this.balanca.getLeituraPeso() > 90) {
            System.out.println("Lixeira precisa ser esvaziada.");
            return true;
        }
        return false;
    }

    public void esvaziarLixeira() {
        this.sensorVolume.zerarVolume();
        this.balanca.zerarBalanca();
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

}
