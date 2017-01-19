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
                return p2.getVoto().compareTo(p1.getVoto());
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
                return p2.getId()- p1.getId();
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

    public List<Product> getProductsFiltered(CharSequence s){
        List<Product> temp = new ArrayList<>();
        for(Product p : allProduct){
            if(p.getNome().toLowerCase().contains(s.toString().toLowerCase())) {
                temp.add(p);
            }
        }
        return temp;
    }

    public void createNewProduct(String nome, String negozio, Double prezzo, String pathFoto, Categoria cat, Float voto){
        Product temp = new Product(nome, negozio, prezzo, pathFoto);
        temp.setCategoria(cat);
        temp.setVoto(voto);
        allProduct.add(temp);
        saveListToFile();
    }
    public void createNewProduct(Product p){
        allProduct.add(p);
        saveListToFile();
    }

    public void deleteProduct(Integer id){
        Log.d("elimina", "prdootto"+id);
        Product prod = getProductById(id);
        List<Combo> l = ComboFactory.getInstance(context).getAllCombo();
        for(Combo c: l){
            if(c.listaProdotti.contains(prod)){
                ComboFactory.getInstance(context).removeProductFromCombo(id,c.getId());
            }
        }
        allProduct.remove(prod);
        saveListToFile();
    }

    public Product getProductById(Integer id) {
        for(Product p: allProduct){
            if(p.getId().equals(id)){
                return p;
            }
        }
        return null;
    }

    public List<Combo> getComboIn(Integer id){
        Product prod = getProductById(id);
        List<Combo> l = ComboFactory.getInstance(context).getComboByName();
        List<Combo> lista = new ArrayList<>();
        for(Combo c: l){
            if(c.listaProdotti.contains(prod)){
                lista.add(c);
            }
        }
        Log.d("Prodotti","Il prodotto è presente nelle combo"+lista);
        return lista;
    }

    private void generateInitialList() {
        Product temp = new Product("T-Shirt", "OVS", 14.90, "");
        temp.setCategoria(Categoria.CAT6);
        temp.setVoto(4.5f);
        temp.setPathFoto("tshirt");
        allProduct.add(temp);

        temp = new Product("Jeans neri", "Bershka", 29.00, "");
        temp.setVoto(4.0f);
        temp.setPathFoto("jeans");
        allProduct.add(temp);

        temp = new Product("Monitor 22\"", "MediaWorld", 189.90, "" );
        temp.setCategoria(Categoria.CAT3);
        temp.setPathFoto("monitor");
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
            System.out.println("Product class not found");
            c.printStackTrace();
            return;
        }

        if(allProduct.size()==0) Product.setMaxId(0);
        else{
            int max = allProduct.get(0).getId();
            for(Product p : allProduct){
                if(p.getId()>max) max=p.getId();
            }
            Product.setMaxId(max+1);
        }
    }
}
