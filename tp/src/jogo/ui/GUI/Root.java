package jogo.ui.GUI;



import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import jogo.logica.JogoObservavel;

import java.io.File;

import static jogo.ui.GUI.constantesGUI.*;

public class Root extends BorderPane {

    private JogoObservavel jogoObservavel;
    private PrincipalPane principalPane;
    private MenuItem novoJogoMI;


    public Root(JogoObservavel jogoObservavel) {
        this.jogoObservavel = jogoObservavel;
        menus();
        criarVistaCentral();


    }

    private void criarVistaCentral(){

        principalPane = new PrincipalPane(jogoObservavel);
        setCenter(principalPane);
    }

    private void menus() {
        MenuBar menuBar = new MenuBar();
        setTop(menuBar);

        // menu Jogo
        Menu jogoMenu = new Menu("_Jogo");  // underscore: abre com alt + j

        MenuItem lerObjMI = new MenuItem("Ler jogo");
        lerObjMI.setAccelerator(new KeyCodeCombination(KeyCode.L, KeyCombination.CONTROL_DOWN));

        MenuItem gravarObjMI = new MenuItem("Gravar");
        gravarObjMI.setAccelerator(new KeyCodeCombination(KeyCode.G, KeyCombination.CONTROL_DOWN));

        MenuItem sairMI = new MenuItem("Sair");
        sairMI.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));

        jogoMenu.getItems().addAll( lerObjMI, gravarObjMI,new SeparatorMenuItem(), sairMI);



        lerObjMI.setOnAction(new LerObjMenuBarListener());

        gravarObjMI.setOnAction(new GravarObjMenuBarListener());

        sairMI.setOnAction((ActionEvent e)-> {
            Stage janela2 = (Stage) this.getScene().getWindow();
            fireEvent( new WindowEvent(janela2, WindowEvent.WINDOW_CLOSE_REQUEST));
        });


        // menu ajuda
        Menu ajudaMenu = new Menu("Sobre");

        MenuItem LogsMI = new MenuItem("Logs do jogo");
        LogsMI.setAccelerator(new KeyCodeCombination(KeyCode.J, KeyCombination.CONTROL_DOWN));

        MenuItem acercaMI = new MenuItem("Autor");
        acercaMI.setAccelerator(new KeyCodeCombination(KeyCode.A, KeyCombination.CONTROL_DOWN));

        ajudaMenu.getItems().addAll( LogsMI,acercaMI);

        LogsMI.setOnAction(new LogsListener());
        acercaMI.setOnAction(new AcercaListener());

        menuBar.getMenus().addAll(jogoMenu,ajudaMenu);
    }
    class LerObjMenuBarListener implements EventHandler<ActionEvent>  {
        @Override
        public void handle(ActionEvent e) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(new File("./jogosgravados"));
            File selectedFile = fileChooser.showOpenDialog(null);
            if (selectedFile != null) {
                jogoObservavel.ler(selectedFile);
            } else {
                System.err.println("Leitura cancelada ");
            }
        }
    }

    class GravarObjMenuBarListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Binary files (.bin)", ".bin");
            fileChooser.getExtensionFilters().add(extFilter);
            fileChooser.setInitialDirectory(new File("./jogosgravados"));

            File selectedFile = fileChooser.showSaveDialog(null);
            if (selectedFile != null) {
                jogoObservavel.gravar(selectedFile);
            } else {
                System.err.println("Gravacao cancelada ");
            }
        }
    }


    class LogsListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {
            Alert dialogoResultado = new Alert(Alert.AlertType.INFORMATION);
            dialogoResultado.setHeaderText("Logs");
            dialogoResultado.setContentText(String.join(",",jogoObservavel.logs()));
            dialogoResultado.showAndWait();
        }
    }
    class AcercaListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {
            Alert dialogoResultado = new Alert(Alert.AlertType.INFORMATION);
            dialogoResultado.setHeaderText("Autor");
            dialogoResultado.setContentText("Disciplina de Programacao Avancada\nRealizado por: Samuel Tavares \nN: 2019126468");

            dialogoResultado.showAndWait();
        }
    }

}
