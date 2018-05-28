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
public class Lixeira {

    private Capacidade capacidade;
    private Coordenada coord;
    private SensorSonar sensorVolume = new SensorSonar();
    private Balanca balanca = new Balanca();

    public Lixeira(double lati, double longi) {
        this.capacidade.setPeso(0);
        this.capacidade.setVolume(0);
        this.coord.setLatitude(lati);
        this.coord.setLongitude(longi);
    }
    
    public float getPeso(){
        return balanca.getLeituraPeso();
    }
    
    public float getVolume(){
        return sensorVolume.getLeituraVolume();
    }
}
