package com.example.selima.shopmemo;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.example.selima.shopmemo.model.ProductFactory;

/**
 * Created by filippo on 11/01/17.
 */

public class DeleteProductDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final Bundle bundle = this.getArguments();
        int i = 0;
        int idProd = -1;
        if (bundle != null) {
            i = bundle.getInt("NUMCOMBO", 0);
            idProd = bundle.getInt("IDPROD", -1);
        }
        final int idProdotto = idProd;
        builder.setTitle("Elimina oggetto").setMessage("L'oggetto selezionato è presente in "+
                i + " combinazioni. Vuoi proseguire?")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ProductFactory.getInstance(getActivity()).deleteProduct(idProdotto);
                        Log.d("prod","prodotto"+idProdotto);

                        boolean isSummary =bundle.getBoolean("Summary");

                        if(isSummary){
                            Intent i = new Intent(Home_Activity.context(), Home_Activity.class);
                            getActivity().finish();
                           // startActivity(i);
                        }
                        else ((AllFragment)((Home_Activity)getActivity()).adapter.getItem(0)).setList(((Home_Activity) getActivity()).productList);


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
