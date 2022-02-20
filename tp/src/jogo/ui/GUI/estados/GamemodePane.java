
package jogo.ui.GUI.estados;

        import javafx.geometry.Pos;
        import javafx.scene.control.Button;
        import javafx.scene.control.Label;


        import javafx.scene.layout.VBox;
        import javafx.scene.paint.Color;
        import jogo.logica.JogoObservavel;

        import static jogo.logica.situation.modoJogo;
        //import static jogo.ui.GUI.constantesGUI.LETRA;

public class GamemodePane extends VBox {


    private JogoObservavel jogoObservavel;

    private Button PVPButton;
    private Button PVCButton;
    private Button CVCButton;
    private Label menuLabel;


    public GamemodePane(JogoObservavel jogoObservavel) {
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
        menuLabel = new Label("Escolha um modo de jogo");
        //menuLabel.setFont(LETRA);
        menuLabel.setTextFill(Color.WHITE);
        PVPButton = new Button("PvP");
        PVCButton = new Button("PvC");
        CVCButton = new Button("CvC");
        //PVPButton.setFont(LETRA);
        //PVCButton.setFont(LETRA);
        //CVCButton.setFont(LETRA);

        setAlignment(Pos.CENTER);
        setSpacing(10);
        getChildren().addAll(menuLabel, PVPButton, PVCButton,CVCButton);
        PVPButton.setOnAction((e)->jogoObservavel.modoJogo(1));
        PVCButton.setOnAction((e)->jogoObservavel.modoJogo(2));
        CVCButton.setOnAction((e)->jogoObservavel.modoJogo(3));

    }
    private void atualiza() {

        this.setVisible(jogoObservavel.getSituation() == modoJogo );

    }
}