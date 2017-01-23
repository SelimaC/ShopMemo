package com.example.selima.shopmemo;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.selima.shopmemo.model.Categoria;
import com.example.selima.shopmemo.model.Product;
import com.example.selima.shopmemo.model.ProductFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;

public class NewObject_Activity extends AppCompatActivity {

    final int PHOTO_REQUEST_CODE = 1;
    boolean take = false;
    Bitmap photo;
    DialogFragment settingsDialog;
    Categoria categoria = Categoria.NO_CAT;
    EditText nome;
    EditText negozio;
    EditText prezzo;
    RatingBar preferenza;
    TextView scatta;
    Integer idObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_object);

        Product obj = new Product("","", 0d,"");
        idObj = obj.getId();
        ProductFactory.getInstance(this).createNewProduct(obj);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();

        ab.setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductFactory.getInstance(getApplicationContext()).deleteProduct(idObj);
                Product.setMaxId(Product.getMaxId()-1);
                finish();
            }
        });

        ab.setTitle("Nuovo oggetto");

        nome = (EditText) findViewById(R.id.nomeoggetto);
        negozio = (EditText) findViewById(R.id.negoziooggetto);
        prezzo = (EditText) findViewById(R.id.prezzooggetto);
        preferenza = (RatingBar) findViewById(R.id.ratingBar);
        scatta = (TextView) findViewById(R.id.scatta);

        ImageView takephoto = (ImageView)findViewById(R.id.takephoto);
        takephoto.setImageResource(R.drawable.ic_camera);
        takephoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(photoIntent, PHOTO_REQUEST_CODE);
            }
        });


        final Activity con = this;
        ImageButton cat = (ImageButton) findViewById(R.id.plusCat);
        cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final FragmentManager supportFragment = getFragmentManager();
                DialogFragment fragment = new InsertCategoryDialog();
                fragment.show(supportFragment,"categoria");
            }
        });
        Log.d("lista", ProductFactory.getInstance(this).getAllProducts().toString());

        ImageButton comb = (ImageButton) findViewById(R.id.plusCombo);
        comb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FragmentManager supportFragment = getFragmentManager();

                DialogFragment frag = new AddProdToComboDialog();
                Bundle bundle = new Bundle();
                bundle.putInt("IDPROD",idObj);

                frag.setArguments(bundle);
                frag.show(supportFragment,"conferma");
            }
        });

        Button crea = (Button) findViewById(R.id.crea);
        crea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean check = true;
                String nomes="", negozios="";
                Double prezzod=0d;
                float pref = 0f;

                if(nome.getText().toString().equals("") || nome.getText()==null){
                    check = false;
                    nome.setError("Inserire il nome");
                }
                else nomes=nome.getText().toString();

                if(negozio.getText().toString().equals("") || negozio.getText()==null){
                    check = false;
                    negozio.setError("Inserire il negozio");
                }
                else negozios=negozio.getText().toString();

                if(prezzo.getText().toString().equals("") || prezzo.getText()==null){
                    check = false;
                    prezzo.setError("Inserire il prezzo");
                }
                else prezzod=Double.parseDouble(prezzo.getText().toString());

                if(photo!=null){

                    ContextWrapper cw = new ContextWrapper(getApplicationContext());
                    File directory = cw.getDir("object", Context.MODE_PRIVATE);
                    if (!directory.exists()) {
                        directory.mkdir();
                    }
                    File mypath = new File(directory, "obj"+ ProductFactory.getInstance(con).getProductById(idObj).getId()+ ".JPG");

                    ProductFactory.getInstance(con).getProductById(idObj).setPathFoto(mypath.getPath());
                    Log.d("SAVE_IMAGE", "path : " + mypath.getPath());

                    FileOutputStream fos = null;
                    try {
                        fos = new FileOutputStream(mypath);
                        photo.compress(Bitmap.CompressFormat.PNG, 100, fos);
                        fos.close();
                    } catch (Exception e) {
                        Log.e("SAVE_IMAGE", e.getMessage(), e);
                    }
                }
                else{
                    check=false;
                    scatta.setTextColor(Color.RED);
                }

                pref = preferenza.getRating();

                if(check) {
                    ProductFactory.getInstance(con).getProductById(idObj).setNome(nomes);
                    ProductFactory.getInstance(con).getProductById(idObj).setNegozio(negozios);
                    ProductFactory.getInstance(con).getProductById(idObj).setPrezzo(prezzod);
                    ProductFactory.getInstance(con).getProductById(idObj).setVoto(pref);
                    ProductFactory.getInstance(con).getProductById(idObj).setCategoria(categoria);

                    Toast.makeText(getApplicationContext(), "Oggetto creato", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else {

                }

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==PHOTO_REQUEST_CODE)
        {
            if(data!=null) {
                if(data.getExtras() != null){
                    photo = (Bitmap) data.getExtras().get("data");
                    if (photo != null) {
                        TextView scatta = (TextView) findViewById(R.id.scatta);
                        scatta.setVisibility(View.INVISIBLE);
                        ImageView takephoto = (ImageView) findViewById(R.id.takephoto);
                        takephoto.setImageResource(0);
                        takephoto.setImageBitmap(photo);
                    }
                }
            }
        }
    }

    public void selectCat(View v) {
        Log.d("prova",""+v);
        switch (v.getId()) {
            case R.id.cat1i:
            case R.id.cat1t:
                Toast.makeText(getApplicationContext(), "Alimentari e cura della persona", Toast.LENGTH_SHORT).show();
                categoria=Categoria.CAT1;
                findViewById(R.id.plusCat).setBackgroundResource(R.drawable.cat1);
                break;
            case R.id.cat2i:
            case R.id.cat2t:
                Toast.makeText(getApplicationContext(), "Libri, film, musica", Toast.LENGTH_SHORT).show();
                categoria=Categoria.CAT2;
                findViewById(R.id.plusCat).setBackgroundResource(R.drawable.cat2);
                break;
            case R.id.cat3i:
            case R.id.cat3t:
                Toast.makeText(getApplicationContext(), "Elettronica, informatica e giochi", Toast.LENGTH_SHORT).show();
                categoria=Categoria.CAT3;
                findViewById(R.id.plusCat).setBackgroundResource(R.drawable.cat3);
                break;
            case R.id.cat4i:
            case R.id.cat4t:
                Toast.makeText(getApplicationContext(), "Casa, giardino e fai da te", Toast.LENGTH_SHORT).show();
                categoria=Categoria.CAT4;
                findViewById(R.id.plusCat).setBackgroundResource(R.drawable.cat4);
                break;
            case R.id.cat5i:
            case R.id.cat5t:
                Toast.makeText(getApplicationContext(), "Sport, auto e tempo libero", Toast.LENGTH_SHORT).show();
                categoria=Categoria.CAT5;
                findViewById(R.id.plusCat).setBackgroundResource(R.drawable.cat5);
                break;
            case R.id.cat6i:
            case R.id.cat6t:
                Toast.makeText(getApplicationContext(), "Vestiario, scarpe e accessori", Toast.LENGTH_SHORT).show();
                categoria=Categoria.CAT6;
                findViewById(R.id.plusCat).setBackgroundResource(R.drawable.cat6);
                break;
        }
        if(settingsDialog != null) {
            settingsDialog.dismiss();
        }

    }

    @Override
    public void onBackPressed() {
        ProductFactory.getInstance(getApplicationContext()).deleteProduct(idObj);
        Product.setMaxId(Product.getMaxId()-1);
        finish();
    }

}
