package jogo.logica.dados;

import jogo.logica.Memento.CareTaker;

import java.io.*;

public class Save {
    Jogo jogo;
    CareTaker caretaker;

    public static boolean ValidaFile(String s){
        File f = new File("replays/"+s+".bin");
        return f.exists();
    }

    public static String autosaves(Jogo jogo, CareTaker caretaker) {
        Object[] things = new Object[2];
        File theDir = new File("../tp/replays");
        if (!theDir.exists()){
            theDir.mkdirs();
        }
        int cont=theDir.list().length;
        String FileName = theDir+"/AutoSave"+  cont + ".bin";
        if(cont==5){
            File[] logFiles = theDir.listFiles();
            long oldestDate = Long.MAX_VALUE;
            File oldestFile = null;
            for(File f: logFiles){
                if(f.lastModified() < oldestDate){
                    oldestDate = f.lastModified();
                    oldestFile = f;
                }
            }
            if(oldestFile != null){
                FileName = oldestFile.getName();
                oldestFile.delete();
            }
        }
        try {
            things[0] = jogo;
            things[1] = caretaker;
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FileName));
            oos.writeObject(things);
            oos.close();
            cont++;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return FileName;
    }
    public static Object[] replayGames(File filename) {
        Object[] aux;

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));
            aux = (Object[]) ois.readObject();

            ois.close();
            return aux;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }



    public static void saves(Jogo jogo, CareTaker caretaker,File file) {
        Object[] things = new Object[2];

        try {

            things[0] = jogo;
            things[1] = caretaker;
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(things);

            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static Object[] load(File file) {
        Object[] aux;

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            aux = (Object[]) ois.readObject();

            ois.close();
            return aux;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


}
