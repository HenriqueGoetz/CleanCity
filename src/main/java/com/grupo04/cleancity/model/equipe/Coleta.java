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

    /**
     *
     * @return hora da coleta
     */
    public int getHora() {
        return hora;
    }

    /**
     *
     * @param hora valor da Hora configurada para a coleta
     */
    public void setHora(int hora) {
        this.hora = hora;
    }

    /**
     *
     * @return minuto da coleta
     */
    public int getMinutos() {
        return minutos;
    }

    /**
     *
     * @return dia da coleta
     */
    public DiaDaSemana[] getDias() {
        return dias;
    }

    /**
     *
     * @param hora valor da hora escolhida para a coleta
     * @param minutos valor do minuto escolhido para a coleta
     * @param dias valor do dia escolhido para a coleta
     * @param equipe conjunto de funcionários integrantes da coleta
     * @param caminhao o caminhão que realizará a coleta
     */
    public Coleta(int hora, int minutos, DiaDaSemana[] dias, Equipe equipe, Caminhao caminhao) {
        this.hora = hora;
        this.minutos = minutos;
        this.dias = dias;
        this.equipe = equipe;
        this.caminhao = caminhao;
    }

    /**
     *
     * @param dias valor do dia configurado para a coleta
     */
    public void setDias(DiaDaSemana[] dias) {
        this.dias = dias;
    }

    /**
     *
     * @param minutos valor do minuto configurado para a coleta
     */
    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    /**
     * Chega se o dia atual corresponde ao dia da coleta
     * @param dia dia marcado para a coleta
     * @return TRUE se for o dia da coleta, FALSE caso contrário
     */
    public boolean EhDiaDaColeta(int dia){
        for(int i=0; i < this.dias.length; i++){
            if(this.dias[i].equals(dia))
                return true;
        }
        return false;
    }

    /**
     *
     * @return caminhão encarregado da coleta
     */
    public Caminhao getCaminhao() {
        return caminhao;
    }

    /**
     * Marca a data da coleta
     * @param hour hora agendada da coleta
     * @param min minuto agendado da coleta
     * @param days dia agendado da coleta
     */
    public void agendarColeta(int hour, int min, DiaDaSemana[] days){
        this.setHora(hour);
        this.setMinutos(min);
        this.setDias(days);
    }
}
