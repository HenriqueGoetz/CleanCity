package com.grupo04.cleancity.model.equipe;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.grupo04.cleancity.model.DiaDaSemana;
import com.grupo04.cleancity.model.dispositivos.Caminhao;
import com.grupo04.cleancity.model.mapa.Rota;

/**
 *
 * @author Henrique Goetz
 */
public class Coleta {
    
    private int hora;
    private int minutos;
    private Caminhao caminhao;
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

    public Coleta(int hora, int minutos, DiaDaSemana[] dias, Equipe equipe, Caminhao caminhao) {
        this.hora = hora;
        this.minutos = minutos;
        this.dias = dias;
        this.equipe = equipe;
        this.caminhao = caminhao;
    }

    public void setDias(DiaDaSemana[] dias) {
        this.dias = dias;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    public boolean EhDiaDaColeta(int dia){
        for(int i=0; i < this.dias.length; i++){
            if(this.dias[i].equals(dia))
                return true;
        }
        return false;
    }

    public Caminhao getCaminhao() {
        return caminhao;
    }

    public void agendarColeta(int hour, int min, DiaDaSemana[] days){
        this.setHora(hour);
        this.setMinutos(min);
        this.setDias(days);
    }
}
