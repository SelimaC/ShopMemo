package com.example.selima.shopmemo;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.selima.shopmemo.model.Product;

import java.util.ArrayList;
import java.util.List;


public abstract class PageFragmentAll extends Fragment {
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
    static List<Product> list = new ArrayList<>();
    public void setList(List<Product> l){
        list = l;
        Log.d("listaProdotti",""+list);

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

    abstract void fillList(List<Product> list);

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
        void onListItemClick(int id);
    }
}