package com.grupo04.cleancity;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import modelagem.cleancity.Coordenada;
import modelagem.cleancity.Lixeira;

public class MainController implements Initializable {

    @FXML
    WebView mapViewer;

    ArrayList<Lixeira> lixeiras = new ArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        final WebEngine webEngine = mapViewer.getEngine();
        String path = new File("src/main/resources/html/mapa.html").getAbsolutePath();
        String contents = null;

        try {
            contents = new String(Files.readAllBytes(Paths.get(path)));
            webEngine.loadContent(contents);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addLixeira(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
            mapViewer.getEngine().executeScript("adicionarLixeira(new google.maps.LatLng(-30.0227, -51.1287))");
            double latitude = -30.0227;
            double longitude = -51.1287;

            lixeiras.add(new Lixeira(latitude, longitude));
            System.out.println("Lixeira Adicionada com sucesso.");
        }
    }

    public void verificarLixeiras(){
        Random rand = new Random();
        for(int i = 0; i < lixeiras.size(); i++){
            if(rand.nextInt(3)==0){
                lixeiras.get(i).jogarNaLixeira();
                if(lixeiras.get(i).espacoPertoDoLimite()){
                    System.out.print("Lixeira precisando ser esvaziada. Nº: ");
                    System.out.println(i);
                }
            }
        }
    }
    public void removeLixeira(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
            mapViewer.getEngine().executeScript("removeMarcador = true");

            double latitude = -30.0227;
            double longitude = -51.1287;

            for (int i = 0; i < lixeiras.size(); i++) {
                if (lixeiras.get(i).getCoord().equals(new Coordenada(latitude, longitude))) {
                    System.out.println("Lixeira Removida com sucesso.");
                    lixeiras.remove(i);
                }
            }
        }
    }
}
