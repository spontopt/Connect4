package jogo.ui.GUI.estados;

        import javafx.geometry.Pos;
        import javafx.scene.control.Alert;
        import javafx.scene.control.Button;
        import javafx.scene.control.Label;

        import javafx.scene.control.TextField;
        import javafx.scene.layout.VBox;
        import javafx.scene.paint.Color;
        import jogo.logica.JogoObservavel;

        import static jogo.logica.situation.identificacao;
        //import static jogo.ui.GUI.constantesGUI.LETRA;

public class IDPane extends VBox {


    private JogoObservavel jogoObservavel;

    private Button avancaButton;
    private TextField p0TextField;
    private TextField p1TextField;
    private Label p0Label;
    private Label p1Label;


    public IDPane(JogoObservavel jogoObservavel) {
        this.jogoObservavel = jogoObservavel;
        criarVista();
        registarObservador();
        atualiza();

    }
    private void registarObservador(){

        jogoObservavel.addPropertyChangeListener("jjjj", evt -> {
            atualiza();
        });
    }
    private void criarVista(){



        p0Label = new Label();
        p0TextField = new TextField();
        p1Label = new Label();
        p1TextField = new TextField();
        avancaButton = new Button("Avancar");

        setAlignment(Pos.CENTER);
        setSpacing(10);




    }
    private void atualiza() {

        this.setVisible(jogoObservavel.getSituation() == identificacao );
        if(p0Label!=null)
            getChildren().removeAll(p0Label,p1Label,p1TextField,p0TextField,avancaButton);
        if(jogoObservavel.getmodo()==1){
            p0Label.setText("Jogador 1: ");
            p1Label.setText("Jogador 2: ");
            getChildren().addAll(p0Label, p0TextField, p1Label, p1TextField, avancaButton);
            avancaButton.setOnAction((e)->setName());
        }else if(jogoObservavel.getmodo()==2){
            p0Label.setText("Jogador 1:");
            p1Label.setText("Jogador 2: \nComputador");

            getChildren().addAll(p0Label, p0TextField,p1Label, avancaButton);
            avancaButton.setOnAction((e)->setName());
        }else if(jogoObservavel.getmodo()==3){
            p0Label.setText("Jogador 1:\nComputador1");
            p1Label.setText("Jogador 2: \nComputador2");

            getChildren().addAll(p0Label,p1Label, avancaButton);
            avancaButton.setOnAction((e)->setName());
        }

    }

    private Boolean setName() {
        String s1,s2;
        if(jogoObservavel.getmodo()==1){
        s1=(p0TextField.getText()).trim();
        s2=(p1TextField.getText()).trim();
        if(s1.equals(s2)){
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Nomes Introduzidos Nao Podem Ser Iguais!!");
            a.show();
            return false;
        }
        }else if(jogoObservavel.getmodo()==2){
            s1=(p0TextField.getText()).trim();
            s2 = "Computador";
        }else{
            s1="Computador1";
            s2="Computador2";
        }
        System.out.println(s1+" "+s2);
        jogoObservavel.id(s1,s2);
        return true;
    }
}