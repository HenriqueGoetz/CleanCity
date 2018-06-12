package com.grupo04.cleancity.model.mapa;

import com.grupo04.cleancity.MainController;
import com.grupo04.cleancity.data.Database;
import com.grupo04.cleancity.model.dispositivos.Lixeira;
import com.grupo04.cleancity.model.dispositivos.ReguladorPh;

/**
 * @author Júlia Eidelwein.
 */
public class JavaApp {

    private Coordenada coordenadaRecebida;
    private int idRecebido;

    /**
     * Recebe uma coordenada do código da API do mapa em javascript, contida em resources/html/mapa.html
     * @param coord string com a coordenada codificada na forma "(latitude,longitude)"
     */
    public void recebeCoordenada(String coord) {
        coord = coord.replaceAll("[()]", "");
        String[] coordenadaDaAPI = coord.split(", ");
        coordenadaRecebida = new Coordenada(Double.parseDouble(coordenadaDaAPI[0]), Double.parseDouble(coordenadaDaAPI[1]));
    }

    /**
     * Recebe um número identificador de um marcador da API do mapa
     * @param id número identificador do marcador
     */
    public void recebeId(int id){
        this.idRecebido = id;
    }

    /**
     *
     * @return coordenada recebida da API por meio de recebeCoordenada()
     */
    public Coordenada getCoordenadaRecebida(){
        return coordenadaRecebida;
    }

    /**
     * Move a lixeira de identificador id de acordo com o seu respectivo marcador, movido no mapa
     * @param coord coordenada onde está o marcador, onde a lixeira também deve ser posicionada
     * @param id número identificador da lixeira
     */
    public void moveLixeira(String coord, String id){
        recebeCoordenada(coord);
        idRecebido = Integer.parseInt(id);
        Lixeira movida = Database.getInstance().getLixeiraById(idRecebido);
        MainController.reposicionaLixeira(movida, coordenadaRecebida);
    }

    /**
     * Move o regulador de identificador id de acordo com o seu respectivo marcador, movido no mapa
     * @param coord coordenada onde está o marcador, onde o regulador também deve ser posicionado
     * @param id número identificador do regulador
     */
    public void moveRegulador(String coord, String id){
        recebeCoordenada(coord);
        idRecebido = Integer.parseInt(id);
        ReguladorPh movido = Database.getInstance().getReguladorById(idRecebido);
        MainController.reposicionaRegulador(movido, coordenadaRecebida);
    }

    /**
     *
     * @return número identificador recebido pelo da API por meio de recebeId()
     */
    public int getIdRecebido(){
        return this.idRecebido;
    }

}
