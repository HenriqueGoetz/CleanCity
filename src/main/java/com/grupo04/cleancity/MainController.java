package com.grupo04.cleancity;

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
import modelagem.cleancity.*;
import netscape.javascript.JSObject;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class MainController implements Initializable, Schedulable {

    private Scheduler scheduler;

    @FXML
    WebView mapViewer;
    @FXML
    Label lblMin;
    @FXML
    Label lblHora;
    @FXML
    Label lblDia;

    private static List<Lixeira> lixeiras = new ArrayList<>();
    private List<Lixeira> lixeirasCheias = new ArrayList<>();
    private List<ReguladorPh> reguladoresPH = new ArrayList<>();
    private List<Caminhao> caminhoes = new ArrayList<>();
    private List<Coleta> coletas = new ArrayList<>();
    private List<Equipe> equipes = new ArrayList<>();
    private List<Funcionario> funcionarios = new ArrayList<>();

    private int hora = 0;
    private int minuto = 0;
    private int dia = 0;

    JavaApp app = new JavaApp();

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

                /* Casos de teste
        DiaDaSemana[] dias1 = new DiaDaSemana[3];
        DiaDaSemana[] dias2 = new DiaDaSemana[3];

        dias1[0] = SEG;
        dias1[1] = QUA;
        dias1[2] = SEX;
        dias2[0] = DOM;
        dias2[1] = TER;
        dias2[2] = QUI;

        addCaminhao();
        addCaminhao();
        addCaminhao();
        addCaminhao();

        System.out.println("Caminhoes criados.");

        addFuncionario("Joao");
        addFuncionario("Joaguim");
        addFuncionario("Maria");
        addFuncionario("Pedro");
        addFuncionario("Marcos");

        System.out.println("Funcionários criados.");

        addEquipe(0);
        addEquipe(1);
        addEquipe(2);

        System.out.println("Equipes criadas.");

        addColeta(6, 30, dias1);
        addColeta(12, 00, dias1);
        addColeta(18, 30, dias1);
        addColeta(6, 30, dias2);
        addColeta(12, 00, dias2);
        addColeta(18, 30, dias2);

        System.out.println("Coletas criadas");

        */
    }


    public static Lixeira getLixeiraById(int id) {
        for (Lixeira lixeira : lixeiras) {
            if (lixeira.getId() == id) {
                return lixeira;
            }
        }
        return null;
    }

    public void addLixeira(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
            mapViewer.getEngine().executeScript("adicionarLixeira(new google.maps.LatLng())");

            Coordenada coordenada = app.getCoordenadaRecebida();

            lixeiras.add(new Lixeira(coordenada.getLatitude(), coordenada.getLongitude(), app.getIdRecebido()));
            System.out.println("Lixeira Adicionada com sucesso.");
        }
    }

    public static void reposicionaLixeira(Lixeira lixeira, Coordenada coord) {
        lixeira.getCoord().setLatitude(coord.getLatitude());
        lixeira.getCoord().setLongitude(coord.getLongitude());
    }

    private void addCaminhao() {
        caminhoes.add(new Caminhao());
    }

    public void onAddColetaClick(ActionEvent event) {
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
            coletas.add(new Coleta(hora, min, dias, equipes.get(random.nextInt(equipes.size()))));
        } else {
            System.out.println("Não há caminhões disponíveis.");
        }
    }

    private boolean haCaminhoesDisponiveis() {
        for (Caminhao caminhoe : caminhoes) {
            if (caminhoe.isDisponibilidade()) {
                caminhoe.setDisponibilidade(false);
                return true;
            }
        }
        return false;
    }

    private void verificarLixeiras() {
        Random rand = new Random();
        for (Lixeira lixeira : lixeiras) {
            if (rand.nextInt(3) == 0) {
                lixeira.jogarNaLixeira(); // Simulando o sensor.
                if (lixeira.verificarLixeira()) {
                    lixeirasCheias.add(lixeira);
                }
            }
        }
    }

    public void removeLixeira(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
            //mapViewer.getEngine().executeScript("removeMarcador = true");

            mapViewer.getEngine().executeScript("marcadorSel.setMap(null);");
            mapViewer.getEngine().executeScript("marcadorSel =  null;");

            int indice = lixeiras.indexOf(getLixeiraById(app.getIdRecebido()));
            lixeiras.remove(indice);
            System.out.println("Lixeira Removida com sucesso.");
        }
    }

    private void verificarReguladoresPh() {
        Random rand = new Random();
        for (ReguladorPh aReguladoresPH : reguladoresPH) {
            if (rand.nextInt(3) == 0) {
                aReguladoresPH.verificaPH(); // Simulando o sensor.
                aReguladoresPH.testarPH();
            }
        }

    }

    private void verificarColeta() {
        for (Coleta coleta : coletas) {
            if (coleta.getMinutos() == this.minuto && coleta.getHora() == this.hora && coleta.EhDiaDaColeta(dia)) {
                realizarColeta();
            }
        }
    }

    public void onAddFuncionarioClick(ActionEvent event) {
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
        funcionarios.add(new Funcionario(nome));
    }

    public void onAddEquipeClick(ActionEvent event) {
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
        if (funcionarios.size() > 2) { //Existe pelo menos 3 funcionários
            // Sorteia 3 funcionarios e cria uma equipe com os sorteados.
            Random random = new Random();
            Funcionario[] func = new Funcionario[3];

            do {
                func[0] = funcionarios.get(random.nextInt(funcionarios.size()));
                func[1] = funcionarios.get(random.nextInt(funcionarios.size()));
                func[2] = funcionarios.get(random.nextInt(funcionarios.size()));
            } while (func[0] == func[1] || func[1] == func[2] || func[0] == func[2]);

            equipes.add(new Equipe(func, id));
        } else {
            System.out.println("Não há funcionários suficientes para criar uma equipe.");
        }

    }

    private void realizarColeta() {
        this.lixeirasCheias.clear();
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
        System.out.println("Dia: " + this.dia);
        System.out.println("Hora: " + this.hora);
        System.out.println("Minuto :" + this.minuto);
    }

    public void imprimeTempo() {
        //Atualizar o tempo na janela.
        lblMin.setText(String.valueOf(this.minuto));
        lblHora.setText(String.valueOf(this.hora));
        lblDia.setText(String.valueOf(this.dia));

    }

    @Override
    public void loop(ActionEvent event) {
        recalculaTempo();
        imprimeTempo();
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
