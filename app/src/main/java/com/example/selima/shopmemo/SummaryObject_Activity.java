package com.example.selima.shopmemo;

import android.animation.Animator;
import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.icu.util.RangeValueIterator;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.selima.shopmemo.model.Combo;
import com.example.selima.shopmemo.model.ComboFactory;
import com.example.selima.shopmemo.model.Product;
import com.example.selima.shopmemo.model.ProductFactory;

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

public class SummaryObject_Activity extends AppCompatActivity {


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary_object_);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();

        ab.setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        String o = i.getStringExtra("oggetto");
        int id = Integer.parseInt(i.getStringExtra("oggetto"));

        Product obj = ProductFactory.getInstance(this).getProductById(id);
        ab.setTitle(obj.getNome());

        final ImageView photo = (ImageView) findViewById(R.id.photoobj);
        final int idphoto = Home_Activity.context().getResources().getIdentifier(obj.getPathFoto(),
                "drawable", Home_Activity.context().getPackageName());
        photo.setImageResource(idphoto);


        TextView shop = (TextView) findViewById(R.id.shop);
        TextView price = (TextView) findViewById(R.id.price);
        TextView category = (TextView) findViewById(R.id.category);
        TextView combo = (TextView) findViewById(R.id.combo);

        shop.setText(obj.getNegozio());
        price.setText( obj.getPrezzo() + " €");
        category.setText(obj.getCategoria()+ "");


        List<Combo> c = new ArrayList<>();
        c = ProductFactory.getInstance(this).getComboIn(id);

        LinearLayout l = (LinearLayout) findViewById(R.id.summary);
        if(c.size()==0) {
            TextView t = new TextView(this);
            t.setText("Nessuna");
            t.setTextSize(17);
            t.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            l.addView(t);
        }
        else {
            for (Combo item : c) {
                TextView t = new TextView(this);
                t.setText(item.getNome());
                t.setTextSize(17);
                t.setId(item.getId());
                t.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                l.addView(t);
                t.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Toast.makeText(v.getContext(), "Riepilogo combo", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        }
       /* final ArrayList <String> listp = new ArrayList<String>();
        if(c.size()==0) {listp.add("Nessuna");}
        else
          for (Combo item : c) {
              listp.add(item.getNome());
          }

        final ArrayAdapter <String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listp);
        list.setAdapter(adapter);*/
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
                Toast.makeText(this, "Aggiunta", Toast.LENGTH_SHORT).show();
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

}
