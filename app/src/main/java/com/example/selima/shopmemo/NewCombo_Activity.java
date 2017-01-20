package com.example.selima.shopmemo;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
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
        Window window = getWindow();
        window.setStatusBarColor(Color.parseColor("#EF6C00"));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent i = getIntent();
        final String nomeCombo = (i.getStringExtra("NOME"));
        final boolean creazione = i.getBooleanExtra("CREAZIONE",false);

        Log.d("bool","vsl : " + creazione);
        final int idCombo = i.getIntExtra("ID",-1);;

        if (creazione)
          ab.setTitle(nomeCombo);
        else
          ab.setTitle("Aggiunta oggetti");
        //Setto la lista
        final ListView myList = (ListView)findViewById(R.id.list);
        final List<Product> listaProdotti = new ArrayList<>();
        if(creazione)
          listaProdotti.addAll(ProductFactory.getInstance(this).getProductsByName());
        else {
            listaProdotti.addAll(ComboFactory.getInstance(this).getProductNotIn(idCombo));
            Button b = (Button) findViewById(R.id.crea);
            b.setText("Aggiungi");
        }
        final List<String> listaNomi = new ArrayList<>();
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
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                R.layout.checklistcombo,
                listContent);
        
        myList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        myList.setAdapter(adapter);
        //Setto il button
        Button button = (Button) findViewById(R.id.crea);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Product> selectedProducts = new ArrayList<>();
                int cntChoice = myList.getCount();
                SparseBooleanArray sparseBooleanArray = myList.getCheckedItemPositions();
                for(int i = 0; i < cntChoice; i++) {
                    if (sparseBooleanArray.get(i)) {
                        selectedProducts.add(listaProdotti.get(i));
                    }
                }
                if(creazione)
                   ComboFactory.getInstance(getApplicationContext()).createNewCombo(selectedProducts, nomeCombo);
                else
                   ComboFactory.getInstance(getApplicationContext()).getComboById(idCombo).getListaProdotti().addAll(selectedProducts);
                finish();
            }});

        //Setto il filtro
        EditText filter = (EditText) findViewById(R.id.filter);
        filter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //This method is called to notify you that, within s, the count characters beginning at start have
                // just replaced old text that had length before. It is an error to attempt to make changes to s from this callback.
                //List<Product> lista = ProductFactory.getInstance(getApplicationContext()).getProductsFiltered(s);
                //genero la lista di charseq
                //List<String> listaNomi = new ArrayList<>();
                listaProdotti.clear();
                listaProdotti.addAll(ProductFactory.getInstance(getApplicationContext()).getProductsFiltered(s));
                listaNomi.clear();
                for(Product p : listaProdotti){
                    listaNomi.add(p.getNome());
                }
                String[] listContent = listaNomi.toArray(new String[0]);
                //riimposto i prodotti
                ArrayAdapter<String> adapter = new ArrayAdapter(getApplicationContext(),
                        R.layout.checklistcombo,
                        listContent);
                /*{
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        View view = super.getView(position, convertView, parent);
                        TextView text = (TextView) view.findViewById(android.R.id.text1);
                        text.setTextColor(Color.BLACK);
                        CheckBox c = (CheckBox)view.findViewById(android.R.id.c);
                        c.setTextColor(Color.BLACK);
                        return view;
                    }
                };*/
                myList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                myList.setAdapter(adapter);
                myList.invalidate();


            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }
}
