package jogo.logica.estados;

import jogo.logica.dados.Jogo;
import jogo.logica.situation;


public class EsperaMini extends adapter{
    public EsperaMini(Jogo jogo){super(jogo);}

    @Override
    public UI_state esperaMini(Boolean b) {
        if (b){
            if(jogo.getMiniJogo()==1){
                return new MiniJogoMat(jogo);
            }else{
                return new MiniJogoFrase(jogo);
            }
        }else{
            if(jogo.getNumlt()==0)
                return new Player0(jogo);
            else
                return new Player1(jogo);

        }


    }

    @Override
    public situation getSituation(){return situation.esperamini;}
}
