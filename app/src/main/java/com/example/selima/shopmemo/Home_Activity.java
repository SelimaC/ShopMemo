package com.example.selima.shopmemo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.selima.shopmemo.model.Combo;
import com.example.selima.shopmemo.model.ComboFactory;
import com.example.selima.shopmemo.model.Product;
import com.example.selima.shopmemo.model.ProductFactory;

import java.util.List;


public class Home_Activity extends AppCompatActivity implements View.OnClickListener,PageFragmentAll.OnListItemClickListener {

    private static Home_Activity mApp = null;
    LinearLayout mRevealView;
    boolean hidden=true;
    LinearLayout ib_cat1,ib_cat2,ib_cat3;
    LinearLayout ib_cat4,ib_cat5,ib_cat6;
    List<Product> productList;
    private List<Combo> comboList;
    PagerAdapter adapter;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApp=this;
        setContentView(R.layout.activity_home_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();

        mRevealView = (LinearLayout) findViewById(R.id.reveal_items);
        mRevealView.setVisibility(View.INVISIBLE);

        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("ALL"));
        tabLayout.addTab(tabLayout.newTab().setText("COMBO"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        ib_cat1=(LinearLayout)findViewById(R.id.cat1);
        ib_cat2=(LinearLayout)findViewById(R.id.cat2);
        ib_cat3=(LinearLayout)findViewById(R.id.cat3);
        ib_cat4=(LinearLayout)findViewById(R.id.cat4);
        ib_cat5=(LinearLayout)findViewById(R.id.cat5);
        ib_cat6=(LinearLayout)findViewById(R.id.cat6);

        ib_cat1.setOnClickListener(this);
        ib_cat2.setOnClickListener(this);
        ib_cat3.setOnClickListener(this);
        ib_cat4.setOnClickListener(this);
        ib_cat5.setOnClickListener(this);
        ib_cat6.setOnClickListener(this);


        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        adapter = new PagerAdapter
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

                    mRevealView.setVisibility(View.INVISIBLE);
                    hidden=true;
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


        productList = ProductFactory.getInstance(this).getAllProducts();
        ((AllFragment)(adapter.getItem(0))).setList(productList);
        comboList = ComboFactory.getInstance(this).getAllCombo();

    }


    // imagebutton click events
    @Override
    public void onClick(View v) {
        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.offsetTopAndBottom(-((LinearLayout)(findViewById(R.id.reveal_items))).getHeight());
        Log.d("onClick","offset of -"+((LinearLayout)(findViewById(R.id.reveal_items))).getHeight());
        switch (v.getId()) {
            case R.id.cat1:
                Toast.makeText(Home_Activity.this,
                        "Categoria1", Toast.LENGTH_SHORT).show();
                mRevealView.setVisibility(View.INVISIBLE);
                mRevealView.setVisibility(View.INVISIBLE);
                hidden=true;
                break;
            case R.id.cat2:
                Toast.makeText(Home_Activity.this,
                        "Categoria2", Toast.LENGTH_SHORT).show();
                mRevealView.setVisibility(View.INVISIBLE);
                mRevealView.setVisibility(View.INVISIBLE);
                hidden=true;
                break;
            case R.id.cat3:
                Toast.makeText(Home_Activity.this,
                        "Categoria3", Toast.LENGTH_SHORT).show();
                mRevealView.setVisibility(View.INVISIBLE);
                mRevealView.setVisibility(View.INVISIBLE);
                hidden=true;
                break;
            case R.id.cat4:
                Toast.makeText(Home_Activity.this,
                        "Categoria4", Toast.LENGTH_SHORT).show();
                mRevealView.setVisibility(View.INVISIBLE);
                mRevealView.setVisibility(View.INVISIBLE);
                hidden=true;
                break;
            case R.id.cat5:
                Toast.makeText(Home_Activity.this,
                        "Categoria5", Toast.LENGTH_SHORT).show();
                mRevealView.setVisibility(View.INVISIBLE);
                mRevealView.setVisibility(View.INVISIBLE);
                hidden=true;
                break;
            case R.id.cat6:
                Toast.makeText(Home_Activity.this,
                        "Categoria6", Toast.LENGTH_SHORT).show();
                mRevealView.setVisibility(View.INVISIBLE);
                mRevealView.setVisibility(View.INVISIBLE);
                hidden=true;
                break;
        }
    }

    private Menu menu;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu=menu;
        getMenuInflater().inflate(R.menu.menu_actionall, menu);
        hideOption(R.id.sortCombo);
        return true;
    }
//


    //Variabili per l'effetto del menu categorie
    Animator animator, animate;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.filterAll:

                final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
                viewPager.offsetTopAndBottom(+((LinearLayout)(findViewById(R.id.reveal_items))).getHeight());
                Log.d("opening","offset of +"+((LinearLayout)(findViewById(R.id.reveal_items))).getHeight());


                // finding X and Y co-ordinates
                int cx = (mRevealView.getLeft() + mRevealView.getRight());
                int cy = (mRevealView.getTop());

                // to find  radius when icon is tapped for showing layout
                int startradius=0;
                int endradius = Math.max(mRevealView.getWidth(), mRevealView.getHeight());

                // performing circular reveal when icon will be tapped
                animator = ViewAnimationUtils.createCircularReveal(mRevealView,
                        cx, cy, startradius, endradius);
                animator.setInterpolator(new AccelerateDecelerateInterpolator());
                animator.setDuration(400);

                //reverse animation
                // to find radius when icon is tapped again for hiding layout
                //  starting radius will be the radius or the extent to which circular reveal animation is to be shown

