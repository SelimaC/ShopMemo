package com.example.selima.shopmemo;

/**
 * Created by selima on 29/12/2016.
 */


import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.selima.shopmemo.model.Product;
import com.example.selima.shopmemo.model.ProductFactory;

import java.util.ArrayList;
import java.util.List;

public class AllFragment extends PageFragmentAll {

    /*@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.all_fragment1, container, false);
    }*/

    public static PageFragmentAll newInstance() {
        return new AllFragment();
    }

    @Override
    void fillList(List<Product> list) {
        list.addAll(ProductFactory.getInstance(getContext()).getAllProducts());
    }
}