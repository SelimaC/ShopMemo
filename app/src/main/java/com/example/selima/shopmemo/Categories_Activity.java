package com.example.selima.shopmemo;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.selima.shopmemo.model.Categoria;
import com.example.selima.shopmemo.model.Product;
import com.example.selima.shopmemo.model.ProductFactory;

import java.util.ArrayList;
import java.util.List;

public class Categories_Activity extends AppCompatActivity implements PageFragmentAll.OnListItemClickListener{
    List<Product> productList;
    Categoria categoria;
    //AllFragment allFragment;
    @Override
    protected void onPause(){
        //findViewById(R.id.fragCateg).setVisibility(View.INVISIBLE);
        //allFragment.setList(productList);
        super.onPause();
    }

    @Override
    protected void onResume(){
        productList = ProductFactory.getInstance(this).getProductsFiltered(categoria);
        new AllFragmentCateg().setList(productList);
        super.onResume();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories_);
        //findViewById(R.id.fragCateg).setVisibility(View.INVISIBLE);
        findViewById(R.id.fball).setVisibility(View.INVISIBLE);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();

        ab.setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        String cat = i.getStringExtra("categoria");
        switch (cat){
            case "cat1":
                ab.setTitle("Alimentari e cura della persona");
                productList = ProductFactory.getInstance(this).getProductsFiltered(Categoria.CAT1);
                categoria = Categoria.CAT1;
                break;
            case "cat2":
                ab.setTitle("Libri, film e musica");
                productList = ProductFactory.getInstance(this).getProductsFiltered(Categoria.CAT2);
                categoria = Categoria.CAT2;
                break;
            case "cat3":
                ab.setTitle("Elettronica, informatica e giochi");
                productList = ProductFactory.getInstance(this).getProductsFiltered(Categoria.CAT3);
                categoria = Categoria.CAT3;
                break;
            case "cat4":
                ab.setTitle("Casa, giardino, fai da te");
                productList = ProductFactory.getInstance(this).getProductsFiltered(Categoria.CAT4);
                categoria = Categoria.CAT4;
                break;
            case "cat5":
                ab.setTitle("Sport, auto e tempo libero");
                productList = ProductFactory.getInstance(this).getProductsFiltered(Categoria.CAT5);
                categoria = Categoria.CAT5;
                break;
            case "cat6":
                ab.setTitle("Vestiario, scarpe e accessori");
                productList = ProductFactory.getInstance(this).getProductsFiltered(Categoria.CAT6);
                categoria = Categoria.CAT6;
                break;
            default:
                throw new RuntimeException("Categoria non valida: "+cat);
        }

        //allFragment = new AllFragment();
        // (new AllFragment()).setList(productList);
        new AllFragmentCateg().setList(productList);
        //findViewById(R.id.fragCateg).setVisibility(View.VISIBLE);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
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
        showPopup(view);
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
                        bundle.putBoolean("Summary",false);

                        fragment.setArguments(bundle);
                        fragment.show(supportoFragment,"conferma");
                        updateList();
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

class AllFragmentCateg extends Fragment {
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
        Log.d("listaProdottiCateg",""+list);

        if(recyclerView == null) return;
        RecyclerAdapterAll adapter = new RecyclerAdapterAll(l);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemTapListener(mListItemClickListener);
        recyclerView.invalidate();

    }
    //final RecyclerAdapterAll adapter = new RecyclerAdapterAll(list);
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