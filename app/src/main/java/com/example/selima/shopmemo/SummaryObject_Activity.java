package com.example.selima.shopmemo;

import android.animation.Animator;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.selima.shopmemo.model.ComboFactory;
import com.example.selima.shopmemo.model.ProductFactory;

import static java.security.AccessController.getContext;

public class SummaryObject_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary_object_);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();

        ab.setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        String obj = i.getStringExtra("oggetto");

        ab.setTitle(obj);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_summaryobject, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.options:


                return true;
            case R.id.menu_summary1:

                Toast.makeText(this, "La modifica non è implementata", Toast.LENGTH_SHORT).show();

                return  true;
            case R.id.menu_summary2:

                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
