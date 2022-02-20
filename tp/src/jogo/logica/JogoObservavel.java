package jogo.logica;
import jogo.logica.dados.playerList;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.util.List;

public class JogoObservavel {
    private StateMachine sm;
    private final PropertyChangeSupport propertyChangeSupport;

    public JogoObservavel(StateMachine jogoMaqEstados) {
        this.sm = jogoMaqEstados;
        propertyChangeSupport = new PropertyChangeSupport(jogoMaqEstados);
    }
    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
    }

    public situation getSituation(){return sm.getSituation();}

    public void start(int n){sm.comeca(n);
        propertyChangeSupport.firePropertyChange("jjjj", null, null);}


    public void modoJogo(int modo){sm.gamemode(modo);
        propertyChangeSupport.firePropertyChange("jjjj", null, null);}

    public void id(String n1, String n2){sm.id(n1,n2);
        propertyChangeSupport.firePropertyChange("jjjj", null, null);}

    public void p0(int peca, int opt){
        sm.saveMem();
        sm.player0(peca, opt);

        propertyChangeSupport.firePropertyChange("jjjj", null, null);}

    public void p1(int peca, int opt){
        sm.saveMem();
        sm.player1(peca, opt);

        propertyChangeSupport.firePropertyChange("jjjj", null, null);
    }
    public void escolheM(Boolean b){sm.esperaMini(b);
        propertyChangeSupport.firePropertyChange("jjjj", null, null);}
    public void MiniMat(Boolean b){sm.miniJogoMat(b);
        propertyChangeSupport.firePropertyChange("jjjj", null, null);}
    public void MiniFrase(Boolean b){sm.miniJogoFrase(b);
        propertyChangeSupport.firePropertyChange("jjjj", null, null);}

    public void endgame(){sm.termina();
        propertyChangeSupport.firePropertyChange("jjjj", null, null);}


    public void loadF(File name){sm.loadautoSaveSM(name);}
    public void reset(){sm.resetGame();}
    public void gravar(File file){sm.saveSM(file);}
    public void ler(File file){
        sm.loadSM(file);
        propertyChangeSupport.firePropertyChange("jjjj", null, null);}

    public void setmodo(int m){sm.setModo(m);}
    public int getmodo(){return sm.getModoSM();}
    public void setnum(int n){sm.setnumltSM(n);}
    public void redoo(){sm.redoGame(); }
    public void back(){
        sm.undo();

    }




    public playerList getp(){return sm.getPlsm();}

    public List<String> logs(){return sm.showLogs();}

    public char[][] getTab(){return sm.getTabSM();}

    public int getTurn(){return sm.getTurnoSM();}
    public int getTurnC(){return sm.getTurnoSMC();}
    public int getTmini(char lt){return sm.getMiniTurno(lt);}

    public String getFrase(){return sm.miniJogoW();}
    public Boolean verificaFrase(String s1, String s2, long start){return sm.verificaMJW(s1,s2,start);}

    public String[] getContas(){return sm.miniJogoM();}
    public Boolean verificaMat(String[] m){return sm.VerificaMJM(m);}

    public void autosave(){sm.autoSaveSM();}


}
