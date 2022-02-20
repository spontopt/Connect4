package jogo.logica.estados;

import jogo.logica.dados.Jogo;

public abstract class adapter implements UI_state {

    protected Jogo jogo;

    protected adapter(Jogo jogo) {
        this.jogo = jogo;
    }

    @Override
    public UI_state comeca(int n) {
        return this;
    }

    @Override
    public UI_state reverJogo(Boolean b) { return this;}

    @Override
    public UI_state gamemode(int modo) {
        return this;
    }

    @Override
    public UI_state id(String n1, String n2) {
        return this;
    }

  //  @Override
   // public UI_state player() {
   //     return this;
    //}
    @Override
    public UI_state player0(int peca, int opt) {
        return this;
    }
    @Override
    public UI_state player1(int peca, int opt) {
        return this;
    }

    @Override
    public UI_state esperaMini(Boolean b) {
        return this;
    }
    @Override
    public UI_state miniJogoFrase(Boolean b) {
        return this;
    }
    @Override
    public UI_state miniJogoMat(Boolean b) {
        return this;
    }
    @Override
    public UI_state termina() {
        return this;
    }
}
