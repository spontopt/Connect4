
package jogo.ui.GUI.estados;


        import javafx.geometry.Pos;
        import javafx.scene.Scene;

        import javafx.scene.control.Button;
        import javafx.scene.control.Label;


        import javafx.scene.control.TextField;
        import javafx.scene.layout.HBox;
        import javafx.scene.layout.VBox;

        import javafx.stage.Stage;
        import jogo.logica.JogoObservavel;

        import java.io.File;

        import static jogo.logica.situation.inicio;
        import static jogo.logica.situation.minifrase;


public class MiniJogoFrasePane extends VBox {


    private JogoObservavel jogoObservavel;

    private Button next;
    private TextField frase;
    private Label titulo;
    private Label fraseMini;
    private String s1;
    private String s2;
    private Boolean b=false;
    private long start;

    private Label l;
    private Stage s;
    private Button btn;
    private HBox hstage;




    public MiniJogoFrasePane(JogoObservavel jogoObservavel) {
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
        titulo = new Label("Mini-Jogo: Frase");
        next = new Button("Avancar");
        fraseMini = new Label();
        frase = new TextField();



        s = new Stage();
        l= new Label();
        btn = new Button("Seguinte");
        hstage =new HBox(10);
        s.setScene(new Scene(hstage));
        hstage.setStyle(
                "-fx-background-color: linear-gradient(to top, #2c3e50, #2980b9);" +
                        "-fx-min-height: 130px;" +
                        "-fx-min-width: 550px;");
        l.setStyle(
                "-fx-min-width: 70px;"+
                        "-fx-min-height: 30px;"+
                        "-fx-font-family: Consolas;"+
                        "-fx-font-size: 18px;"+
                        "-fx-text-fill: #ffffff;"
        );
        btn.setStyle(
                "-fx-min-width: 150px;"+
                        "-fx-min-height: 35px;"+
                        "-fx-font-family: Consolas;"+
                        "-fx-font-size: 16px;"+
                        "-fx-background-color: #808080;"+
                        "-fx-text-fill: #ffffff;"+
                        "-fx-background-radius: 10px;" +
                        "-fx-alignment: center"
        );


        hstage.setAlignment(Pos.CENTER);
        setAlignment(Pos.CENTER);
        setSpacing(10);



    }


    private void atualiza() {
        this.setVisible(jogoObservavel.getSituation() ==  minifrase );
        if(jogoObservavel.getSituation() ==  minifrase && !jogoObservavel.getp().getpeca()) {
            if (titulo != null) {
                getChildren().removeAll(titulo, fraseMini, frase, next);
                hstage.getChildren().removeAll(l, btn);
            }
            s2 = jogoObservavel.getFrase();
            fraseMini.setText(s2);
            start = System.currentTimeMillis();
            getChildren().addAll(titulo, fraseMini, frase, next);
            next.setOnAction((e) -> {
                s1 = (frase.getText().trim());
                if (jogoObservavel.verificaFrase(s1, s2, start)) {
                    l.setText("Mini-Jogo Vencido!!\nFoi obtida uma peca especial!!!");
                    b = true;
                } else {
                    l.setText("Mini-Jogo Perdido...\nPossibilidade de jogar perdida.");
                    b = false;
                }

                s.show();
                hstage.getChildren().addAll(l, btn);
                btn.setOnAction((w) -> {
                    s.hide();
                    jogoObservavel.MiniFrase(b);
                });

            });
        }


    }




}