package com.example.selima.shopmemo;

/**
 * Created by selima on 29/12/2016.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.selima.shopmemo.model.Combo;
import com.example.selima.shopmemo.model.ComboFactory;
import com.example.selima.shopmemo.model.Product;
import com.example.selima.shopmemo.model.ProductFactory;

import java.util.List;

public class ComboFragment extends PageFragmentCombo {

    /*@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.all_fragment1, container, false);
    }*/

    public static PageFragmentCombo newInstance() {
        return new ComboFragment();
    }

    @Override
    void fillList(List<Combo> list) {
        list.addAll(ComboFactory.getInstance(getContext()).getAllCombo());
    }
}