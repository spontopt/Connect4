
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

        import static jogo.logica.situation.esperamini;
        import static jogo.logica.situation.inicio;


public class esperaMiniPane extends VBox {


    private JogoObservavel jogoObservavel;

    private Button sim;
    private Button nao;
    private Label menuLabel;



    public esperaMiniPane(JogoObservavel jogoObservavel) {
        this.jogoObservavel = jogoObservavel;
        criarVista();
        registarObservador();
        atualiza();


    }

    private void registarObservador() {
        // regista um observador do jogoObservavel
        jogoObservavel.addPropertyChangeListener("jjjj", evt -> {
            atualiza();
        });
    }

    private void criarVista() {
        menuLabel = new Label("Menu inicial");
        menuLabel.setTextFill(Color.WHITE);
        sim = new Button("Aceitar");
        nao = new Button("Recusar");


        setAlignment(Pos.CENTER);
        setSpacing(10);
        getChildren().addAll(menuLabel, sim, nao);
        sim.setOnAction((e) -> jogoObservavel.escolheM(true));
        nao.setOnAction((e) -> jogoObservavel.escolheM(false));


    }


    private void atualiza() {
        this.setVisible(jogoObservavel.getSituation() == esperamini);
    }

}

