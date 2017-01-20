package com.example.selima.shopmemo;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.selima.shopmemo.model.Categoria;

/**
 * Created by filippo on 20/01/17.
 */
public class InsertCategoryDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){

        if(getActivity() instanceof NewObject_Activity){
            ((NewObject_Activity) getActivity()).settingsDialog = this;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View view = inflater.inflate(R.layout.popup_categories, null);
        builder.setView(view)
                .setTitle("Scegli la categoria");
        return builder.create();
    }
}
