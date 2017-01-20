package com.example.selima.shopmemo;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.selima.shopmemo.model.Combo;
import com.example.selima.shopmemo.model.ComboFactory;
import com.example.selima.shopmemo.model.Product;
import com.example.selima.shopmemo.model.ProductFactory;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class SummaryCombo_Activity extends AppCompatActivity implements PageFragmentAll.OnListItemClickListener {

    List<Product> productList;
    int id;

    @Override
    protected void onPause(){
        super.onPause();
    }

    @Override
    protected void onResume(){
        productList = ComboFactory.getInstance(this).getComboById(id).getListaProdotti();
        new SummaryComboFragment().setList(productList);
        DecimalFormat df = new DecimalFormat("#.##");
        ((TextView)findViewById(R.id.tot)).setText(df.format(ComboFactory.getInstance(this).getComboById(id).getPrezzoTotale()).toString()+" €");
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary_combo_);
        //findViewById(R.id.fball).setVisibility(View.INVISIBLE);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fball);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editCombo(view);
            }
        });
        fab.setBackgroundTintList(fab.getResources().getColorStateList(R.color.colorAccent));
        fab.setImageDrawable(fab.getResources().getDrawable(R.drawable.edit));
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
        id = Integer.parseInt(i.getStringExtra("combo"));

        Combo combo = ComboFactory.getInstance(this).getComboById(id);
        ab.setTitle(combo.getNome());
        DecimalFormat df = new DecimalFormat("#.##");
        ((TextView)findViewById(R.id.tot)).setText(df.format(combo.getPrezzoTotale()).toString()+" €");
        new SummaryComboFragment().setList(combo.getListaProdotti());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_summarycombo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i = getIntent();
        int id = Integer.parseInt(i.getStringExtra("combo"));
        switch (item.getItemId()) {
            case R.id.options:


                return true;
            case R.id.menu_summary1:

                Intent in = new Intent(getApplicationContext(), EditCombo_Activity.class);
                in.putExtra("combo", String.valueOf(id));
                startActivity(in);

                return  true;
            case R.id.menu_summary2:

                final FragmentManager supportoFragment = getFragmentManager();

                DialogFragment fragment = new DeleteComboDialog();
                Bundle bundle = new Bundle();
                bundle.putInt("IDCOMBO",id);

                bundle.putBoolean("Summary",true);

                fragment.setArguments(bundle);
                fragment.show(supportoFragment,"conferma");


                //Toast.makeText(this, "Elimina", Toast.LENGTH_SHORT).show();

                return true;
            case R.id.menu_summary3:

                Toast.makeText(this, "Galleria", Toast.LENGTH_SHORT).show();

                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void onListItemClick(int id) {
        Intent i = new Intent(getApplicationContext(), SummaryObject_Activity.class);
        i.putExtra("oggetto", String.valueOf(id));
        startActivity(i);
    }

    public void updateList(){
        new AllFragmentCateg().setList(productList);
    }
    public void cardMoreFunctionProd(View view){

    }

    public void editCombo(View view) {
        Intent i = new Intent(getApplicationContext(), EditCombo_Activity.class);
        i.putExtra("combo", String.valueOf(id));
        startActivity(i);
    }
}


class SummaryComboFragment extends Fragment {
    static private PageFragmentAll.OnListItemClickListener mListItemClickListener;
    /*
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment PageFragment.
         */
 /*   public static PageFragment newInstance() {
        return new PageFragment();
    }

    public PageFragmentAll() {
        // Required empty public constructor
    }/**/
    static RecyclerView recyclerView;
    static List<Product> list = new ArrayList<>();
    public void setList(List<Product> l){
        list = l;

        if(recyclerView == null) return;
        RecyclerAdapterAll adapter = new RecyclerAdapterAll(l);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemTapListener(mListItemClickListener);
        recyclerView.invalidate();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.all_fragment1, container, false);
Log.d("Z","Sono qui");

        recyclerView = (RecyclerView) view.findViewById(R.id.al_list);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        recyclerView.setLayoutManager(linearLayoutManager);


        RecyclerAdapterAll adapter = new RecyclerAdapterAll(list);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemTapListener(mListItemClickListener);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListItemClickListener = (PageFragmentAll.OnListItemClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnListItemClickListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListItemClickListener = null;
    }


}