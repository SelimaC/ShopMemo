package com.example.selima.shopmemo.model;

import java.io.Serializable;

/**
 * Created by filippo on 30/12/16.
 */

public class Product implements Serializable {
    String nome;
    String negozio;
    Double prezzo; //Si potrebbe usare una stringa per non avere troppi decimali?
    String categoria; //Si può fare un enumerazione per le categorie
    //La foto
    //Voto

    Product(){
    }

}
