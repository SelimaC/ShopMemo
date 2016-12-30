package com.example.selima.shopmemo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by filippo on 30/12/16.
 */

public class Combo implements Serializable {
    List<Product> listaProdotti;

    Combo(){
        listaProdotti = new ArrayList<>();
    }
    Combo(List<Product> l){
        listaProdotti = new ArrayList<>();
        listaProdotti.addAll(l);
    }

}
