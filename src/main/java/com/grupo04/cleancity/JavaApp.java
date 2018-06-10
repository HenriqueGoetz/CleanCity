package com.grupo04.cleancity;

import com.grupo04.cleancity.data.Database;
import modelagem.cleancity.mapa.Coordenada;
import modelagem.cleancity.dispositivos.Lixeira;

public class JavaApp {

    private Coordenada coordenadaRecebida;
    private MainController controller;
    private int idRecebido;

    public void recebeCoordenada(String coord) {
        coord = coord.replaceAll("[()]", "");
        String[] coordenadaDaAPI = coord.split(", ");
        coordenadaRecebida = new Coordenada(Double.parseDouble(coordenadaDaAPI[0]), Double.parseDouble(coordenadaDaAPI[1]));
    }

    public void recebeId(int id){
        this.idRecebido = id;
    }

    public Coordenada getCoordenadaRecebida(){
        return coordenadaRecebida;
    }

    public void moveLixeira(String coord, String id){
        recebeCoordenada(coord);
        idRecebido = Integer.parseInt(id);
        Lixeira movida = Database.getInstance().getLixeiraById(idRecebido);
        MainController.reposicionaLixeira(movida, coordenadaRecebida);
    }

    public int getIdRecebido(){
        return this.idRecebido;
    }

}
