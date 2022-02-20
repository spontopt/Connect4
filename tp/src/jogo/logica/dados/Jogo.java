package jogo.logica.dados;


import jogo.logica.Memento.CareTaker;
import jogo.logica.Memento.IMementoOriginator;
import jogo.logica.Memento.Memento;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class Jogo implements Serializable, IMementoOriginator {
    public char [][]tabuleiro;
    int auxTurno=1,turno = 1,modo=0,numlt=-1, turnoCont=1,startOpt,coluna,miniJogo=0;
    int[] mini = new int[2];
    private ArrayList<playerList> plist;

    ///////////////////////////////////////////////////////////////
    ArrayList<String> log = new ArrayList<>();

    public void addLog(String str){
        log.add(str);
    }

    public List<String> getLog(){
        return log;
    }

    ///////////////////////////////////////////////////////////////
    public Jogo(){

    }

    public void startGame() {
        tabuleiro = new char[6][7];
        for(int i=0;i<6;i++){
            for(int j=0;j<7;j++){
                tabuleiro[i][j]='_';
            }
        }

        plist = new ArrayList<>();
        turno=1;
        turnoCont=1;
        mini[0]=1;
        mini[1]=1;
        miniJogo=0;


    }

    public int getRand(){
        Random r = new Random();
        int res = r.nextInt(2 ) + 1;
        return res;}

    public int getColuna(){return coluna;}
    public char[][] getTab(){
        return tabuleiro;
    }

    public void setStartOpt(int n){startOpt=n;}
    public int getStartOpt(){return startOpt;}


    public void setModo(int M){modo=M;}
    public int getmodo(){return modo;}



    public boolean verificaTab(int col){
        char letra = '_';
        if(col>7||col<1){
            return false;
        }
        if(numlt==-1)
            return false;
        System.out.println(plist.get(numlt).nome);
        if(plist.get(numlt).getlet()==0){
            letra = '0';
        }else if(plist.get(numlt).getlet()==1){
            letra = 'X';
        }

        int cont=0;
        for(int i = 5; i>=0; i--,cont++){

            if(tabuleiro[i][col-1]=='_' && cont<=6){
                tabuleiro[i][col-1]=letra;
                coluna = col;
                return true;
            }


        }
        return false;

    }



    public int win(){
        int lt='_';
        if(plist.get(numlt).getlet()==0){
            lt='0';
        }else if(plist.get(numlt).getlet()==1){
            lt='X';
        }

        for(int i=0;i< tabuleiro.length;i++){
            int cont=0;
            for(int j=0;j< tabuleiro[0].length;j++){
                if(tabuleiro[i][j]==lt) {
                    cont++;
                    if(cont>=4){
                        return 1;
                    }
                }else{
                    cont=0;
                }
            }


        }

        for(int i=0;i<tabuleiro[0].length;i++){
            int cont=0;
            for(int j=0;j<tabuleiro.length;j++){
                if(tabuleiro[j][i]==lt){
                    cont++;
                }else{
                    cont=0;
                }
            }
            if(cont>=4){
                return 1;
            }
        }


        for (int i = 3; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro[0].length - 3; j++) {
                if (tabuleiro[i][j] == lt && tabuleiro[i - 1][j + 1] == lt && tabuleiro[i - 2][j + 2] == lt && tabuleiro[i - 3][j + 3] == lt)
                    return 1;
            }
        }

        for (int i = 3; i < tabuleiro.length; i++) {
            for (int j = 3; j < tabuleiro[0].length; j++) {
                if (tabuleiro[i][j] == lt && tabuleiro[i - 1][j - 1] == lt && tabuleiro[i - 2][j - 2] == lt && tabuleiro[i - 3][j - 3] == lt)
                    return 1;
            }

        }

        for(int i=0;i< tabuleiro.length;i++){
            int cont=0;
            for(int j=0;j< tabuleiro[0].length;j++){
                if(tabuleiro[i][j]!='_') {
                    cont++;
                    if(cont>=tabuleiro.length*tabuleiro[0].length){
                        return 2;
                    }
                }else{
                    cont=0;
                }
            }


        }






        return 0;
    }

    public void setNumlt(int ltnum){ numlt=ltnum;}
    public int getNumlt(){return numlt;}
    public int getTurno(){return turno;}
    public int getTurnoC(){return turnoCont;}
    public void incTurno(){++turno;++turnoCont;}
    public int getMini(char lt){
        if(lt=='X') {
            return mini[1];
        }else if(lt=='0')
            return mini[0];
        return 0;
    }
    public void incMini(char lt){
        if(lt=='0')
            ++mini[0];
        else if(lt=='X')
            ++mini[1];
    }
    public void resetMini(char lt) {
        if(lt=='X')
            mini[1] = 1;
        if(lt=='0')
            mini[0]=1;
    }




    public void pc(char lt){

        int valida4=0;
        do {
            Random rd = new Random();
            int c = rd.nextInt(7 ) + 1;
            if (verificaTab(c) != true) {
                System.out.println("Coluna invalida");
                valida4 = 0;
            } else {
                valida4 = 1;
            }
            System.out.println(c);
        } while (valida4 == 0);
    }




    public int getMiniJogo(){
        ++miniJogo;
        if(miniJogo==1 || miniJogo==2){
            return 1;
        }else if(miniJogo==4){
            return 2;
        }else{
            miniJogo=0;
            return 2;
        }
    }

    public String MiniJogoWords(){
        StringBuilder Words= null;
        String[] read = new String[100];

        try {
            File myfile = new File("words.txt");
            Scanner txt = new Scanner(myfile);
            int cnt=0;
            while (txt.hasNextLine()) {
                read[cnt] = txt.nextLine();
                cnt++;

            }
            txt.close();
        } catch (FileNotFoundException e) {
            System.out.println("Erro");
            e.printStackTrace();
        }


        Random rnd = new Random();
        int w1 = rnd.nextInt(99)+1;

        Words = new StringBuilder(read[w1]);
        for(int i=0;i<4;i++){
            int w2 = rnd.nextInt(99)+1;

            Words.append(" ").append(read[w2]);
        }


        return Words.toString();
    }

    public boolean verificaMiniJogoWords(String wordsOriginal, String wordsCopy, long startTimer){
        long endTimer = System.currentTimeMillis();
        if(wordsCopy.equals(wordsOriginal) && startTimer+(wordsOriginal.length()/2)* 1000L >= endTimer){
            plist.set(numlt, new playerList(plist.get(numlt).getNome(),numlt,plist.get(numlt).getCreditos(),true));
            return true;
        }
        return false;

    }




    public String[] miniJogoMAT(){

        String []mat = new String[4];
        Random rd = new Random();
        int num1 = rd.nextInt(10 ) + 1;
        int num2 = rd.nextInt(10 ) + 1;
        int operador = rd.nextInt(4) + 1;

        if(operador==1){
            mat[1]="+";
        }else if(operador==2){
            mat[1]="-";
        }else if(operador==3){
            mat[1]="*";
        }else if(operador==4){
            mat[1]="/";
        }else{
            System.out.println("erro");
        }
        mat[0]=Integer.toString(num1);
        mat[2]=Integer.toString(num2);
        return mat;
    }
    static int contMini=0;
    int contfunction=0;

    public Boolean verificaMiniJogoMat(String[] mat){
        contfunction++;
        int a = Integer.valueOf(mat[0]);
        int b = Integer.valueOf(mat[2]);
        int res = Integer.valueOf(mat[3]);


        switch (mat[1]) {
            case "+":
                if (a + b == res) {
                    contMini++;

                }

                break;
            case "-":
                if (a - b == res) {
                    contMini++;
                }

                break;
            case "*":
                if (a * b == res) {
                    contMini++;

                }
                break;
            case "/":
                if (a / b == res) {
                    contMini++;

                }
                break;
        }
        System.out.println(contMini);
        if(contMini>=5) {

            plist.set(numlt, new playerList(plist.get(numlt).getNome(),numlt,plist.get(numlt).getCreditos(),true));
            return true;
        }else{
        return false;}
    }
    public void deccontMINI(){
        contMini=0;
    }



    public void addPlayer(String nome,int np ){
        plist.add(new playerList(nome, numlt,5,false));

    }



    public playerList getpl(){
        if(numlt==-1)
            return null;
        return plist.get(numlt);
    }
    public playerList specificPlayer(int n){
        return plist.get(n);
    }



    @Override
    public Memento getMemento() throws IOException {
        Memento m = new Memento(this);
        return m;
    }



    @Override
    public void setMemento(Memento m) throws IOException, ClassNotFoundException {

            Jogo jogo = (Jogo) m.getSnapshot();
            tabuleiro = jogo.tabuleiro;
            turnoCont = jogo.turnoCont;
            plist = jogo.plist;
            mini = jogo.mini;

    }


    public Boolean validaTurno(int dec){
        if(dec<1 || dec > plist.get(numlt).getCreditos() || turnoCont<dec){
            return false;
        }
        auxTurno = turno;
        return true;
    }
    public void decTurno(int dec){
        plist.set(numlt, new playerList(plist.get(numlt).getNome(),numlt,plist.get(numlt).getCreditos()-dec,plist.get(numlt).getpeca()));
        turno = auxTurno;
    }




    public Boolean verificaPeca(){
        return plist.get(numlt).getpeca();

    }


    public boolean verificaColPeca(int colP) {
        if(colP>tabuleiro[0].length || colP<1){

            return false;
        }

        for(int i = tabuleiro.length-1; i>=0; i--){
            tabuleiro[i][colP-1]='_';
        }
        plist.set(numlt,new playerList(plist.get(numlt).nome,numlt,plist.get(numlt).creditos,false));

        return true;
    }




}

