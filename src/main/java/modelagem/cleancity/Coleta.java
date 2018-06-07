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
public class Coleta {
    
    private int hora;
    private int minutos;
    private Caminhao[] frota;
    private Rota rota;
    private DiaDaSemana[] dias;
    private Equipe equipe;

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public int getMinutos() {
        return minutos;
    }

    public DiaDaSemana[] getDias() {
        return dias;
    }

    public void setDias(DiaDaSemana[] dias) {
        this.dias = dias;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }
    
    public void agendarColeta(int hour, int min, DiaDaSemana[] days){
        this.setHora(hour);
        this.setMinutos(min);
        this.setDias(days);
    }
}
