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
public class Balanca extends Sensor {
    
    private float leituraPeso;

    public float getLeituraPeso() {
        return leituraPeso;
    }

    public void setLeituraPeso(float leituraPeso) {
        this.leituraPeso = leituraPeso;
    }
    
}