                int reverse_startradius = Math.max(mRevealView.getWidth(),mRevealView.getHeight());

                //endradius will be zero
                int reverse_endradius=0;

                // performing circular reveal for reverse animation
                animate = ViewAnimationUtils.createCircularReveal(mRevealView,cx,cy,reverse_startradius,reverse_endradius);
                if(hidden){
                //    final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
               //     viewPager.setVisibility(View.INVISIBLE);

                    // to show the layout when icon is tapped
                    mRevealView.setVisibility(View.VISIBLE);
                    animator.start();
                    hidden = false;

                }

                return true;

            case R.id.sortAll:
                // Apre menu a tendina
                return true;

            case R.id.sortCombo:
                // Apre menu a tendina
                return true;
            case R.id.menu_all0:
                //Ordina di recente
                ((AllFragment)(adapter.getItem(0))).setList(ProductFactory.getInstance(this).getProductsByTime());
                return true;
            case R.id.menu_all1:
                //Ordina per nome
                ((AllFragment)(adapter.getItem(0))).setList(ProductFactory.getInstance(this).getProductsByName());
                return true;
            case R.id.menu_all2:
                //Ordina per prezzo
                ((AllFragment)(adapter.getItem(0))).setList(ProductFactory.getInstance(this).getProductsByPrice());
                return true;
            case R.id.menu_all3:
                //Ordina per preferenza
                ((AllFragment)(adapter.getItem(0))).setList(ProductFactory.getInstance(this).getProductsByPreference());
                return true;
            case R.id.menu_all4:
                //Ordina per categoria
                ((AllFragment)(adapter.getItem(0))).setList(ProductFactory.getInstance(this).getProductsByCategory());
                return true;
            case R.id.menu_combo0:
                //Ordina di recente
                ComboFactory.getInstance(this).getComboByTime();
                return true;
            case R.id.menu_combo1:
                //Ordina per nome
                ComboFactory.getInstance(this).getComboByName();
                return true;
            case R.id.menu_combo2:
                //Ordina per prezzo
                ComboFactory.getInstance(this).getComboByPrice();
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    // Controlla se il touch è dentro la finestra delle categorie
    boolean isWithinEditTextBounds(int xPoint, int yPoint) {
        int[] l = new int[2];
        mRevealView.getLocationOnScreen(l);
        int x = l[0];
        int y = l[1];
        int w = mRevealView.getWidth();
        int h = mRevealView.getHeight();

        if (xPoint< x || xPoint> x + w || yPoint< y || yPoint> y + h) {
            return false;
        }
        return true;
    }
    //Fa sparire le categorie se si clicca al di fuori
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if(!isWithinEditTextBounds((int)ev.getRawX(),(int)ev.getRawY()) && hidden==false){
            final int offset = ((LinearLayout) (findViewById(R.id.reveal_items))).getHeight();
            final boolean flag = ev.getActionMasked() == MotionEvent.ACTION_DOWN;
            int cx = (mRevealView.getLeft() + mRevealView.getRight());
            int cy = (mRevealView.getTop());
            int reverse_startradius = Math.max(mRevealView.getWidth(),mRevealView.getHeight());

            //endradius will be zero
            int reverse_endradius=0;

            // performing circular reveal for reverse animation
            animate = ViewAnimationUtils.createCircularReveal(mRevealView,cx,cy,reverse_startradius,reverse_endradius);

                mRevealView.setVisibility(View.VISIBLE);


                // to hide layout on animation end
                animate.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        mRevealView.setVisibility(View.INVISIBLE);
                        hidden = true;
                        if(flag) {
                            final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
                            Log.d("dispatch", "offset of -" + offset);
                            viewPager.offsetTopAndBottom(-offset);
                        }
                    }
                });
                animate.start();



            return true;

        }

        return super.dispatchTouchEvent(ev);
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


    @Override
    public void onListItemClick(String title) {
        Toast.makeText(this, title, Toast.LENGTH_SHORT).show();
    }
    public void cardMoreFunctionProd(View view){
       showPopup(view);
    }

    public static Context context()
    {
        return mApp.getApplicationContext();
    }

    public void showPopup(View v) {
        final View view = v;
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menuthreedotsproduct, popup.getMenu());
        final List<Product> lista = productList;
        final FragmentManager supportoFragment = getFragmentManager();
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.eliminaProd:
                        ViewGroup viewGroup = (ViewGroup) view.getParent();
                        TextView child = (TextView) viewGroup.getChildAt(6);
                        String id = child.getText().toString();
                        //Toast.makeText(view.getContext(), "Elimina oggetto "+id, Toast.LENGTH_SHORT).show();
                        //ProductFactory.getInstance(getApplicationContext()).deleteProduct(Integer.parseInt(id));
                        DialogFragment fragment = new DeleteProductDialog();
                        Bundle bundle = new Bundle();
                        bundle.putInt("NUMCOMBO",ProductFactory.getInstance(getApplicationContext()).getComboIn(Integer.parseInt(id)).size());
                        bundle.putInt("IDPROD",(Integer.parseInt(id)));

                        fragment.setArguments(bundle);
                        fragment.show(supportoFragment,"conferma");
                        //((AllFragment)(adapter.getItem(0))).setList(lista);
                        break;
                    case R.id.modificaProd:
                        Toast.makeText(view.getContext(), "La modifica non è implementata", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.addA:
                        Toast.makeText(view.getContext(), "Aggiunta", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });
        popup.show();
    }

}