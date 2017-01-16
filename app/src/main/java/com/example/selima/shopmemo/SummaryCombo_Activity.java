package com.example.selima.shopmemo;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
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
import android.widget.Toast;

import com.example.selima.shopmemo.model.Combo;
import com.example.selima.shopmemo.model.ComboFactory;
import com.example.selima.shopmemo.model.Product;
import com.example.selima.shopmemo.model.ProductFactory;

import java.util.ArrayList;
import java.util.List;

public class SummaryCombo_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary_combo_);

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
        int id = Integer.parseInt(i.getStringExtra("combo"));

        Combo combo = ComboFactory.getInstance(this).getComboById(id);
        ab.setTitle(combo.getNome());
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

                Toast.makeText(this, "La modifica non è implementata", Toast.LENGTH_SHORT).show();

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
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
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

        recyclerView = (RecyclerView) view.findViewById(R.id.al_list);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        //recyclerView.setHasFixedSize(true); improve performace but mine can change

        //List<Product> list = new ArrayList<>();

        //fillList(list);

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