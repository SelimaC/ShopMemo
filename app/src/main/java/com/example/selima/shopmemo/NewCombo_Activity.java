package com.example.selima.shopmemo;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.selima.shopmemo.model.Combo;
import com.example.selima.shopmemo.model.ComboFactory;
import com.example.selima.shopmemo.model.Product;
import com.example.selima.shopmemo.model.ProductFactory;

import java.util.ArrayList;
import java.util.List;

public class NewCombo_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_combo_);
//Setto la toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent i = getIntent();
        final String nomeCombo = (i.getStringExtra("NOME"));
        ab.setTitle(nomeCombo);
        //Setto la lista
        final ListView myList = (ListView)findViewById(R.id.list);
        final List<Product> listaProdotti = ProductFactory.getInstance(this).getAllProducts();
        List<String> listaNomi = new ArrayList<>();
        for(Product p : listaProdotti){
            listaNomi.add(p.getNome());
        }
        /*for(Product p : listaProdotti){
            listaNomi.add(p.getNome());
        }for(Product p : listaProdotti){
            listaNomi.add(p.getNome());
        }for(Product p : listaProdotti){
            listaNomi.add(p.getNome());
        }for(Product p : listaProdotti){
            listaNomi.add(p.getNome());
        }for(Product p : listaProdotti){
            listaNomi.add(p.getNome());
        }for(Product p : listaProdotti){
            listaNomi.add(p.getNome());
        }for(Product p : listaProdotti){
            listaNomi.add(p.getNome());
        }for(Product p : listaProdotti){
            listaNomi.add(p.getNome());
        }for(Product p : listaProdotti){
            listaNomi.add(p.getNome());
        }*/
        String[] listContent = listaNomi.toArray(new String[0]);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_multiple_choice,
                listContent);
        myList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        myList.setAdapter(adapter);
        //Setto il button
        Button button = (Button) findViewById(R.id.crea);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*  String selected = "";
                int cntChoice = myList.getCount();
                SparseBooleanArray sparseBooleanArray = myList.getCheckedItemPositions();
                for(int i = 0; i < cntChoice; i++){
                    if(sparseBooleanArray.get(i)) {
                        selected += myList.getItemAtPosition(i).toString() + "\n";
                    }
                }
                Toast.makeText(getApplicationContext(),
                        selected,
                        Toast.LENGTH_LONG).show();*/
                List<Product> selectedProducts = new ArrayList<>();
                int cntChoice = myList.getCount();
                SparseBooleanArray sparseBooleanArray = myList.getCheckedItemPositions();
                for(int i = 0; i < cntChoice; i++) {
                    if (sparseBooleanArray.get(i)) {
                        selectedProducts.add(listaProdotti.get(i));
                    }
                }
                ComboFactory.getInstance(getApplicationContext()).createNewCombo(selectedProducts, nomeCombo);
                finish();
            }});
    }
}
