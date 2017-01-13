package com.example.selima.shopmemo;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.selima.shopmemo.model.Combo;
import com.example.selima.shopmemo.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by selim on 13/01/2017.
 */

public abstract class PageFragmentCombo extends Fragment {
    static private OnListItemClickListener mListItemClickListener;
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
    static List<Combo> list = new ArrayList<>();
    public void setList(List<Combo> l){
        list = l;
        Log.d("listaCombo",""+list);

        if(recyclerView == null) return;
        RecyclerAdapterCombo adapter = new RecyclerAdapterCombo(l);
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
        View view = inflater.inflate(R.layout.combo_fragment2, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.combo_list);

        GridLayoutManager linearLayoutManager = new GridLayoutManager(getActivity().getBaseContext(),2);
        recyclerView.setLayoutManager(linearLayoutManager);
        //recyclerView.setHasFixedSize(true); improve performace but mine can change

        //List<Product> list = new ArrayList<>();

        //fillList(list);

        RecyclerAdapterCombo adapter = new RecyclerAdapterCombo(list);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemTapListener(mListItemClickListener);
        return view;
    }

    abstract void fillList(List<Combo> list);

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListItemClickListener = (OnListItemClickListener) context;
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


    public interface OnListItemClickListener {
        void onComboItemClick(int id);
    }
}