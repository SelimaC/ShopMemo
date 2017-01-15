package com.example.selima.shopmemo.model;

import android.content.Context;
import android.util.Log;
import android.widget.CompoundButton;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by filippo on 30/12/16.
 */

public class ComboFactory {
    private List<Combo> allCombo = new ArrayList<>();
    private Context context;
    private static ComboFactory singleton = null;

    private ComboFactory(Context context){
        this.context = context;
    }
    public static ComboFactory getInstance(Context context){
        if (singleton == null)
            singleton = new ComboFactory(context);
        return singleton;
    }

    public List<Combo> getAllCombo(){
        if(allCombo.isEmpty()){
            Log.d("combo","caricando dal file");
            System.out.println("carico il file");
            loadListFromFile();
            if (allCombo.isEmpty()) {
                Log.d("combo","generando da zero");
                generateInitialList();
            }
        }
        return getComboByTime();
    }

    public List<Combo> getComboByTime() {
        Collections.sort(allCombo, new Comparator<Combo>() {
            @Override
            public int compare(Combo c1, Combo c2) {
                return c1.getId().compareTo(c2.getId());
            }
        });
        return allCombo;
    }

    public List<Combo> getComboByName(){
        Collections.sort(allCombo, new Comparator<Combo>() {
            @Override
            public int compare(Combo c1, Combo c2) {
                return c1.getNome().compareTo(c2.getNome());
            }
        });
        return allCombo;
    }

    public List<Combo> getComboByPrice(){
        Collections.sort(allCombo, new Comparator<Combo>() {
            @Override
            public int compare(Combo c1, Combo c2) {
                return c1.getPrezzoTotale().compareTo(c2.getPrezzoTotale());
            }
        });
        return allCombo;
    }

    public void createNewCombo(List<Product> list, String name){
        Combo combo = new Combo(list, name);
        allCombo.add(combo);
        saveListToFile();
    }

    public void deleteCombo(Integer id){
        allCombo.remove(getComboById(id));
        saveListToFile();
    }

    public void addProductToCombo(Integer idProd, Integer idCombo){
        Combo combo = getComboById(idCombo);
        Product prod = ProductFactory.getInstance(context).getProductById(idProd);
        combo.listaProdotti.add(prod);
        saveListToFile();
    }

    public void removeProductFromCombo(Integer idProd, Integer idCombo){
        Combo combo = getComboById(idCombo);
        Product prod = ProductFactory.getInstance(context).getProductById(idProd);
        combo.listaProdotti.remove(prod);
        saveListToFile();
    }

    public Combo getComboById(Integer id) {
        for(Combo c: allCombo){
            if(c.getId().equals(id)){
                return c;
            }
        }
        return null;
    }

    private void generateInitialList() {

        Combo combo = new Combo(null,"Regali per Ugo");
        allCombo.add(combo);
        combo = new Combo(null,"Matrimonio");
        allCombo.add(combo);
        combo = new Combo(null,"Spesa natale");
        allCombo.add(combo);
        saveListToFile();
    }

    private void loadListFromFile() {
        try {
            FileInputStream fileIn = context.openFileInput("listaCombo");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            allCombo = (List<Combo>) in.readObject();
            in.close();
            fileIn.close();
        }catch(IOException i) {
            i.printStackTrace();
            return;
        }catch(ClassNotFoundException c) {
            System.out.println("Combo class not found");
            c.printStackTrace();
            return;
        }
    }

    private void saveListToFile() {
        try {
            FileOutputStream fos = context.openFileOutput("listaCombo", Context.MODE_PRIVATE);
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(allCombo);
            out.close();
            fos.close();
        }catch(IOException i) {
            i.printStackTrace();
        }
    }
}
