package com.grupo04.cleancity.model.mapa;

import com.grupo04.cleancity.JavaApp;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author Lucas Hagen.
 */
public class Mapa {

    private JavaApp app;
    private WebView webView;

    public Mapa() throws IOException {
        super();
        webView = new WebView();
        app = new JavaApp();

        WebEngine webEngine = webView.getEngine();
        String path = new File("src/main/resources/html/mapa.html").getAbsolutePath();
        String contents;

        contents = new String(Files.readAllBytes(Paths.get(path)));
        webEngine.loadContent(contents);
        JSObject window = (JSObject) webEngine.executeScript("window");
        window.setMember("app", app);
    }

    public WebView getWebView() {
        return webView;
    }

    public void adicionarRegulador() {
        webView.getEngine().executeScript("adicionarRegulador()");
    }

    public Coordenada getCoordenadaRecebida() {
        return app.getCoordenadaRecebida();
    }

    public int getIdRecebido() {
        return app.getIdRecebido();
    }


    public void removerRegulador() {
        webView.getEngine().executeScript("marcadorSel.setMap(null);");
        webView.getEngine().executeScript("marcadorSel =  null;");
    }

    public void adicinarLixeira() {
        webView.getEngine().executeScript("adicionarLixeira(new google.maps.LatLng())");
    }

    public void removerLixeira() {
        webView.getEngine().executeScript("marcadorSel.setMap(null);");
        webView.getEngine().executeScript("marcadorSel =  null;");
    }
}
