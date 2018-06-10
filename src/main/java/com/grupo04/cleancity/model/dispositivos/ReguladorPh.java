package com.grupo04.cleancity.model.dispositivos;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.grupo04.cleancity.model.mapa.Coordenada;
import com.grupo04.cleancity.model.dispositivos.sensor.SensorPh;

/**
 *
 * @author Henrique Goetz
 */
public class ReguladorPh {

    private SensorPh sensorPh = new SensorPh();
    private Coordenada coord;
    private int id;

    public ReguladorPh(double lati, double longi) {
        this.coord = new Coordenada(lati, longi);
    }

    // Overload do construtor de ReguladorPh
    public ReguladorPh(double lati, double longi, int id) {
        this.coord = new Coordenada(lati, longi);
        this.id = id;
    }

    private void elevarPH(){
        System.out.println("O ph está sendo elevado.");
        sensorPh.setLeituraPh(7);
    }
    
    private void reduzirPH(){
        System.out.println("O ph está sendo elevado.");
        sensorPh.setLeituraPh(7);
    }

    public int getId(){
        return id;
    }

    public void setId(){
        this.id = id;
    }

    public void verificaPH(){
        sensorPh.lerPH();
    }
    // TODO: Verificar RN para nível onde eleva/reduz o PH e nível para notificar
    public void testarPH(){
        float phAgora = sensorPh.getLeituraPh();
        if(phAgora>8){
            this.elevarPH();
        }else if(phAgora < 6){
            this.reduzirPH();
        }
        if(phAgora>9 || phAgora <5){
            // TODO: Verificar como será feita a notificação
            System.out.print("Notificando prefeitura. Ph em: " + Float.toString(phAgora));
        }
    }

}
