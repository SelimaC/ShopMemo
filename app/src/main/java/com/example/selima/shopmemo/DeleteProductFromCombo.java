package com.example.selima.shopmemo;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.example.selima.shopmemo.model.ComboFactory;
import com.example.selima.shopmemo.model.Product;
import com.example.selima.shopmemo.model.ProductFactory;

/**
 * Created by filippo on 11/01/17.
 */

public class DeleteProductFromCombo extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final Bundle bundle = this.getArguments();
        int idC = -1;
        int idProd = -1;
        if (bundle != null) {
            idC = bundle.getInt("IDCOMBO", -1);
            idProd = bundle.getInt("IDPROD", -1);
        }
        final int idProdotto = idProd;
        final int idCombo = idC;
        builder.setTitle("Elimina oggetto").setMessage("L'oggetto selezionato sarà eliminato dalla combinazione" +
                ". Vuoi proseguire?")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ComboFactory.getInstance(getActivity()).getComboById(idCombo)
                                .getListaProdotti().remove(ProductFactory.getInstance(getActivity()).getProductById(idProdotto));
                        Log.d("prod","prodotto"+idProdotto);

                         ((EditCombo_Activity)getActivity()).productList =  ComboFactory.getInstance(getActivity()).getComboById(idCombo)
                                    .getListaProdotti();
                        new EditComboFragment().setList(((EditCombo_Activity)getActivity()).productList);



                    }
                })
                .setNegativeButton("annulla", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

}
