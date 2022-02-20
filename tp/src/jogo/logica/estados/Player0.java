package jogo.logica.estados;


import jogo.logica.dados.Jogo;
import jogo.logica.situation;


    public class Player0 extends adapter{

        public Player0(Jogo jogo){super(jogo);}


        @Override
        public UI_state player0(int num, int opt){
            jogo.setNumlt(0);


            if(opt==5 ){

                if(jogo.getpl().getpeca())
                    return this;

                jogo.resetMini('0');

                return new EsperaMini(jogo);
            }

            for(int j=1;j<8;j++){
                System.out.print(" "+j+" ");
            }
            System.out.println();
            if(opt==1){
                System.out.println("\n\nNome1"+jogo.getpl().getNome());
                if(!jogo.verificaTab(num))
                    return this;
                else{
                    jogo.incTurno();
                    jogo.incMini('0');
                    jogo.incMini('X');}
            }
            if(opt==2){
                jogo.pc('0');
                jogo.incTurno();
                jogo.incMini('0');
                jogo.incMini('X');
            }
            if(opt==3){

                jogo.validaTurno(num);
                jogo.decTurno(num);
                jogo.incMini('0');
                jogo.incMini('X');

            }
            if(opt==6){
                if(!jogo.verificaColPeca(num))
                    return this;
                else{
                    jogo.incTurno();
                    jogo.incMini('0');
                    jogo.incMini('X');}
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




            jogo.addLog("Jogador1: "+jogo.specificPlayer(0).getNome()+" jogou na coluna: "+jogo.getColuna()+"\n");

            if(jogo.win()==1) {
                jogo.addLog("Jogador1: "+jogo.specificPlayer(0).getNome()+" Ganhou");
                return new End(jogo);
            }else if(jogo.win()==2){
                jogo.addLog("Empate");
                return new End(jogo);
            }

            return new Player1(jogo);

        }





        @Override
        public situation getSituation(){return situation.jogador0;}
    }

