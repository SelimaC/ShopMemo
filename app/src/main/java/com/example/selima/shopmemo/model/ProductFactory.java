package com.example.selima.shopmemo.model;

import android.content.Context;
import android.util.Log;

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

public class ProductFactory {
    private List<Product> allProduct = new ArrayList<>();
    private Context context;

    private static ProductFactory singleton = null;
    private ProductFactory(Context context){
        this.context = context;
    }

    public static ProductFactory getInstance(Context context){
        if (singleton == null) {
            singleton = new ProductFactory(context);
        }
        return singleton;
    }

    public List<Product> getAllProducts(){
        if(allProduct.isEmpty()){
            Log.d("prodotti","caricando dal file");
            System.out.println("carico il file");
            loadListFromFile();
            if (allProduct.isEmpty()) {
                Log.d("prodotti","generando da zero");
                System.out.println("carico da zero");
                generateInitialList();
            }
        }
        return getProductsByTime();
    }

    public List<Product> getProductsByName(){
        Collections.sort(allProduct, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return p1.getNome().compareTo(p2.getNome());
            }
        });
        return allProduct;
    }

    public List<Product> getProductsByPrice(){
        Collections.sort(allProduct, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return p1.getPrezzo().compareTo(p2.getPrezzo());
            }
        });
        return allProduct;
    }

    public List<Product> getProductsByPreference(){
        Collections.sort(allProduct, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return p1.getVoto().compareTo(p2.getVoto());
            }
        });
        return allProduct;
    }

    public List<Product> getProductsByCategory(){
        Collections.sort(allProduct, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return p1.getCategoria().compareTo(p2.getCategoria());
            }
        });
        return allProduct;
    }

    public List<Product> getProductsByTime(){
        Collections.sort(allProduct, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return p1.getId().compareTo(p2.getId());
            }
        });
        return allProduct;
    }

    public List<Product> getProductsFiltered(Categoria cat){
        List<Product> temp = new ArrayList<>();
        for(Product p: allProduct){
            if (p.getCategoria().equals(cat)){
                temp.add(p);
            }
        };
        return temp;
    }

    public void createNewProduct(String nome, String negozio, Double prezzo, String pathFoto, Categoria cat, Float voto){
        Product temp = new Product(nome, negozio, prezzo, pathFoto);
        temp.setCategoria(cat);
        temp.setVoto(voto);
        allProduct.add(temp);
        saveListToFile();
    }

    public void deleteProduct(Integer id){
        allProduct.remove(getProductById(id));
        saveListToFile();
    }

    private Product getProductById(Integer id) {
        for(Product p: allProduct){
            if(p.getId().equals(id)){
                return p;
            }
        }
        return null;
    }

    private void generateInitialList() {
        Product temp = new Product("T-Shirt", "OVS", 14.90, "");
        temp.setCategoria(Categoria.CAT1);
        temp.setVoto(4.2f);
        allProduct.add(temp);

        temp = new Product("Jeans neri", "Bershka", 29.00, "");
        temp.setVoto(4.8f);
        allProduct.add(temp);

        temp = new Product("Monitor 22\"", "MediaWorld", 189.90, "" );
        temp.setCategoria(Categoria.CAT2);
        allProduct.add(temp);

        saveListToFile();
    }

    private void saveListToFile() {
        try {
            FileOutputStream fos = context.openFileOutput("listaProdotti", Context.MODE_PRIVATE);
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(allProduct);
            out.close();
            fos.close();
        }catch(IOException i) {
            i.printStackTrace();
        }
    }

    private void loadListFromFile() {
        try {
            FileInputStream fileIn = context.openFileInput("listaProdotti");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            allProduct = (List<Product>) in.readObject();
            in.close();
            fileIn.close();
        }catch(IOException i) {
            i.printStackTrace();
            return;
        }catch(ClassNotFoundException c) {
            System.out.println("Employee class not found");
            c.printStackTrace();
            return;
        }
    }
}
