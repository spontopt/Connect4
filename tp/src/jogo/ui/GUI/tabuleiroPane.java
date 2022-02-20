package jogo.ui.GUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import jogo.logica.JogoObservavel;
import jogo.ui.GUI.resources.ImageLoader;



import static jogo.ui.GUI.constantesGUI.*;

public class tabuleiroPane extends GridPane {

    private JogoObservavel jogoObservavel;

    public tabuleiroPane(JogoObservavel jogoObservavel) {
        this.jogoObservavel = jogoObservavel; this.
                criarVista();
                registarObservador();
                updateTab();

    }

    private void registarObservador(){
        // regista um observador do jogoObservavel
        jogoObservavel.addPropertyChangeListener("jjjj", evt -> {
            updateTab();
        });
    }

    private void criarVista(){

        setPrefSize(250, 300);


        setBackground(new Background(
                new BackgroundFill(Color.web("#859398"), new CornerRadii(20), null)));

        this.setPadding(new Insets(10, 10, 10, 10));
        this.setAlignment(Pos.CENTER);
        this.setHgap(GAP_X_BOLAS);
        this.setVgap(GAP_Y_BOLAS);
    }

    private void updateTab() {

        char[][] tab = jogoObservavel.getTab();
        if(tab==null)
            jogoObservavel.start(0);


        tab = jogoObservavel.getTab();

        this.getChildren().clear();




        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[0].length; j++) {

                ImageView imageView = new ImageView();
                imageView.setFitHeight(40);
                imageView.setFitWidth(40);
                Image imagem = null;
                if (tab[i][j]=='0') {
                    imagem = ImageLoader.getImage(BOLA_AMARELA);
                } else if (tab[i][j]=='X') {
                    imagem = ImageLoader.getImage(BOLA_VERMELHA);
                }else if(tab[i][j]=='_')
                    imagem = ImageLoader.getImage(BOLA_BRANCA);
                if (imagem != null) {
                    imageView.setImage(imagem);
                } else {
                    System.err.println(" imagem = null");
                }
                add(imageView, j, i);

            }
        }

    }
}
