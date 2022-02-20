package jogo.ui.GUI;

import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import jogo.logica.JogoObservavel;
import jogo.ui.GUI.estados.*;
import jogo.ui.GUI.resources.CSSManager;

import static jogo.ui.GUI.constantesGUI.*;

public class PrincipalPane extends BorderPane{
    private JogoObservavel jogoObservavel;
    private Label tabuleiro;
    HBox cp,cp2;


    public PrincipalPane(JogoObservavel jogoObservavel) {
        this.jogoObservavel = jogoObservavel;
        CSSManager.setCSS(this,"styling.css");
        criarVista();

    }

    private void criarVista(){

        setStyle("-fx-background-color: linear-gradient(to top, #2c3e50, #2980b9);");

//       setMaxSize(DIM_X_FRAME, DIM_Y_FRAME);
        setPrefSize(DIM_X_FRAME, DIM_Y_FRAME);
        setMinSize(DIM_X_FRAME, DIM_Y_FRAME);

       // setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID,
              //  null, new BorderWidths(2))));

        // BOX ESQUERDA
        tabuleiro =new Label("Tabuleiro");



        tabuleiroPane tabuleiroPane = new tabuleiroPane(jogoObservavel);
        cp = new ColunasPane(jogoObservavel);
        cp.setVisible(false);
        cp2 = new ColunasPanePeca(jogoObservavel);
        cp2.setVisible(false);
        VBox leftBox = new VBox();

        leftBox.setPrefSize(LEFT_VBOX_X, LEFT_VBOX_Y);
        leftBox.setMinSize(LEFT_VBOX_X, LEFT_VBOX_Y);

        leftBox.getChildren().addAll(tabuleiro,tabuleiroPane, cp, cp2);
        //leftBox.setBorder(new Border(new BorderStroke(Color.ORANGE, BorderStrokeStyle.SOLID,
          //      null, new BorderWidths(2))));

        setLeft(leftBox);


        StartPane startpane = new StartPane(jogoObservavel);
        EndPane endpane = new EndPane(jogoObservavel);
        GamemodePane gamemodepane = new GamemodePane(jogoObservavel);
        IDPane idpane = new IDPane(jogoObservavel);
        Player0Pane p0pane = new Player0Pane(jogoObservavel,cp,cp2);
        Player1Pane p1pane = new Player1Pane(jogoObservavel,cp,cp2);
        esperaMiniPane emp = new esperaMiniPane(jogoObservavel);
        MiniJogoFrasePane mf = new MiniJogoFrasePane(jogoObservavel);
        MiniJogoMatPane mm = new MiniJogoMatPane(jogoObservavel);



        StackPane rightBox = new StackPane(startpane, endpane, gamemodepane, idpane, p0pane, p1pane,emp,mf,mm);

        rightBox.setPrefSize(RIGHT_VBOX_X , RIGHT_VBOX_Y );
        rightBox.setMinSize(RIGHT_VBOX_X , RIGHT_VBOX_Y );

        setRight(rightBox);

    }
}
