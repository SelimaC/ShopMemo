package com.example.selima.shopmemo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by filippo on 30/12/16.
 */

public class Combo implements Serializable {
    List<Product> listaProdotti = new ArrayList<>();

    Combo(){
    }

    Combo(List<Product> l){
        if (l!= null) {
            listaProdotti.addAll(l);
        }
    }

}
