package com.grupo04.cleancity;

import com.grupo04.cleancity.data.Database;
import com.grupo04.cleancity.scheduler.Schedulable;
import com.grupo04.cleancity.scheduler.Scheduler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import com.grupo04.cleancity.model.DiaDaSemana;
import com.grupo04.cleancity.model.dispositivos.Caminhao;
import com.grupo04.cleancity.model.dispositivos.Lixeira;
import com.grupo04.cleancity.model.dispositivos.ReguladorPh;
import com.grupo04.cleancity.model.equipe.Coleta;
import com.grupo04.cleancity.model.equipe.Equipe;
import com.grupo04.cleancity.model.equipe.Funcionario;
import com.grupo04.cleancity.model.mapa.Coordenada;
import netscape.javascript.JSObject;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class MainController implements Initializable, Schedulable {

    @FXML
    WebView mapViewer;
    @FXML
    Label lblMin;
    @FXML
    Label lblHora;
    @FXML
    Label lblDia;
    @FXML
    Label lblLixeiras;
    @FXML
    Label lblLixeirasCheias;
    @FXML
    Label lblFuncionarios;
    @FXML
    Label lblEquipes;
    @FXML
    Label lblCaminhoes;
    @FXML
    Label lblColetas;
    @FXML
    Label lblReguladoresPh;

    private int hora = 0;
    private int minuto = 0;
    private int dia = 0;

    private Scheduler scheduler;
    private JavaApp app = new JavaApp();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        final WebEngine webEngine = mapViewer.getEngine();
        String path = new File("src/main/resources/html/mapa.html").getAbsolutePath();
        String contents;

        try {
            contents = new String(Files.readAllBytes(Paths.get(path)));
            webEngine.loadContent(contents);
            JSObject window = (JSObject) webEngine.executeScript("window");
            window.setMember("app", app);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.scheduler = new Scheduler(this);
        scheduler.start();

    }

    public void onAddReguladorClick(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
            mapViewer.getEngine().executeScript("adicionarRegulador()");

            Coordenada coordenada = app.getCoordenadaRecebida();

            Database.getInstance().addRegulador(new ReguladorPh(coordenada.getLatitude(), coordenada.getLongitude(), app.getIdRecebido()));
            System.out.println("Regulador Adicionado com sucesso.");
        }
    }

    public void onRemoveReguladorClick(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {

            mapViewer.getEngine().executeScript("marcadorSel.setMap(null);");
            //mapViewer.getEngine().executeScript("reguladores.splice(reguladores.indexOf(marcadorSel), 1);");
            mapViewer.getEngine().executeScript("marcadorSel =  null;");

            Database.getInstance().removerRegulador(Database.getInstance().getReguladorById(app.getIdRecebido()));
            System.out.println("Regulador Removido com sucesso.");
        }
    }


    public void addLixeira(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
            mapViewer.getEngine().executeScript("adicionarLixeira(new google.maps.LatLng())");

            Coordenada coordenada = app.getCoordenadaRecebida();

            Database.getInstance().addLixeira(new Lixeira(coordenada.getLatitude(), coordenada.getLongitude(), app.getIdRecebido()));
            System.out.println("Lixeira Adicionada com sucesso.");
        }
    }

    static void reposicionaLixeira(Lixeira lixeira, Coordenada coord) {
        lixeira.getCoord().setLatitude(coord.getLatitude());
        lixeira.getCoord().setLongitude(coord.getLongitude());
    }

    static void reposicionaRegulador(ReguladorPh regulador, Coordenada coord) {
        regulador.getCoord().setLatitude(coord.getLatitude());
        regulador.getCoord().setLongitude(coord.getLongitude());
    }

    private void addCaminhao() {
        Dialog<String[]> dialog = new Dialog<>();
        dialog.setTitle("Adicionar Caminhão");
        dialog.setHeaderText("Especifique a capacidade:");

        ButtonType addButton = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButton, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField vol = new TextField();
        vol.setPromptText("Volume Máximo");
        setIntegerOnly(vol);
        TextField peso = new TextField();
        peso.setPromptText("Peso Máximo");
        setIntegerOnly(peso);

        grid.add(new Label( "Volume Max (L)"), 0, 0);
        grid.add(vol, 1, 0);
        grid.add(new Label("Peso Max (Kg)"), 0, 1);
        grid.add(peso, 1, 1);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButton) {
                return new String[]{vol.getText(), peso.getText()};
            }
            return null;
        });

        Optional<String[]> result = dialog.showAndWait();

        if (result.isPresent() && !result.get()[0].isEmpty() && !result.get()[1].isEmpty()) {
            Database.getInstance().addCaminhao(new Caminhao(Float.valueOf(result.get()[0]), Float.valueOf(result.get()[1])));
        }
    }

    public void onAddCaminhaoClick() {
        addCaminhao();
    }
    public void onAddColetaClick() {
        Dialog<String[]> dialog = new Dialog<>();
        dialog.setTitle("Adicionar Coleta");
        dialog.setHeaderText("Especifique os horários");

        ButtonType addButton = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButton, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField hora = new TextField();
        hora.setPromptText("Hora");
        setIntegerOnly(hora);
        TextField minutos = new TextField();
        minutos.setPromptText("Minutos");
        setIntegerOnly(minutos);
        TextField dias = new TextField();
        dias.setPromptText("Dias");
        setIntegerListOnly(dias);

        grid.add(new Label("Hora:"), 0, 0);
        grid.add(hora, 1, 0);
        grid.add(new Label("Minutos:"), 0, 1);
        grid.add(minutos, 1, 1);
        grid.add(new Label("Dias (1-7, separados por virgulas):"), 0, 2);
        grid.add(dias, 1, 2);

        Node nodeAddButton = dialog.getDialogPane().lookupButton(addButton);
        nodeAddButton.setDisable(true);

        hora.textProperty().addListener(value -> inputColetaChecker(hora, minutos, dias, nodeAddButton));
        minutos.textProperty().addListener(value -> inputColetaChecker(hora, minutos, dias, nodeAddButton));
        dias.textProperty().addListener(value -> inputColetaChecker(hora, minutos, dias, nodeAddButton));

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButton) {
                return new String[]{hora.getText(), minutos.getText(), dias.getText()};
            }
            return null;
        });

        Optional<String[]> result = dialog.showAndWait();

        if (result.isPresent()) {
            int h = Integer.valueOf(result.get()[0]);
            int m = Integer.valueOf(result.get()[1]);
            String[] diasString = result.get()[2].split(",");
            DiaDaSemana[] d = new DiaDaSemana[diasString.length];

            int i = 0;
            for (String s : diasString) {
                d[i++] = DiaDaSemana.fromInteger(Integer.valueOf(s));
            }

            addColeta(h, m, d);
        }
    }

    private void inputColetaChecker(TextField hora, TextField min, TextField dias, Node btn) {
        if (!hora.getText().isEmpty() && !min.getText().isEmpty() &&
                dias.getText().matches("[1-7] *([,] *[1-7] *)*")) {
            btn.setDisable(false);
        } else {
            btn.setDisable(true);
        }
    }

    private void addColeta(int hora, int min, DiaDaSemana[] dias) {
        if (haCaminhoesDisponiveis()) { // Verifica se existe caminhão disponível para coleta.
            // Sorteia uma equipe para realizar a coleta.
            Random random = new Random();
            Database.getInstance().addColeta(new Coleta(hora, min, dias,
                    Database.getInstance().getEquipes().get(random.nextInt(Database.getInstance().getEquipes().size())), selecionaCaminhaoDisponivel()));
        } else {
            System.out.println("Não há caminhões disponíveis.");
        }
    }

    public Caminhao selecionaCaminhaoDisponivel(){
        for(Caminhao caminhoes : Database.getInstance().getCaminhoes()){
            if(caminhoes.isDisponivel()){
                caminhoes.setDisponivel(false);
                return caminhoes;
            }
        }
        return null;
    }

    private boolean haCaminhoesDisponiveis() {
        for (Caminhao caminhoe : Database.getInstance().getCaminhoes()) {
            if (caminhoe.isDisponivel()) {
                caminhoe.setDisponivel(false);
                return true;
            }
        }
        return false;
    }

    private boolean jaEstaNaListaDeCheias(Lixeira lix){

        for(Lixeira lixeira : Database.getInstance().getLixeirasCheias()){
            if(lixeira.equals(lix)){
                return true;
            }
        }

        return false;
    }

    private void verificarLixeiras() {
        Random rand = new Random();
        for (Lixeira lixeira : Database.getInstance().getLixeiras()) {
            if (rand.nextInt(3) == 0) {
                lixeira.jogarNaLixeira(); // Simulando o sensor.
                if (lixeira.verificarLixeira() && !jaEstaNaListaDeCheias(lixeira)) {
                    Database.getInstance().getLixeirasCheias().add(lixeira);
                }
            }
        }
    }

    public void removeLixeira(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
            //mapViewer.getEngine().executeScript("removeMarcador = true");

            mapViewer.getEngine().executeScript("marcadorSel.setMap(null);");
            //mapViewer.getEngine().executeScript("reguladores.splice(reguladores.indexOf(marcadorSel), 1);");
            mapViewer.getEngine().executeScript("marcadorSel =  null;");

            Database.getInstance().removerLixeira(Database.getInstance().getLixeiraById(app.getIdRecebido()));
            System.out.println("Lixeira Removida com sucesso.");
        }
    }

    private void verificarReguladoresPh() {
        Random rand = new Random();
        for (ReguladorPh aReguladoresPH : Database.getInstance().getReguladoresPH()) {
            if (rand.nextInt(3) == 0) {
                aReguladoresPH.verificaPH(); // Simulando o sensor.
                aReguladoresPH.testarPH();
            }
        }

    }

    private void verificarColeta() {
        for (Coleta coleta : Database.getInstance().getColetas()) {
            if (coleta.getMinutos() == this.minuto && coleta.getHora() == this.hora && coleta.EhDiaDaColeta(this.dia)) {
                System.out.println("HORA DA COLETA");
                realizarColeta(selecionarLixeiras(coleta.getCaminhao()));
            }
        }
    }

    public List<Lixeira> selecionarLixeiras(Caminhao caminhao){
        int indice = 0;
        List<Lixeira> selecionadas = new ArrayList<>();
        boolean couber = true;
        while(couber){
            Lixeira lix = Database.getInstance().getLixeirasCheias().get(indice);
            if(lix.getVolume() + caminhao.getLeituraVolume() > 0.8*(caminhao.getCapacidade().getVolume()) && lix.getPeso() + caminhao.getLeituraBalanca() > 0.8*(caminhao.getCapacidade().getPeso())){
                selecionadas.add(lix);
            }else{
                couber = false;
            }
        }
        return selecionadas;
    }

    public void onAddFuncionarioClick() {
        TextInputDialog inputDialog = new TextInputDialog();
        inputDialog.setTitle("Adicionar Funcionário");
        inputDialog.setHeaderText("Digite o nome do funcionário:");
        inputDialog.getEditor().setPromptText("Nome");

        inputDialog.showAndWait().ifPresent(name -> {
            if (!name.isEmpty())
                addFuncionario(name);
        });
    }

    private void addFuncionario(String nome) {
        Database.getInstance().addFuncionario(new Funcionario(nome));
    }

    public void onAddEquipeClick() {
        TextInputDialog inputDialog = new TextInputDialog();
        inputDialog.setTitle("Adicionar Equipe");
        inputDialog.setHeaderText("Digite ID (número inteiro) da equipe. Três funcionários aleatórios serão alocado para a equipe.");
        inputDialog.getEditor().setPromptText("ID da Equipe");
        setIntegerOnly(inputDialog.getEditor());

        inputDialog.showAndWait().ifPresent(id -> {
            if (!id.isEmpty())
                addEquipe(Integer.parseInt(id));
        });
    }

    private void addEquipe(int id) {
        List<Funcionario> funcionarios = Database.getInstance().getFuncionarios();
        if (funcionarios.size() > 2) { //Existe pelo menos 3 funcionários
            // Sorteia 3 funcionarios e cria uma equipe com os sorteados.
            Random random = new Random();
            Funcionario[] func = new Funcionario[3];

            do {
                func[0] = funcionarios.get(random.nextInt(funcionarios.size()));
                func[1] = funcionarios.get(random.nextInt(funcionarios.size()));
                func[2] = funcionarios.get(random.nextInt(funcionarios.size()));
            } while (func[0] == func[1] || func[1] == func[2] || func[0] == func[2]);

            Database.getInstance().addEquipe(new Equipe(func, id));
        } else {
            System.out.println("Não há funcionários suficientes para criar uma equipe.");
        }

    }

    private void realizarColeta(List<Lixeira> lixeiras) {
        for (Lixeira lix : lixeiras) {
            Database.getInstance().getLixeirasCheias().remove(lix);
        }
    }
    private void recalculaTempo() {

        if (this.minuto == 59 && this.hora == 23) {
            if (this.dia != 6)
                this.dia++;
            else
                this.dia = 0;
            this.hora = 0;
            this.minuto = 0;
        } else if (this.minuto == 59) {
            this.hora++;
            this.minuto = 0;
        } else {
            this.minuto++;
        }

    }

    private void imprimeTempo() {
        //Atualizar o tempo na janela.
        lblMin.setText(String.valueOf(this.minuto));
        lblHora.setText(String.valueOf(this.hora));
        lblDia.setText(String.valueOf(this.dia));
    }

    private void recalculaDados(){
        lblLixeiras.setText(String.valueOf(Database.getInstance().getLixeiras().size()));
        lblLixeirasCheias.setText(String.valueOf(Database.getInstance().getLixeirasCheias().size()));
        lblFuncionarios.setText(String.valueOf(Database.getInstance().getFuncionarios().size()));
        lblEquipes.setText(String.valueOf(Database.getInstance().getEquipes().size()));
        lblCaminhoes.setText(String.valueOf(Database.getInstance().getCaminhoes().size()));
        lblColetas.setText(String.valueOf(Database.getInstance().getColetas().size()));
        lblReguladoresPh.setText(String.valueOf(Database.getInstance().getReguladoresPH().size()));
    }
    @Override
    public void loop(ActionEvent event) {
        recalculaTempo();
        imprimeTempo();
        recalculaDados();
        verificarLixeiras();
        verificarReguladoresPh();
        verificarColeta();
    }

    private void setIntegerOnly(TextInputControl input) {
        input.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                input.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

    private void setIntegerListOnly(TextInputControl input) {
        input.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("[1-7 ,]*")) {
                input.setText(newValue.replaceAll("[^1-7 ,]", ""));
            }
        });
    }
}
