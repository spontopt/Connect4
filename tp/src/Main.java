import jogo.ui.texto.textUI;
import jogo.logica.StateMachine;

public class Main {
    public static void main(String[] args){
        StateMachine sm = new StateMachine();
        textUI tui = new textUI(sm);
        tui.comeca();
    }
}
