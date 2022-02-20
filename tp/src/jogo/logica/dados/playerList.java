package jogo.logica.dados;

import java.io.Serializable;



public class playerList implements Serializable{

    String nome;
    int ltnum;
    int creditos;
    Boolean peca = false;

    public playerList(String nome, int ltnum, int creditos, boolean peca){

        this.nome = nome;
        this.ltnum = ltnum;
        this.creditos = creditos;
        this.peca = peca;


    }

    public int getCreditos(){
        return this.creditos;
    }
    public Boolean getpeca(){
        return this.peca;
    }

    public String getNome(){
        return this.nome;
    }

    public int getlet(){return this.ltnum;}















}
