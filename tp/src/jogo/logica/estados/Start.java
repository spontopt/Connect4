package jogo.logica.estados;

import jogo.logica.dados.Jogo;
import jogo.logica.situation;



public class Start extends adapter{
    public Start(Jogo jogo){super(jogo);}


    @Override
    public UI_state comeca(int n){
        jogo.addLog("Jogo Iniciado\n");
        jogo.startGame();
        if(n==1) {
            System.out.println("1111111111111");
            return new GameMode(jogo);
        }
        return this;
    }





    @Override
    public situation getSituation(){return situation.inicio;}
}
