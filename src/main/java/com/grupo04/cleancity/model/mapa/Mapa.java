package com.grupo04.cleancity.model.mapa;

import com.grupo04.cleancity.model.dispositivos.Lixeira;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * @author Lucas Hagen.
 */
public class Mapa {

    private JavaApp app;
    private WebView webView;

    /**
     * Instancia um a webEngine que poderá esnviar comando de execuções de script à API em javascript contida em
     * resources/html/mapa.html
     * @throws IOException
     */
    public Mapa() throws IOException {
        super();
        webView = new WebView();
        app = new JavaApp();

        WebEngine webEngine = webView.getEngine();

        InputStream in = getClass().getResourceAsStream("/html/mapa.html");
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder contents = new StringBuilder();

        String line;
        while((line = reader.readLine()) != null)
            contents.append(line).append("\n");

        webEngine.loadContent(contents.toString());
        JSObject window = (JSObject) webEngine.executeScript("window");
        window.setMember("app", app);
    }

    /**
     *
     * @return webView da webEngine
     */
    public WebView getWebView() {
        return webView;
    }

    /**
     * Solicita à API a inserção de um novo marcador de regulador ao mapa
     */
    public void adicionarRegulador() {
        webView.getEngine().executeScript("adicionarRegulador()");
    }

    /**
     *
     * @return coordenada recebida da API pelo java app
     */
    public Coordenada getCoordenadaRecebida() {
        return app.getCoordenadaRecebida();
    }

    /**
     *
     * @return id recebido da API pelo java app
     */
    public int getIdRecebido() {
        return app.getIdRecebido();
    }

    /**
     * Solicita à API a remoção de um marcador de regulador, presente no mapa
     */
    public void removerRegulador() {
        webView.getEngine().executeScript("marcadorSel.setMap(null);");
        webView.getEngine().executeScript("marcadorSel =  null;");
    }

    /**
     * Solicita à API a inserção de um marcador de lixeira no mapa
     */
    public void adicinarLixeira() {
        webView.getEngine().executeScript("adicionarLixeira(new google.maps.LatLng())");
    }

    /**
     * Solicita à API a remoção de um marcador de lixeira, presente no mapa
     */
    public void removerLixeira() {
        webView.getEngine().executeScript("marcadorSel.setMap(null);");
        webView.getEngine().executeScript("marcadorSel =  null;");
    }

    /**
     * Solicita à API que insira cada uma das lixeiras na lista de ponto inertemdiários de uma rota de coleta
     * @param lixeiras array de lixeiras que deverão ser recolhidas em uma coleta
     */
    public void criarRota(List<Lixeira> lixeiras){
        for (Lixeira lix : lixeiras) {
            webView.getEngine().executeScript("adicionarAoArrayDeWayPoints(" + lix.getCoord().getLatitude() + "," +  lix.getCoord().getLongitude() + ")");
        }
        System.out.println("Lixeiras adicionadas na rota");
    }

    /**
     * Solicita à API que calcule e mostre uma rota entre os pontos adicionados com criarRota()
     */
    public void mostrarRota(){
        webView.getEngine().executeScript("calcularRota()");
    }

    /**
     * Solicita à API que limpe a rota mostrada no mapa
     */
    public void apagarRota(){
        webView.getEngine().executeScript("displayDirecao.setDirections(null)");
    }
}
