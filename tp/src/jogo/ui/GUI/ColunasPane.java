
package jogo.ui.GUI;

        import javafx.geometry.Insets;
        import javafx.geometry.Pos;

        import javafx.scene.control.Button;
        import javafx.scene.layout.*;
        import javafx.scene.paint.Color;
        import jogo.logica.JogoObservavel;

        import static jogo.logica.situation.jogador0;
        import static jogo.logica.situation.jogador1;
        import static jogo.ui.GUI.constantesGUI.*;

    public class ColunasPane extends HBox {

    private JogoObservavel jogoObservavel;

        private Button b1;
        private Button b2;
        private Button b3;
        private Button b4;
        private Button b5;
        private Button b6;
        private Button b7;


    public ColunasPane(JogoObservavel jogoObservavel) {
        this.jogoObservavel = jogoObservavel; this.criarVista();
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

        setPrefSize(50, 50);
        setMaxHeight(50);



        b1 = new Button("1");b1.setId("buttonCol");
        b2 = new Button("2");b2.setId("buttonCol");
        b3 = new Button("3");b3.setId("buttonCol");
        b4 = new Button("4");b4.setId("buttonCol");
        b5 = new Button("5");b5.setId("buttonCol");
        b6 = new Button("6");b6.setId("buttonCol");
        b7 = new Button("7");b7.setId("buttonCol");

       // this.setPadding(new Insets(1, 20, 0, 20));
        getChildren().addAll(b1,b2,b3,b4,b5,b6,b7);
        setSpacing(18);
        this.setAlignment(Pos.CENTER);

    }



    private void updateTab() {
        this.setVisible(false);
        if(jogoObservavel.getSituation()==jogador0){

            b1.setOnAction((e)->jogoObservavel.p0(1,1));
            b2.setOnAction((e)->jogoObservavel.p0(2,1));
            b3.setOnAction((e)->jogoObservavel.p0(3,1));
            b4.setOnAction((e)->jogoObservavel.p0(4,1));
            b5.setOnAction((e)->jogoObservavel.p0(5,1));
            b6.setOnAction((e)->jogoObservavel.p0(6,1));
            b7.setOnAction((e)->jogoObservavel.p0(7,1));
        }else if(jogoObservavel.getSituation()==jogador1){

            b1.setOnAction((e)->jogoObservavel.p1(1,1));
            b2.setOnAction((e)->jogoObservavel.p1(2,1));
            b3.setOnAction((e)->jogoObservavel.p1(3,1));
            b4.setOnAction((e)->jogoObservavel.p1(4,1));
            b5.setOnAction((e)->jogoObservavel.p1(5,1));
            b6.setOnAction((e)->jogoObservavel.p1(6,1));
            b7.setOnAction((e)->jogoObservavel.p1(7,1));
        }
    }
}
