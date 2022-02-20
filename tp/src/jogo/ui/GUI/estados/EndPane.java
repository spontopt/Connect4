
package jogo.ui.GUI.estados;


        import javafx.geometry.Pos;
        import javafx.scene.control.Button;
        import javafx.scene.control.Label;


        import javafx.scene.image.Image;
        import javafx.scene.image.ImageView;
        import javafx.scene.layout.VBox;
        import javafx.scene.paint.Color;
        import jogo.logica.JogoObservavel;
        import jogo.ui.GUI.resources.ImageLoader;

        import static javafx.application.Platform.exit;
        import static jogo.logica.situation.fim;
        import static jogo.ui.GUI.constantesGUI.TROPHY;


public class EndPane extends VBox {


    private JogoObservavel jogoObservavel;

    private Button startButton;
    private Button sair;
    private Label menuLabel;
    private Label playerLabel;
    private ImageView imageV;

    public EndPane(JogoObservavel jogoObservavel) {
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

        menuLabel = new Label("Jogo Terminado");
        playerLabel = new Label();
        startButton = new Button("Re-Start");
        sair = new Button("Sair");
        ImageView imageView = new ImageView();
        imageView.setFitHeight(80);
        imageView.setFitWidth(80);
        Image imagem = ImageLoader.getImage(TROPHY);
        imageV = new ImageView(imagem);

        setAlignment(Pos.CENTER);
        setSpacing(10);


    }


    private void atualiza() {
        this.setVisible(jogoObservavel.getSituation() ==  fim );
        if(menuLabel!=null)
            getChildren().removeAll(menuLabel,playerLabel,startButton,sair,imageV);
        if(jogoObservavel.getSituation()==fim) {

            if (jogoObservavel.getp().getlet() == '0') {
                playerLabel.setText("PLAYER 1");
            } else {
                playerLabel.setText("PLAYER 2");
            }
            if(jogoObservavel.getmodo()!=4)
                jogoObservavel.autosave();

            getChildren().addAll(imageV, playerLabel, menuLabel, startButton, sair);
            startButton.setOnAction((e) -> jogoObservavel.endgame());
            sair.setOnAction((e)->exit());
        }
    }


}