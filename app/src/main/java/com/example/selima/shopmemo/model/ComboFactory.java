package com.example.selima.shopmemo.model;

/**
 * Created by filippo on 30/12/16.
 */

public class ComboFactory {
    private static ComboFactory singleton = null;
    private ComboFactory(){
    }
    public static ComboFactory getInstance(){
        if (singleton == null)
            singleton = new ComboFactory();
        return singleton;
    }
}
