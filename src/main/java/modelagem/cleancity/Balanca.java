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
public class Balanca extends Sensor {

    private float leituraPeso;

    public float getLeituraPeso() {
        return leituraPeso;
    }

    public void setLeituraPeso(float leituraPeso) {
        this.leituraPeso = leituraPeso;
    }

    public void lerBalanca() {
        Random random = new Random();
        setLeituraPeso((float) ((random.nextInt() % 10) + this.leituraPeso));
    }
    
    public void zerarBalanca(){
        setLeituraPeso(0);
    }
}