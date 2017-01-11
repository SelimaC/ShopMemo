package com.example.selima.shopmemo;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.example.selima.shopmemo.model.Categoria;
import com.example.selima.shopmemo.model.Product;
import com.example.selima.shopmemo.model.ProductFactory;

import java.util.ArrayList;
import java.util.List;

public class Categories_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories_);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();

        ab.setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        String cat = i.getStringExtra("categoria");
        switch (cat){
            case "cat1": ab.setTitle("Alimentari e cura della persona");
                break;
            case "cat2":  ab.setTitle("Libri, film e musica");
                break;
            case "cat3":  ab.setTitle("Elettronica, informatica e giochi");
                break;
            case "cat4":  ab.setTitle("Casa, giardino, fai da te");
                break;
            case "cat5":  ab.setTitle("Sport, auto e tempo libero");
                break;
            case "cat6":  ab.setTitle("Vestiario, scarpe e accessori");
                break;
            default: break;
        }


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
