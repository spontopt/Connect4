
package jogo.logica.estados;

        import jogo.logica.dados.Jogo;
        import jogo.logica.situation;


public class MiniJogoFrase extends adapter{
    public MiniJogoFrase(Jogo jogo){super(jogo);}

    @Override
    public UI_state miniJogoFrase(Boolean b) {
        if(jogo.getNumlt()==0){
            if(b){
                jogo.addLog("Jogador1: "+jogo.specificPlayer(0).getNome()+" venceu o Mini-Jogo: Frase\n");
                return new Player0(jogo);
            }else{
                jogo.addLog("Jogador1: "+jogo.specificPlayer(0).getNome()+" perdeu o Mini-Jogo: Frase\n");
                return new Player1(jogo);
            }
        }else{
            if(b){
                jogo.addLog("Jogador2: "+jogo.specificPlayer(1).getNome()+" venceu o Mini-Jogo: Frase\n");
                return new Player1(jogo);
            }else{
                jogo.addLog("Jogador2: "+jogo.specificPlayer(1).getNome()+" perdeu o Mini-Jogo: Frase\n");
                return new Player0(jogo);
            }
        }


    }

    @Override
    public situation getSituation(){return situation.minifrase;}
}
