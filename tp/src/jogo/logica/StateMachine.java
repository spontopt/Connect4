package jogo.logica;

import jogo.logica.dados.Jogo;
import jogo.logica.dados.Save;
import jogo.logica.dados.playerList;
import jogo.logica.estados.Player0;
import jogo.logica.estados.Player1;
import jogo.logica.estados.UI_state;
import jogo.logica.estados.Start;
import jogo.logica.Memento.CareTaker;


import java.io.File;
import java.util.List;

public class StateMachine {
    Jogo jogo;
    UI_state now;
    CareTaker caretaker;



    public StateMachine() {

        jogo = new Jogo();
        now = new Start(jogo);
        caretaker = new CareTaker(jogo);
    }


    public void comeca(int n){ now = now.comeca(n);}


    public void gamemode(int modo) { now = now.gamemode(modo);}

    public void id(String n1, String n2){ now = now.id(n1,n2);}

    public void player0(int peca, int opt){ now = now.player0(peca, opt);}
    public void player1(int peca, int opt){ now = now.player1(peca, opt);}
    public void esperaMini(Boolean b) { now = now.esperaMini(b);}
    public void miniJogoFrase(Boolean b){ now = now.miniJogoFrase(b);}
    public void miniJogoMat(Boolean b){ now = now.miniJogoMat(b);}
    public void termina(){
        caretaker.limpar();
        now = now.termina();
    }

    public situation getSituation(){
        return now.getSituation();
    }


    public void saveMem(){ caretaker.gravaMemento();}



    public int getMiniJogoSM(){return jogo.getMiniJogo(); }
    public Boolean getVT(int col){
        caretaker.gravaMemento();
        return jogo.verificaTab(col);}

    public char[][] getTabSM(){return jogo.getTab();}

    public int getModoSM(){return jogo.getmodo();}
    public void setModo(int m){jogo.setModo(m);}
    public int getWin(){return jogo.win();}
    public int getTurnoSM(){return jogo.getTurno();}
    public int getTurnoSMC(){return jogo.getTurnoC();}
    public void incTurnoSM(){jogo.incTurno();}
    public int getMiniTurno(char lt){return jogo.getMini(lt);}
    //public void incMiniSM(){jogo.incMini();}
    public void resetMiniTurno(char lt){jogo.resetMini(lt);}

    public void getPC(char lt){
        caretaker.gravaMemento();
        jogo.pc(lt);}



    public String[] miniJogoM(){return jogo.miniJogoMAT();}
    public void decCM(){jogo.deccontMINI();}
    public boolean VerificaMJM(String[] mat){return jogo.verificaMiniJogoMat(mat);}

    public String miniJogoW(){return jogo.MiniJogoWords();}
    public boolean verificaMJW(String w1,String w2, long tmp){return jogo.verificaMiniJogoWords(w1,w2,tmp);}


    //public void playerInfo(String nome,int np){jogo.addPlayer(nome,np);}
    public playerList getPlsm(){return jogo.getpl();}

    public boolean validaTurnDec(int dec){return jogo.validaTurno(dec);}
    public void decTrn(int dec){ jogo.decTurno(dec);}


    public void undo() {
        caretaker.undo();
    }

    public boolean verificaPecaSM(){return jogo.verificaPeca();}
    public boolean verificaColPecaSM(int colP){return jogo.verificaColPeca(colP);}

    public void setnumltSM(int ltnum){jogo.setNumlt(ltnum);}

    public void saveSM(File file){ Save.saves(jogo,caretaker,file);}
    public void loadSM(File file){
        Object[] aux;
        aux = Save.load(file);
        this.jogo = (Jogo) aux[0];
        this.caretaker = (CareTaker) aux[1];
        if(jogo.getNumlt()==0){
            this.now=new Player1(jogo);
        }else if(jogo.getNumlt()==1){
        this.now=new Player0(jogo);}

    }


     public String autoSaveSM(){return Save.autosaves(jogo,caretaker);}
     public void loadautoSaveSM(File s){
        Object[] aux;
        aux = Save.replayGames(s);
        this.jogo = (Jogo) aux[0];
        this.caretaker = (CareTaker) aux[1];
        if(jogo.getNumlt()==0){
            this.now=new Player1(jogo);
        }else if(jogo.getNumlt()==1){
            this.now=new Player0(jogo);}

     }

     public boolean verificaFile(String s){return Save.ValidaFile(s);}
     public void resetGame(){caretaker.restart();}
     public void redoGame(){caretaker.redo();}

     public List<String> showLogs(){return jogo.getLog();}


}
