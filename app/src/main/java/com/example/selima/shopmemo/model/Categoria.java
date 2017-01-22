package com.example.selima.shopmemo.model;

import java.io.Serializable;

public enum Categoria implements Serializable {
    CAT1, CAT2, CAT3, CAT4, CAT5, CAT6, NO_CAT;

    @Override
    public String toString(){
        switch (this){
            case CAT1: return "Alimentari e cura della persona";
            case CAT2: return "Libri, film, musica";
            case CAT3: return "Elettronica, informatica e giochi";
            case CAT4: return "Casa, giardino e fai da te";
            case CAT5: return "Sport, auto e tempo libero";
            case CAT6: return "Vestiario, scarpe e accessori";
            default: return "Nessuna";
        }
    }
}
