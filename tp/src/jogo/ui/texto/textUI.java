package jogo.ui.texto;
import jogo.logica.StateMachine;
import jogo.ui.utils.UtilUI;
import java.util.Random;


public class textUI {

    StateMachine sm;
    boolean sair;

    char lt;

    public textUI(StateMachine sm) {
        this.sm = sm;
    }

    public void comeca() {

        sair = false;
        while(!sair){
            switch (sm.getSituation()){
                case inicio:
                    inicioUI();
                    break;
                case rever:
                    replaysUI();
                    break;
                case modoJogo:
                    modoJogoUI();
                    break;
                case identificacao:
                    idUI();
                    break;
                case jogador0:
                    lt = '0';
                    sm.setnumltSM(0);
                    playerUI();
                    break;
                case jogador1:
                    lt = 'X';
                    sm.setnumltSM(1);
                    playerUI();
                    break;
                case fim:
                    fimJogoUI();
                    break;
            }
        }
    }


    private void inicioUI() {
        int op = UtilUI.escolheOpcao("Jogar","Load","Replay","Sair");
        switch (op){
            case 1:
                //sm.setOPTStart(0);
                sm.comeca(1);
                break;
            case 2:
               // sm.loadSM();
                showtab();
                playerUI();
                break;
            case 3:
                //sm.setOPTStart(1);
                sm.comeca(2);
                replaysUI();
                break;
            default:
                sair = true;
                break;
        }
    }
    private void replaysUI(){
        String s;
        do {
            s=UtilUI.pedeString("\n---------------------------------\nNome do Ficheiro:\n>");
            if(!sm.verificaFile(s))
                System.out.println("Impossivel abrir ficheiro introduzido");
        }while(!sm.verificaFile(s));
       // sm.loadautoSaveSM(s);
        sm.resetGame();
        System.out.println("\nResumo do Jogo: "+s);
        replayGame();
        //sm.reverJogo();
    }
    public void replayGame(){
        System.out.println("\n---------------------------------\n");
        int op = UtilUI.escolheOpcao("Seguinte","Sair");
        switch (op){
            case 1:
                System.out.println("turno: " + sm.getTurnoSM());
                System.out.println(sm.getPlsm());
                sm.redoGame();
                showtab();
                if(sm.getWin()==1){
                    System.out.println("\n---------------------------------\n   VENCEDOR: "+sm.getPlsm().getNome());
                }
                replayGame();
                break;
            default:
                //sm.reverJogo();
                break;
        }
    }
    private void fimJogoUI() {
        System.out.println("O jogo terminou!!!");
        System.out.println("Jogo guardado apelidado de: "+sm.autoSaveSM());
        sm.termina();
    }


    private void modoJogoUI() {
        int modo=0;
        System.out.println("\n---------------------------------\nEscolha o modo de jogo");
        switch (UtilUI.escolheOpcao("PvP","PvC","CvC","Sair")){
            case 1:
                modo=1;
                break;
            case 2:
                modo=2;
                break;
            case 3:
                modo=3;
                break;
            default:
                sair = true;
                break;
        }

        sm.gamemode(modo);
    }


    private void idUI(){
        String n1,n2;
        System.out.println("\n---------------------------------\n");
        do {
            n1 = UtilUI.pedeString("Nome Jogador 1\n>");
            n2 = UtilUI.pedeString("Nome Jogador 2\n>");
        }while(n1.equals(n2));

        sm.id(n1,n2);
    }


    public void showtab(){
        char [][]tabuleiro;
        tabuleiro = sm.getTabSM();
        System.out.println();
        for(int i=0;i<6;i++){
            System.out.print("\t");
            for(int j=0;j<7;j++){
                System.out.print("|"+tabuleiro[i][j]+"|");
            }
            System.out.println();
        }
        System.out.print("\t");
        for(int j=1;j<8;j++){
            System.out.print(" "+j+" ");
        }
        System.out.println();
    }


    public Boolean miniJogos(){
        int turno = sm.getMiniTurno(lt);

        Boolean VMJM = true;
        if(turno%9==0 && turno > 1 || (turno-1)%9==0 && turno > 1 ){
            if(turno==10){ sm.resetMiniTurno(lt);}
            System.out.println(turno);
            System.out.println("\n---------------------------------\n");
            System.out.println("\nMini Jogo:\n");
            switch (UtilUI.escolheOpcao("Aceitar","Recusar")){
                case 1:
                    System.out.println("\n---------------------------------\n");
                    System.out.println("\nDesafio aceite:\n");

                    switch (sm.getMiniJogoSM()){
                        case 1:
                            String []m;
                            int res;
                            long startTimeMat = System.currentTimeMillis();
                            long endTime = 0;
                            while(endTime-startTimeMat<30000){
                                endTime = System.currentTimeMillis();
                                m = sm.miniJogoM();
                                System.out.println(m[0]+" "+m[1]+" "+m[2]);
                                res = UtilUI.pedeInteiro("\n>");
                                m[3]=Integer.toString(res);
                                if(sm.VerificaMJM(m)){
                                    System.out.println("Foi obtida uma peca especial");
                                    VMJM = true;
                                    if(sm.getWin()==1){
                                        System.out.println("VENCEDOR\n    "+sm.getPlsm().getNome());
                                        showtab();
                                        if(lt=='0') {
                                          //  sm.player0();
                                        }else if(lt=='X'){
                                          //  sm.player1();
                                        }
                                    }
                                    break;
                                }else{
                                    VMJM = false;
                                }
                            }
                            if(!VMJM){
                                System.out.println("Desafio Perdido");

                            }
                            sm.decCM();
                            break;
                        case 2:
                            String wordsOriginal,wordsCopy;
                            wordsOriginal = sm.miniJogoW();
                            System.out.println(wordsOriginal);
                            long startTimeWord = System.currentTimeMillis();
                            wordsCopy = UtilUI.pedeString("\n>");
                            if(sm.verificaMJW(wordsOriginal,wordsCopy,startTimeWord)){
                                System.out.println("Foi obtida uma peca especial");
                                VMJM = true;

                                if(sm.getWin()==1){
                                    System.out.println("VENCEDOR\n    "+sm.getPlsm().getNome());
                                    showtab();
                                    if(lt=='0') {
                                      //  sm.player0();
                                    }else if(lt=='X'){
                                      //  sm.player1();
                                    }
                                }
                            }else{
                                System.out.println("Desafio perdido");
                                VMJM = false;
                            }
                    }
                    break;
                default:
                    break;
            }
        }
        return VMJM;
    }

