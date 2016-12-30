package com.example.selima.shopmemo.model;

/**
 * Created by filippo on 30/12/16.
 */

public class ProductFactory {
    private static ProductFactory singleton = null;
    private ProductFactory(){
    }
    public static ProductFactory getInstance(){
        if (singleton == null)
            singleton = new ProductFactory();
        return singleton;
    }
}
