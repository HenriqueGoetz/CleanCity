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
import modelagem.cleancity.*;

public class MainController implements Initializable {

    @FXML
    WebView mapViewer;

    ArrayList<Lixeira> lixeiras = new ArrayList();
    ArrayList<ReguladorPh> reguladoresPH = new ArrayList();
    ArrayList<Caminhao> caminhoes = new ArrayList<>();
    ArrayList<Coleta> coletas = new ArrayList<>();
    ArrayList<Equipe> equipes = new ArrayList<>();
    ArrayList<Funcionario> funcionarios = new ArrayList<>();

    public int hora = 0;
    public int minuto = 0;
    public int dia = 0;

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

            //É preciso pegar o valor de latitude e longitude da lixeira.

            double latitude = -30.0227;
            double longitude = -51.1287;

            lixeiras.add(new Lixeira(latitude, longitude));
            System.out.println("Lixeira Adicionada com sucesso.");
        }
    }

    public void addCaminhao() {
        caminhoes.add(new Caminhao());
    }

    public void addColeta(int hora, int min, DiaDaSemana[] dias) {
        if (haCaminhoesDisponiveis())
            coletas.add(new Coleta(hora, min, dias));
    }

    public boolean haCaminhoesDisponiveis() {
        for (int i = 0; i < caminhoes.size(); i++) {
            if (caminhoes.get(i).isDisponibilidade()) {
                caminhoes.get(i).setDisponibilidade(false);
                return true;
            }
        }
        return false;
    }

    public void verificarLixeiras() {
        Random rand = new Random();
        for (int i = 0; i < lixeiras.size(); i++) {
            if (rand.nextInt(3) == 0) {
                lixeiras.get(i).jogarNaLixeira(); // Simulando o sensor.
                lixeiras.get(i).verificarLixeira();
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

    public void verificarReguladoresPh() {
        Random rand = new Random();
        for (int i = 0; i < reguladoresPH.size(); i++) {
            if (rand.nextInt(3) == 0) {
                reguladoresPH.get(i).verificaPH(); // Simulando o sensor.
                reguladoresPH.get(i).testarPH();
            }
        }

    }

    public void verificarColeta() {
        for (int i = 0; i < coletas.size(); i++) {
            if (coletas.get(i).getMinutos() == this.minuto && coletas.get(i).getHora() == this.hora && coletas.get(i).EhDiaDaColeta(dia)) {
                //Criar Funcao de realizacao da Coleta.
                // coletas.get(i).realizarColeta();
            }
        }
    }

    public void addFuncionario(){
        funcionarios.add(new Funcionario("João"));
    }

    public void addEquipe(){
        if(funcionarios.size()>0) {
            Random random = new Random();
            Funcionario[] func = new Funcionario[];
            func[0] = funcionarios.get(random.nextInt(funcionarios.size()));
            func[1] = funcionarios.get(random.nextInt(funcionarios.size()));
            func[2] = funcionarios.get(random.nextInt(funcionarios.size()));
            equipes.add(new Equipe(func));
        }
        
    }

    public void recalculaTempo() {

        if (this.minuto == 59 && this.hora == 23) {
            this.dia++;
            this.hora = 0;
            this.minuto = 0;
        } else if (this.minuto == 59) {
            this.hora++;
            this.minuto = 0;
        } else {
            this.minuto++;
        }
        System.out.println("Dia: " + this.dia);
        System.out.println("Hora: " + this.hora);
        System.out.println("Minuto :" + this.minuto);
    }

    public void lacoDeControle() {

        boolean fim = false;

        while (!fim) {

            recalculaTempo();

            verificarLixeiras();

            verificarReguladoresPh();

            verificarColeta();
        }
    }
}