    private void playerUI(){

        int turno = sm.getTurnoSM();
        System.out.println("\n\n---------------------------------\n\nTurno: "+turno);
        showPL();
        switch (UtilUI.escolheOpcao("Jogar peca","Voltar atras","Guardar","Mostrar logs","Sair")){
            case 1:
                if(sm.getModoSM() == 1) {
                    if(!miniJogos()){//////////////
                        if(lt=='0') {
                           // sm.player0();
                        }else if(lt=='X'){
                          //  sm.player1();
                        }
                        break;}
                    validaPecaE();
                    int col = 0, valida = 0;
                    do {
                        col = UtilUI.pedeInteiro("\nInsira a coluna\n>");
                        if (!sm.getVT(col)) {
                            System.out.println("Coluna invalida");
                            valida = 0;
                        } else {
                            valida = 1;
                        }
                    } while (valida == 0);
                }
                else if(sm.getModoSM() == 2){

                    if(lt=='X'){
                        System.out.println(sm.getPlsm());
                        sm.getPC(lt);
                    }else if(lt=='0'){
                        miniJogos();
                        validaPecaE();
                        int col=0,valida=0;
                        showPL();
                        do {
                            col = UtilUI.pedeInteiro("\nInsira a coluna\n>");
                            if (!sm.getVT(col)) {
                                System.out.println("Coluna invalida");
                                valida = 0;
                            } else {
                                valida = 1;
                            }
                        } while (valida == 0);
                    }
                }  else if(sm.getModoSM()==3){
                    System.out.println(sm.getPlsm());
                    sm.getPC(lt);
                }
                //sm.incMiniSM();
                vit();
                break;
            case 2:
                int dec=0;
                boolean validation=false;
                do {
                    if(turno==1||sm.getPlsm().getCreditos()<1){
                        System.out.println("Creditos indisponiveis");
                        break;}
                    dec = UtilUI.pedeInteiro("\nNumero de turnos a decrementar\n>");
                    validation = sm.validaTurnDec(dec);

                }while (!validation);

                for(int i=0;i<dec;i++)
                    sm.undo();
                sm.decTrn(dec);
                showtab();
                int col,valida=0;
                do {
                    col = UtilUI.pedeInteiro("\nInsira a coluna\n>");
                    if (!sm.getVT(col)) {
                        System.out.println("Coluna invalida");
                        valida = 0;
                    } else {
                        valida = 1;
                        sm.resetMiniTurno(lt);
                    }
                } while (valida == 0);
                vit();
                break;
            case 3:
                //sm.saveSM();
                playerUI();
                break;
            case 4:
                showL();
                break;
            default:
                sair = true;
                break;
        }
        sm.incTurnoSM();
    }
    public void validaPecaE(){
        int valida=0,col;
        if(sm.verificaPecaSM()){
            System.out.println("Peca especial disponivel\n   Deseja usar?");
            int op = UtilUI.escolheOpcao("Sim ", "Nao");
            switch (op){
                case 1:
                    do {
                        col = UtilUI.pedeInteiro("\nInsira a coluna\n>");
                        if (!sm.verificaColPecaSM(col)) {
                            System.out.println("Coluna invalida");
                            valida = 0;
                        } else {
                            valida = 1;
                        }
                    } while (valida == 0);
                    sm.resetMiniTurno(lt);
                    vit();
                    break;
                default:
                    vit();
                    break;
            }
        }
    }

    public void vit(){
        showtab();
        if(sm.getWin()==1){
            System.out.println("VENCEDOR\n    "+sm.getPlsm().getNome());
        }else if(sm.getWin()==2){
            System.out.println("EMPATE");
            sm.termina();
        }
        if(lt=='0') {
           // sm.player0();
        }else if(lt=='X'){
           // sm.player1();
        }
    }

    public void showPL(){
        System.out.println("___________________________\n | Jogador=" + sm.getPlsm().getNome() +
                " \n | creditos=" + sm.getPlsm().getCreditos() +
                " \n | peca=" + sm.getPlsm().getpeca() +
                "\n___________________________\n");
    }
    public void showL(){
        System.out.println("\n " + sm.showLogs());
    }

}