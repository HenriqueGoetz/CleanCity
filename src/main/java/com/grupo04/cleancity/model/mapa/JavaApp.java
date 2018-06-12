package com.grupo04.cleancity.model.mapa;

import com.grupo04.cleancity.MainController;
import com.grupo04.cleancity.data.Database;
import com.grupo04.cleancity.model.dispositivos.Lixeira;
import com.grupo04.cleancity.model.dispositivos.ReguladorPh;

public class JavaApp {

    private Coordenada coordenadaRecebida;
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

    public void moveRegulador(String coord, String id){
        recebeCoordenada(coord);
        idRecebido = Integer.parseInt(id);
        ReguladorPh movido = Database.getInstance().getReguladorById(idRecebido);
        MainController.reposicionaRegulador(movido, coordenadaRecebida);
    }

    public int getIdRecebido(){
        return this.idRecebido;
    }

}
