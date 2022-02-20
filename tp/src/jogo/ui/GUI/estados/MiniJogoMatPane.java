
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


        import static jogo.logica.situation.minimat;


public class MiniJogoMatPane extends VBox {


    private JogoObservavel jogoObservavel;

    private Button next;
    private TextField res;
    private Label titulo;
    private Label contas;
    private String[] s1;
    private Boolean b=false;
    private long start;
    private long end;

    private Label l;
    private Stage s;
    private Button btn;
    private HBox hstage;
    private int i=0;




    public MiniJogoMatPane(JogoObservavel jogoObservavel) {
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
        titulo = new Label("Mini-Jogo: Matematica");
        next = new Button("Avancar");
        contas = new Label();
        res = new TextField();



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
        this.setVisible(jogoObservavel.getSituation() ==  minimat );
        if(jogoObservavel.getSituation()==minimat && !jogoObservavel.getp().getpeca()) {
            if(i==0) {
                start = System.currentTimeMillis();
                b=false;
                i++;
            }



                if (titulo != null) {
                    getChildren().removeAll(titulo, contas, res, next);
                    hstage.getChildren().removeAll(l, btn);
                }
                getChildren().addAll(titulo, contas, res, next);

                    System.out.println("press");
                    end = System.currentTimeMillis();
                    s1=null;
                    s1 = jogoObservavel.getContas();
            System.out.println(" "+s1[0] + "" + s1[1] + "" + s1[2]);
                    contas.setText(" "+s1[0] + "" + s1[1] + "" + s1[2]);
                    next.setOnAction((e) -> {
                        s1[3] = (res.getText().trim());
                        if (jogoObservavel.verificaMat(s1) && end-start<=30000) {
                            l.setText("Mini-Jogo Vencido!!\nFoi obtida uma peca especial!!!");
                            b = true;
                            i=0;
                            s.show();
                            hstage.getChildren().addAll(l, btn);
                            btn.setOnAction((w) -> {
                                s.hide();
                                jogoObservavel.MiniMat(b);
                            });
                        }  else if (!b && end-start>30000) {
                                i=0;
                                l.setText("Mini-Jogo Perdido...\nPossibilidade de jogar perdida.");
                                b = false;
                                s.show();
                                if(l!=null)
                                    hstage.getChildren().removeAll(l, btn);
                                hstage.getChildren().addAll(l, btn);
                                btn.setOnAction((w) -> {
                                     s.hide();
                                     jogoObservavel.MiniMat(b);
                                });

                        }else if(!b && end-start<=30000)
                            atualiza();

                    });







        }
    }




}