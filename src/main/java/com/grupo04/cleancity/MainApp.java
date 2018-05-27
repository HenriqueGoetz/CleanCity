package com.grupo04.cleancity;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import modelagem.cleancity.*;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;


public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
        
        //MapaGoogle mapa = new MapaGoogle();
       
        WebView browser = new WebView();
        WebEngine webEngine = browser.getEngine();
        String path = new File("src/main/resources/html/mapa.html").getAbsolutePath();
        String contents = new String(Files.readAllBytes(Paths.get(path)));
        webEngine.loadContent(contents);
        //webEngine.load("https://www.google.com.br/maps");
        
        Scene scene = new Scene(browser,750,500);
        scene.getStylesheets().add("/styles/Styles.css");
        
        stage.setTitle("JavaFX and Maven");
        stage.setScene(scene);
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
