package jogo.logica.estados;

import jogo.logica.dados.Jogo;
import jogo.logica.situation;


public class GameMode extends adapter{
    public GameMode(Jogo jogo){super(jogo);}

    @Override
    public UI_state gamemode(int modo) {
        System.out.println("22222222");
        jogo.setModo(modo);
        jogo.addLog("Modo de Jogo escolhido: "+jogo.getmodo()+"\n");

        return new ID(jogo);
    }

    @Override
    public situation getSituation(){return situation.modoJogo;}
}