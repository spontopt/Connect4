package jogo.logica.estados;

import jogo.logica.dados.Jogo;

import jogo.logica.situation;

public class End extends adapter {

    public End(Jogo jogo) {
        super(jogo);
    }

    @Override
    public UI_state termina() {


        jogo.addLog("O jogo terminou \n");
        return new Start(jogo);
    }

    @Override
    public situation getSituation() {
        return situation.fim;
    }
}



