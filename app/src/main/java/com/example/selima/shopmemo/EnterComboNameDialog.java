package com.example.selima.shopmemo;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by filippo on 15/01/17.
 */
public class EnterComboNameDialog extends DialogFragment {
    View view;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        view = inflater.inflate(R.layout.enter_combo_name, null);
        final View view = this.view;
        builder.setView(view)
        // Add action buttons
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int id) {
                       /*EditText editText = (EditText) view.findViewById(R.id.nomeCombo);
                       String nomeCombo = editText.getText().toString();
                       if (nomeCombo == null || nomeCombo.equals("")){
                           editText.setError("Inserire il nome");
                       }else {
                           Intent i = new Intent(getActivity().getApplicationContext(), NewCombo_Activity.class);
                           i.putExtra("NOME", nomeCombo);
                           startActivity(i);
                       }*/
                   }
                })
                .setNegativeButton("annulla", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                   }
                })
                .setTitle("Inserisci il nome");
        return builder.create();
    }

    @Override
    public void onStart(){
        super.onStart();    //super.onStart() is where dialog.show() is actually called on the underlying dialog, so we have to do it after this point
        final AlertDialog d = (AlertDialog)getDialog();
        if(d != null)        {
            Button positiveButton = (Button) d.getButton(Dialog.BUTTON_POSITIVE);
            positiveButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Boolean wantToCloseDialog = false;
                    //Do stuff, possibly set wantToCloseDialog to true then...
                    EditText editText = (EditText) view.findViewById(R.id.nomeCombo);
                    String nomeCombo = editText.getText().toString();
                    if (nomeCombo == null || nomeCombo.equals("")){
                        editText.setError("Inserire il nome");
                    }else {
                        wantToCloseDialog = true;
                    }
                    if(wantToCloseDialog){
                        d.dismiss();
                        Intent i = new Intent(getActivity().getApplicationContext(), NewCombo_Activity.class);
                        i.putExtra("NOME", nomeCombo);
                        i.putExtra("CREAZIONE", true);
                        i.putExtra("ID", -1);
                        startActivity(i);
                    }
                }
            });
        }
    }
}
