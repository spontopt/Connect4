package jogo.ui.GUI.estados;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import jogo.logica.JogoObservavel;

import java.io.File;

import static jogo.logica.situation.inicio;


public class StartPane extends VBox {


    private JogoObservavel jogoObservavel;

    private Button startButton;
    private Button replayButton;
    private Label menuLabel;


    public StartPane(JogoObservavel jogoObservavel) {
        this.jogoObservavel = jogoObservavel;
        criarVista();
        registarObservador();
        atualiza();


    }
    private void registarObservador(){
        // regista um observador do jogoObservavel
        jogoObservavel.addPropertyChangeListener("jjjj", evt -> {
            atualiza();
        });
    }
    private void criarVista(){
        menuLabel = new Label("Menu inicial");
        menuLabel.setTextFill(Color.WHITE);
        startButton = new Button("Start");
        replayButton = new Button("Replay");


        setAlignment(Pos.CENTER);
        setSpacing(10);
        getChildren().addAll(menuLabel, startButton, replayButton);
        startButton.setOnAction((e)->jogoObservavel.start(1));
        replayButton.setOnAction((e)->{
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(new File("./replays"));
            File selectedFile = fileChooser.showOpenDialog(null);
            if (selectedFile != null) {
                jogoObservavel.loadF(selectedFile);
            } else {
                System.err.println("Leitura cancelada ");
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("Ficheiro Inválido");
                a.show();
            }
            jogoObservavel.reset();
            jogoObservavel.setmodo(4);
            jogoObservavel.start(0);
        });

    }


    private void atualiza() {
        this.setVisible(jogoObservavel.getSituation() ==  inicio );
    }



}