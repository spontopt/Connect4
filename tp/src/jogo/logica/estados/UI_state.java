package jogo.logica.estados;

import jogo.logica.situation;

public interface UI_state {

    UI_state comeca(int n);
    UI_state reverJogo(Boolean b);

    UI_state gamemode(int modo);
    UI_state id(String n1, String n2);


    UI_state player0(int peca, int opt);
    UI_state player1(int peca, int opt);


    UI_state esperaMini(Boolean b);
    UI_state miniJogoFrase(Boolean b);
    UI_state miniJogoMat(Boolean b);

    UI_state termina();

    situation getSituation();






}
