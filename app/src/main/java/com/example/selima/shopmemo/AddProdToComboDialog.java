package com.example.selima.shopmemo;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import com.example.selima.shopmemo.model.Combo;
import com.example.selima.shopmemo.model.ComboFactory;
import com.example.selima.shopmemo.model.Product;
import com.example.selima.shopmemo.model.ProductFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by filippo on 14/01/17.
 */

public class AddProdToComboDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final List<Integer> mSelectedItems = new ArrayList<>();  // Where we track the selected items
        final List<Combo> comboList = ComboFactory.getInstance(getActivity()).getComboByName();
        List<String> nomi = new ArrayList<>();
        final Bundle bundle = this.getArguments();
        int idProd = -1;
        if (bundle != null) {
            idProd = bundle.getInt("IDPROD", -1);
        }
        final int idProdotto = idProd;
        final List<Combo> comboL = new ArrayList<>();
        for (Combo c : comboList){
            if (! c.getListaProdotti().contains(ProductFactory.getInstance(getActivity()).getProductById(idProd))) {
                nomi.add(c.getNome());
                comboL.add(c);
            }
        }
        final CharSequence[] nomiCombo =  nomi.toArray(new CharSequence[0]);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        if(nomi.size()==0 && comboList.size()!=0){
            builder.setTitle("Scegli combinazioni").setMessage("Tutte le combinazioni contengono già questo prodotto").
                    setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
        } else{
        if(nomi.size()==0 && comboList.size()==0){
            builder.setTitle("Scegli combinazioni").setMessage("Crea prima una combinazione").
                    setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
        }else {

            // Set the dialog title
            builder.setTitle("Scegli combinazioni")
                    // Specify the list array, the items to be selected by default (null for none),
                    // and the listener through which to receive callbacks when items are selected
                    .setMultiChoiceItems(nomiCombo, null,
                            new DialogInterface.OnMultiChoiceClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which,
                                                    boolean isChecked) {
                                    if (isChecked) {
                                        // If the user checked the item, add it to the selected items
                                        mSelectedItems.add(which);
                                        Log.d("Aggiunta",""+which+" --- "+ comboL.get(which).getNome());
                                    } else if (mSelectedItems.contains(which)) {
                                        // Else, if the item is already in the array, remove it
                                        mSelectedItems.remove(Integer.valueOf(which));
                                    }
                                }
                            })
                    // Set the action buttons
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            // User clicked OK, so save the mSelectedItems results somewhere
                            // or return them to the component that opened the dialog
                            for (Integer i : mSelectedItems) {
                                Integer idCombo = comboL.get(i).getId();
                                ComboFactory.getInstance(getActivity()).addProductToCombo(idProdotto, idCombo);
                            }
                            if (getActivity() instanceof Home_Activity) {
                                ((ComboFragment) ((Home_Activity) getActivity()).adapter.getItem(1)).setList(((Home_Activity) getActivity()).comboList);
                            }
                            Toast.makeText(getActivity(), "Prodotto aggiunto a " + mSelectedItems.size() + " combinazioni", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("annulla", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            //...
                        }
                    });
            }
        }

        return builder.create();
    }
}
