package com.example.selima.shopmemo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by filippo on 30/12/16.
 */

public class Combo implements Serializable {
    private static Integer maxId = 0;

    List<Product> listaProdotti = new ArrayList<>();
    private String nome;
    private Integer id;

    Combo(List<Product> l, String nome){
        if (l!= null) {
            listaProdotti.addAll(l);
        }
        this.nome = nome;
        id = maxId++;
    }

    public String getNome(){
        return nome;
    }

    public List<Product> getListaProdotti() {
        return listaProdotti;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public Double getPrezzoTotale(){
        Double totale = 0.;
        for(Product p: listaProdotti){
            totale += p.getPrezzo();
        }
        return totale;
    }

    public int getNumOggetti(){
        if(listaProdotti != null) return listaProdotti.size();
        else return 0;
    }

    public static void setMaxId(int max){
        maxId=max;
    }

    public static int getMaxId(){
        return maxId;
    }
}
