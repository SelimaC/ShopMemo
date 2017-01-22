package com.example.selima.shopmemo;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.selima.shopmemo.model.Categoria;
import com.example.selima.shopmemo.model.Combo;
import com.example.selima.shopmemo.model.Product;
import com.example.selima.shopmemo.model.ProductFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class SummaryObject_Activity extends AppCompatActivity{
    List<Combo> c = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary_object_);

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
        String o = i.getStringExtra("oggetto");
        int id = Integer.parseInt(i.getStringExtra("oggetto"));

        Product obj = ProductFactory.getInstance(this).getProductById(id);
        ab.setTitle(obj.getNome());

        RatingBar rb = (RatingBar) findViewById(R.id.myRatingBar);
        rb.setRating(obj.getVoto());

        final ImageView photo = (ImageView) findViewById(R.id.photoobj);

        int idphoto = Home_Activity.context().getResources().getIdentifier(obj.getPathFoto(),
                "drawable", Home_Activity.context().getPackageName());

        if(idphoto!=0) {
            photo.setImageResource(idphoto);
        }
        else {
            Bitmap bitmap = null;
            File f = new File(obj.getPathFoto());
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            try {
                bitmap = BitmapFactory.decodeStream(new FileInputStream(f), null, options);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            photo.setImageBitmap(bitmap);
        }

        TextView shop = (TextView) findViewById(R.id.shop);
        TextView price = (TextView) findViewById(R.id.price);
        ImageView category = (ImageView) findViewById(R.id.category);
        TextView combo = (TextView) findViewById(R.id.combo);

        shop.setText(obj.getNegozio());
        price.setText( obj.getPrezzo() + " €");


        switch (obj.getCategoria()) {
            case CAT1:
                category.setImageResource(R.drawable.cat1);
                category.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       Toast.makeText(getApplicationContext(), "Alimentari e cura della persona", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case CAT2:
                category.setImageResource(R.drawable.cat2);
                category.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "Libri, film, musica", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case CAT3:
                category.setImageResource(R.drawable.cat3);
                category.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "Elettronica, informatica e giochi", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case CAT4:
                category.setImageResource(R.drawable.cat4);
                category.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "Casa, giardino e fai da te", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case CAT5:
                category.setImageResource(R.drawable.cat5);
                category.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "Sport, auto e tempo libero", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case CAT6:
                category.setImageResource(R.drawable.cat6);
                category.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "Vestiario, scarpe e accessori", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case NO_CAT: category.setVisibility(View.INVISIBLE);
                     TextView nocat = (TextView) findViewById(R.id.nocat);
                     nocat.setVisibility(View.VISIBLE);
                     nocat.setText("Nessuna");
        }

        c = ProductFactory.getInstance(this).getComboIn(id);

        LinearLayout l = (LinearLayout) findViewById(R.id.combos);
        if(c.size()==0) {
            TextView t = new TextView(this);
            t.setText("Nessuna");
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(150, 10, 10, 10);
            t.setLayoutParams(lp);
            t.setTextSize(20);

            l.addView(t);
        }
        else {
            for (final Combo item : c) {
                TextView t = new TextView(this);
                t.setText(" • " + item.getNome());
                t.setId(item.getId());
                t.setTextColor(getResources().getColor(R.color.colorAccent));
                t.setClickable(true);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lp.setMargins(150, 10, 10, 10);
                t.setLayoutParams(lp);
                t.setTextSize(20);
                l.addView(t);

                t.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Toast.makeText(v.getContext(), "Riepilogo combo", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(), SummaryCombo_Activity.class);
                        i.putExtra("combo", String.valueOf(item.getId()));
                        startActivity(i);
                    }
                });
            }
        }

         final Activity con = this;
         photo.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Dialog settingsDialog = new Dialog(con);

                 View v = con.getLayoutInflater().inflate(R.layout.image_layout, null);
                 ImageView iv = (ImageView) v.findViewById(R.id.img);
                 Bitmap bm = ((BitmapDrawable) photo.getDrawable()).getBitmap();
                 iv.setImageBitmap(bm);
                 settingsDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
                 settingsDialog.setContentView(v);
                 settingsDialog.show();
             }
         });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_summaryobject, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i = getIntent();
        String o = i.getStringExtra("oggetto");
        int id = Integer.parseInt(i.getStringExtra("oggetto"));
        switch (item.getItemId()) {
            case R.id.options:
                return true;
            case R.id.menu_summary1:
                Toast.makeText(this, "La modifica non è implementata", Toast.LENGTH_SHORT).show();

                return  true;
            case R.id.menu_summary2:
                final FragmentManager supportoFragment = getFragmentManager();
                DialogFragment fragment = new DeleteProductDialog();
                Bundle bundle = new Bundle();
                bundle.putInt("NUMCOMBO",ProductFactory.getInstance(getApplicationContext()).getComboIn(id).size());
                bundle.putInt("IDPROD",id);
                bundle.putBoolean("Summary",true);
                fragment.setArguments(bundle);
                fragment.show(supportoFragment,"conferma");

                return true;
            case R.id.menu_summary3:
                final FragmentManager supportFragment = getFragmentManager();
                DialogFragment frag = new AddProdToComboDialog();
                bundle = new Bundle();
                bundle.putInt("IDPROD",id);
                frag.setArguments(bundle);
                frag.show(supportFragment,"conferma");
                //Toast.makeText(this, "Aggiunta", Toast.LENGTH_SHORT).show();

                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }


}
