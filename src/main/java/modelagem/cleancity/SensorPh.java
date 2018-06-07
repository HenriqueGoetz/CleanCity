package modelagem.cleancity;

import java.util.Random;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Henrique Goetz
 */
public class SensorPh extends Sensor {

    private float leituraPh = 7;

    public float getLeituraPh() {
        return leituraPh;
    }

    public void setLeituraPh(float leituraPh) {
        this.leituraPh = leituraPh;
    }

    public void lerPH() {
        Random random = new Random();
        setLeituraPh((float) (random.nextGaussian() + this.leituraPh));
        if (this.leituraPh < 0) {
            setLeituraPh(0);
        } else if (this.leituraPh > 14) {
            setLeituraPh(14);
        }
    }

}
