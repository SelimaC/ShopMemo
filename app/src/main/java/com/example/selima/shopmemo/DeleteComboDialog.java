package com.example.selima.shopmemo;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.example.selima.shopmemo.model.ComboFactory;

/**
 * Created by filippo on 14/01/17.
 */
public class DeleteComboDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final Bundle bundle = this.getArguments();
        int idCombo = -1;
        if (bundle != null) {
            idCombo = bundle.getInt("IDCOMBO", -1);
        }
        final int idCombinazione = idCombo;
        builder.setTitle("Elimina combo").setMessage("Vuoi eliminare questa combinazione?")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ComboFactory.getInstance(getActivity()).deleteCombo(idCombinazione);
                        //Log.d("prod","prodotto"+idCombinazione);

                        boolean isSummary =bundle.getBoolean("Summary");

                        if(isSummary){
                            //Intent i = new Intent(Home_Activity.context(), Home_Activity.class);
                            getActivity().finish();
                        }
                        else
                            throw new Error("Da dove è stato aperto il popup di eliminazione?");


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
