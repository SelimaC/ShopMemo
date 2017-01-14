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
    private Categoria categoria = Categoria.NO_CAT;
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
        if (categoria == null)
            this.categoria = Categoria.NO_CAT;
    }

    public Float getVoto() {
        return voto;
    }

    public void setVoto(Float voto) {
        this.voto = voto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (id != null ? !id.equals(product.id) : product.id != null) return false;
        if (nome != null ? !nome.equals(product.nome) : product.nome != null) return false;
        if (negozio != null ? !negozio.equals(product.negozio) : product.negozio != null)
            return false;
        if (prezzo != null ? !prezzo.equals(product.prezzo) : product.prezzo != null) return false;
        if (pathFoto != null ? !pathFoto.equals(product.pathFoto) : product.pathFoto != null)
            return false;
        if (categoria != product.categoria) return false;
        return voto != null ? voto.equals(product.voto) : product.voto == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        result = 31 * result + (negozio != null ? negozio.hashCode() : 0);
        result = 31 * result + (prezzo != null ? prezzo.hashCode() : 0);
        result = 31 * result + (pathFoto != null ? pathFoto.hashCode() : 0);
        result = 31 * result + (categoria != null ? categoria.hashCode() : 0);
        result = 31 * result + (voto != null ? voto.hashCode() : 0);
        return result;
    }
}

