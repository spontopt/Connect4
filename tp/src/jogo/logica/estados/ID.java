package jogo.logica.estados;

import jogo.logica.dados.Jogo;
import jogo.logica.situation;

import java.util.Random;

public class ID extends adapter{

    public ID(Jogo jogo){super(jogo);}



    @Override
    public UI_state id(String n1, String n2) {
            jogo.setNumlt(0);
            jogo.addPlayer(n1,0);
            jogo.setNumlt(1);
            jogo.addPlayer(n2,1);
        System.out.println("players");

            jogo.addLog("Identificacao:\n Jogador1: "+jogo.specificPlayer(0).getNome()+"\n Jogador2: "+jogo.specificPlayer(1).getNome()+"\n");

            if(jogo.getRand() == 1){
                jogo.setNumlt(0);
                return new Player0(jogo);
            }else{
                jogo.setNumlt(1);
                return new Player1(jogo);
            }





    }



    @Override
    public situation getSituation(){return situation.identificacao;}
}


