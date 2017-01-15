package com.example.selima.shopmemo;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

/**
 * Created by filippo on 15/01/17.
 */
public class EnterComboNameDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        final View view = inflater.inflate(R.layout.enter_combo_name, null);
        builder.setView(view)
        // Add action buttons
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int id) {
                       Intent i = new Intent(getActivity().getApplicationContext(), NewCombo_Activity.class);
                       EditText editText = (EditText) view.findViewById(R.id.nomeCombo);
                       String nomeCombo = editText.getText().toString();
                       i.putExtra("NOME", nomeCombo);

                       startActivity(i);
                   }
                })
                .setNegativeButton("annulla", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                   }
                })
                .setTitle("Inserisci il nome");
        return builder.create();
    }
}
