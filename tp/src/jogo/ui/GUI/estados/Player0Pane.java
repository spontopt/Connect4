

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


        import java.util.concurrent.atomic.AtomicReference;

        import static jogo.logica.situation.jogador0;


public class Player0Pane extends VBox {


    private JogoObservavel jogoObservavel;

    private Button jogar;
    private Button goBack;
    private Button jogarpeca;
    private Label menuLabel;
    private Label pl;
    HBox bt;
    HBox bt2;
    private Label l;
    private TextField t;
    private Stage s;
    private Button btn;
    private VBox hstage;
    int n=-1;

    public Player0Pane(JogoObservavel jogoObservavel , HBox cp, HBox cp2) {
        this.jogoObservavel = jogoObservavel;
        atualiza();
        registarObservador();
        criarVista();
        bt=cp;
        bt2=cp2;
    }

    private void registarObservador(){
        // regista um observador do jogoObservavel
        jogoObservavel.addPropertyChangeListener("jjjj", evt -> {
            atualiza();


        });
    }
    private void criarVista(){
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
                        "-fx-alignment: center"
        );

        menuLabel = new Label("Jogador 1");
        jogar = new Button("Jogar peca");
        goBack = new Button("Voltar a tras");
        jogarpeca = new Button("Jogar especial");
        pl = new Label ();
        s.setScene(new Scene(hstage));
        hstage.setAlignment(Pos.CENTER);
        setAlignment(Pos.CENTER);
        setSpacing(10);


    }

    private void atualiza() {



        this.setVisible(jogoObservavel.getSituation() == jogador0 );
        System.out.println(jogoObservavel.getTmini('0'));

        if(pl!=null) {
            getChildren().removeAll(menuLabel, jogar, goBack, jogarpeca, pl);
        }

        if(jogoObservavel.getSituation() == jogador0 && jogoObservavel.getmodo()!=3 && jogoObservavel.getmodo()!=4) {
            System.out.println("!=3");
            jogoObservavel.setnum(0);
            pl.setText ("Turno: "+jogoObservavel.getTurnC()+"\nNome: "+jogoObservavel.getp().getNome()+"\nNumero de creditos: "+jogoObservavel.getp().getCreditos()+"\nPeca especial: "+jogoObservavel.getp().getpeca());
            getChildren().addAll(menuLabel, jogar, goBack, jogarpeca,pl);
            if(jogoObservavel.getTmini('0')>=9) {
                System.out.println("minijogo");
                jogar.setOnAction((e)-> {
                    System.out.println("minijogo");
                    jogoObservavel.p0(0,5);
                } );
            }else {
                jogar.setOnAction((e) -> {
                    bt.setVisible(true);
                });
                jogarpeca.setOnAction((e) -> {

                    if(jogoObservavel.getp().getpeca()){

                        bt2.setVisible(true);
                    }
                });
            }






        }else if(jogoObservavel.getSituation() == jogador0 && jogoObservavel.getmodo()==3){
            System.out.println("==3");
            pl.setText ("Turno: "+jogoObservavel.getTurnC()+"\nNome: "+jogoObservavel.getp().getNome()+"\nNumero de creditos: "+jogoObservavel.getp().getCreditos()+"\nPeca especial: "+jogoObservavel.getp().getpeca());
            getChildren().addAll(menuLabel, jogar);
            jogar.setOnAction((e)-> {
                jogoObservavel.p0(0,2);
             } );
        }else if(jogoObservavel.getSituation() == jogador0 && jogoObservavel.getmodo()==4){
            System.out.println("==4");
            pl.setText ("Turno: "+jogoObservavel.getTurnC()+"\nNome: "+jogoObservavel.getp().getNome()+"\nNumero de creditos: "+jogoObservavel.getp().getCreditos()+"\nPeca especial: "+jogoObservavel.getp().getpeca());
            getChildren().addAll(menuLabel,jogar,pl);
            jogar.setOnAction((e)-> {
                System.out.println("==5");
                jogoObservavel.redoo();
                jogoObservavel.p0(0,4);
            } );
        }
        if(goBack!=null)
            goBack.setOnAction((e)->{






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


                                    while (i < n ) {
                                        jogoObservavel.back();
                                        i++;
                                    }
                                }


                                if(n!=-1) {
                                    jogoObservavel.p0(n, 3);
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