package jogo.logica.estados;


import jogo.logica.dados.Jogo;
import jogo.logica.situation;

public class Player1 extends adapter{
    public Player1(Jogo jogo){super(jogo);}


    @Override
    public UI_state player1(int num, int opt){
        jogo.setNumlt(1);




        if(opt==5){
            if(jogo.getpl().getpeca())
                return this;
            jogo.resetMini('X');
            return new EsperaMini(jogo);
        }

        if(opt==1){
            System.out.println("\n\nNome2"+jogo.getpl().getNome());
            if(!jogo.verificaTab(num))
                return this;
            else{
                jogo.incTurno();
                jogo.incMini('X');
                jogo.incMini('0');}
        }
        if(opt==2){
            jogo.pc('X');
            jogo.incTurno();
            jogo.incMini('X');
            jogo.incMini('0');
        }
        if(opt==3){

            jogo.validaTurno(num);
            jogo.decTurno(num);
            jogo.incMini('X');
            jogo.incMini('0');

        }
        if(opt==6){
            if(!jogo.verificaColPeca(num))
                return this;
            else{
                jogo.incTurno();
                jogo.incMini('X');
                jogo.incMini('0');}
        }



        System.out.println();
        for(int i=0;i<6;i++){
            System.out.print("\t");
            for(int j=0;j<7;j++){
                System.out.print("|"+jogo.tabuleiro[i][j]+"|");
            }
            System.out.println();
        }
        System.out.print("\t");



        jogo.addLog("Jogador2: "+jogo.specificPlayer(1).getNome()+" jogou na coluna: "+jogo.getColuna()+"\n");
        if(jogo.win()==1) {
            jogo.addLog("Jogador2: "+jogo.specificPlayer(1).getNome()+" Ganhou");
            return new End(jogo);
        }else if(jogo.win()==2){
            jogo.addLog("Empate");
            return new End(jogo);
        }

        return new Player0(jogo);

    }


    @Override
    public situation getSituation(){return situation.jogador1;}
}
