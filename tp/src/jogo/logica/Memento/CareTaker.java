package jogo.logica.Memento;

import jogo.logica.dados.Jogo;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;


public class CareTaker implements Serializable{
    private final IMementoOriginator originator;


    private Deque<Memento> stackHist = new ArrayDeque<>();
    private Deque<Memento> stackRedo = new ArrayDeque<>();


    public CareTaker(IMementoOriginator org) {
        this.originator = org;
    }

    public void gravaMemento() {
        try{
            stackHist.push(originator.getMemento());

        } catch(IOException ex) {
            stackHist.clear();

        }
    }

    public void undo() {
        if (stackHist.isEmpty()) {
            return;
        }
        try {
            Memento now = originator.getMemento();
            stackRedo.push(now);
            Memento before = stackHist.pop();
            originator.setMemento(before);
        } catch(IOException | ClassNotFoundException ex) {
            System.err.println("undo: " + ex);
            stackHist.clear();
            stackRedo.clear();
        }

    }


    public void restart() {
        int tam = stackHist.size();
        for(int i=0;i<tam;i++) {
            undo();
        }
    }


    public void redo() {
        if (stackRedo.isEmpty()) {
            return;
        }
        try {
            Memento sredo = stackRedo.pop();
            stackHist.push(originator.getMemento());
            originator.setMemento(sredo);
        } catch(IOException | ClassNotFoundException ex) {
            System.err.println("redo: " + ex);
            stackHist.clear();
            stackRedo.clear();
        }
    }

    public void limpar(){
        stackHist.clear();
        stackRedo.clear();
    }



}


