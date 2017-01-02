package com.example.selima.shopmemo;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.TabHost;
import android.widget.Toast;


public class Home_Activity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();



        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("ALL"));
        tabLayout.addTab(tabLayout.newTab().setText("COMBO"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Window window = getWindow();
                if(tab.getPosition()==0){
                    tabLayout.setBackgroundColor(Color.parseColor("#00ACC1"));
                    tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#F57C00"));
                    ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00ACC1")));
                    window.setStatusBarColor(Color.parseColor("#0097A7"));

                    hideOption(R.id.sortCombo);
                    showOption(R.id.sortAll);
                    showOption(R.id.filterAll);
                }
                else{
                    tabLayout.setBackgroundColor(Color.parseColor("#F57C00"));
                    tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#00ACC1"));
                    ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#F57C00")));
                    window.setStatusBarColor(Color.parseColor("#EF6C00"));

                    showOption(R.id.sortCombo);
                    hideOption(R.id.sortAll);
                    hideOption(R.id.filterAll);
                }
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private Menu menu;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu=menu;
        getMenuInflater().inflate(R.menu.menu_actionall, menu);
        hideOption(R.id.sortCombo);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.filterAll:
                // User chose the "Settings" item, show the app settings UI...
                Toast.makeText(this, "filtra gli oggetti",
                        Toast.LENGTH_SHORT).show();
                return true;

            case R.id.sortAll:
                // Apre menu a tendina

                return true;

            case R.id.sortCombo:
                // Apre menu a tendina
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }


    private void hideOption(int id)
    {
        MenuItem item = menu.findItem(id);
        item.setVisible(false);
    }

    private void showOption(int id)
    {
        MenuItem item = menu.findItem(id);
        item.setVisible(true);
    }

}