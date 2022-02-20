import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jogo.logica.JogoObservavel;
import jogo.logica.StateMachine;
import jogo.ui.GUI.Root;


public class mainFX extends Application {

    @Override
    public void start(Stage primaryStage) {

        StateMachine jogoMaqEstados = new StateMachine();
        JogoObservavel observableModel = new JogoObservavel(jogoMaqEstados);

        Root root = new Root(observableModel);

        Scene scene = new Scene(root, 1000, 550);
        //primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.setTitle("\"4 em Linha\"");
        primaryStage.setOnCloseRequest(windowEvent -> Platform.exit());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
