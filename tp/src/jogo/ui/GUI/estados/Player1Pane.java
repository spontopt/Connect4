

package jogo.ui.GUI.estados;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import jogo.logica.JogoObservavel;
import jogo.ui.GUI.resources.CSSManager;


import java.util.concurrent.atomic.AtomicReference;

import static jogo.logica.situation.jogador1;



public class Player1Pane extends VBox {


    private JogoObservavel jogoObservavel;

    private Button jogar1;
    private Button goBack1;
    private Button jogarpeca1;




    private Label menuLabel1;
    private Label pl1;
    HBox hb;
    HBox hb2;
    private Label l;
    private TextField t;
    private Stage s;
    private Button btn;
    private VBox hstage;
    int n=-1;

    public Player1Pane(JogoObservavel jogoObservavel, HBox cp, HBox cp2) {

        this.jogoObservavel = jogoObservavel;
        atualiza();
        registarObservador();
        criarVista(jogoObservavel.getmodo());
        hb=cp;
        hb2=cp2;
    }

    private void registarObservador(){
        // regista um observador do jogoObservavel
        jogoObservavel.addPropertyChangeListener("jjjj", evt -> {
            atualiza();
        });
    }

    private void criarVista(int modo){
        t= new TextField();
        s = new Stage();
        l= new Label("Numero de vezes a voltar:");
        btn = new Button("Seguinte");
        hstage = new VBox(10);
        hstage.setStyle(
                "-fx-background-color: linear-gradient(to top, #2c3e50, #2980b9);" +
                        "-fx-min-height: 130px;" +
                        "-fx-min-width: 450px;");
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
                        "-fx-alignment: center" +
                        " "

        );



        menuLabel1 = new Label("Jogador 2");
        jogar1 = new Button("Jogar peca");
        goBack1 = new Button("Voltar a tras");
        jogarpeca1 = new Button("Jogar especial");
        pl1 = new Label ();
        s.setScene(new Scene(hstage));
        hstage.setAlignment(Pos.CENTER);
        setAlignment(Pos.CENTER);
        setSpacing(10);

    }

    private void atualiza() {


        this.setVisible(jogoObservavel.getSituation() == jogador1 );
        System.out.println(jogoObservavel.getTmini('X'));



        if(pl1!=null){
            getChildren().removeAll(menuLabel1, jogar1, goBack1, jogarpeca1,pl1);
        }
        if(jogoObservavel.getSituation() == jogador1 && jogoObservavel.getmodo()==1) {
            System.out.println("==1");
            jogoObservavel.setnum(1);
            pl1.setText ("Turno: "+jogoObservavel.getTurnC()+"\nNome: "+jogoObservavel.getp().getNome()+"\nNumero de creditos: "+jogoObservavel.getp().getCreditos()+"\nPeca especial: "+jogoObservavel.getp().getpeca());
            getChildren().addAll(menuLabel1, jogar1, goBack1, jogarpeca1,pl1);
            if(jogoObservavel.getTmini('X')>=9) {

                jogar1.setOnAction((e)-> {

                    jogoObservavel.p1(0,5);
                } );
            }else{
            jogar1.setOnAction((e)->{
                hb.setVisible(true);

            });
            jogarpeca1.setOnAction((e)->{

                if(jogoObservavel.getp().getpeca()){

                    hb2.setVisible(true);
                }

            });}



        }else if(jogoObservavel.getSituation() == jogador1 && jogoObservavel.getmodo()!=1 && jogoObservavel.getmodo()!=4){
            System.out.println("!=1");
            pl1.setText ("Turno: "+jogoObservavel.getTurnC()+"\nNome: "+jogoObservavel.getp().getNome()+"\nNumero de creditos: "+jogoObservavel.getp().getCreditos()+"\nPeca especial: "+jogoObservavel.getp().getpeca());
            getChildren().addAll(menuLabel1, jogar1);
            jogar1.setOnAction((e)-> {

                jogoObservavel.p1(0,2);
            } );
        }else if(jogoObservavel.getSituation() == jogador1 && jogoObservavel.getmodo()==4){
            System.out.println("==4");
            pl1.setText ("Turno: "+jogoObservavel.getTurnC()+"\nNome: "+jogoObservavel.getp().getNome()+"\nNumero de creditos: "+jogoObservavel.getp().getCreditos()+"\nPeca especial: "+jogoObservavel.getp().getpeca());
            getChildren().addAll(menuLabel1,jogar1,pl1);
            jogar1.setOnAction((e)-> {
                jogoObservavel.redoo();
              jogoObservavel.p1(0,4);
            } );
        }
        if(goBack1!=null)
        goBack1.setOnAction((e)->{

            if(jogoObservavel.getTurn()>1) {

                hstage.getChildren().addAll(l, t, btn);
                s.show();

                btn.setOnAction((w) ->
                {
                    valor();
                    s.hide();
                    if (n <= jogoObservavel.getTurn() && n <= jogoObservavel.getp().getCreditos() && n!=-1) {
                        System.out.println("\nn: " + n);

                        int i=0;
                        if(n>0) {
                            while (i < n) {
                                jogoObservavel.back();
                                System.out.println("ciclo");
                                i++;
                            }
                        }
                        if(n!=-1) {
                            jogoObservavel.p1(n, 3);
                        }
                    }
                    if(l!=null) {
                        hstage.getChildren().removeAll(l, t, btn);
                    }
                });

            }else{
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("Impossivel realizar operacao");
                a.show();
            }



        });



    }
    private void valor(){
        String st;
        st=(t.getText()).trim();
        n = Integer.parseInt(st);

    }
}