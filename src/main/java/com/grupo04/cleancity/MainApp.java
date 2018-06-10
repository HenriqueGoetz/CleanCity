package com.grupo04.cleancity;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sun.applet.Main;


public class MainApp extends Application {

    public static MainApp instance;

    private Stage stage;
    private Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        instance = this;

        primaryStage.setTitle("CleanCity v0.1");

        this.stage = primaryStage;

        Parent root = FXMLLoader.load(getClass().getResource("Scene.fxml"));
        this.scene = new Scene(root, 1080, 720);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static MainApp getInstance() {
        return instance;
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

        MainController mController = new MainController();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                mController.lacoDeControle();
            }
        });

        thread.start();
        launch(args);
    }
}
