package com.example.selima.shopmemo.model;

import java.io.Serializable;

/**
 * Created by filippo on 30/12/16.
 */

public class Product implements Serializable {
    private static Integer maxId = 0;
    //Obbligatori
    private Integer id;
    private String nome;
    private String negozio;
    private Double prezzo;
    private String pathFoto;
    //Opzionali
    private Categoria categoria;
    private Float voto = 0f;

    public Product(String nome, String negozio, Double prezzo, String pathFoto) {
        this.id = maxId++;
        this.nome = nome;
        this.negozio = negozio;
        this.prezzo = prezzo;
        this.pathFoto = pathFoto;
    }

    public Integer getId(){
        return id;
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNegozio() {
        return negozio;
    }

    public void setNegozio(String negozio) {
        this.negozio = negozio;
    }

    public Double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Double prezzo) {
        this.prezzo = prezzo;
    }

    public String getPathFoto() {
        return pathFoto;
    }

    public void setPathFoto(String pathFoto) {
        this.pathFoto = pathFoto;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Float getVoto() {
        return voto;
    }

    public void setVoto(Float voto) {
        this.voto = voto;
    }




}

