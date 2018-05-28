package com.grupo04.cleancity;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import modelagem.cleancity.*;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
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
import javafx.stage.Stage;


public class MainApp extends Application {
    
    //public static Boolean removeMarcador = false;
    @Override
    public void start(Stage stage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
        
        //MapaGoogle mapa = new MapaGoogle();
        
        
       
        WebView browser = new WebView();
        final WebEngine webEngine = browser.getEngine();
        String path = new File("src/main/resources/html/mapa.html").getAbsolutePath();
        String contents = new String(Files.readAllBytes(Paths.get(path)));
        webEngine.loadContent(contents);
        //webEngine.load("https://www.google.com.br/maps");
        
        Scene scene = new Scene(browser,750,500);
        scene.getStylesheets().add("/styles/Styles.css");
        
        stage.setTitle("CleanCity");
 
        Button btnAdd = new Button();
        btnAdd.setText("Adicionar Lixeira");
        btnAdd.setMinWidth(125);
        btnAdd.setMinWidth(125);
        btnAdd.setOnMouseClicked(new EventHandler<MouseEvent>() {
 
            @Override
            public void handle(MouseEvent event) {
                MouseButton button = event.getButton();
                if(button==MouseButton.PRIMARY){
                    webEngine.executeScript("adicionarLixeira(new google.maps.LatLng(-30.0227, -51.1287))");
                }
            }
        });
        
        Button btnRmv = new Button();
        btnRmv.setText("Remover Lixeira");
        btnRmv.setMinWidth(125);
        btnRmv.setMinWidth(125);
        btnRmv.setOnMouseClicked(new EventHandler<MouseEvent>() {
 
            @Override
            public void handle(MouseEvent event) {
                MouseButton button = event.getButton();
                if(button==MouseButton.PRIMARY){
                    //removeMarcador = true;
                     webEngine.executeScript("removeMarcador = true");
                }
            }
        });
        
        VBox vBox = new VBox();
        vBox.getChildren().addAll(btnAdd, btnRmv);
        HBox hBox = new HBox();
        hBox.getChildren().addAll(browser, vBox);
         
        StackPane root = new StackPane();
        root.getChildren().add(hBox);
         
        Scene cena = new Scene(root, 400, 300);
        stage.setScene(cena);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    

}
