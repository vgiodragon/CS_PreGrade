package com.example.user.proyectomoviles.helpClass;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.DialogFragment;
import android.util.Log;

import java.util.ArrayList;

public class DialogSelection extends DialogFragment {
    String[] itemS;
    String[][] itemS2;
    String tittle;
    String tipo="predeter";

    public DialogSelection newInstance(){
        DialogSelection diag = new DialogSelection();
        return diag;
    }

    public DialogSelection(){
    }

    public DialogSelection(String tit){
        this.tittle=tit;
    }

    public DialogSelection(ArrayList<String> a, String tit){
        //items = new ArrayList<String>( Arrays.asList("Buenos Aires", "Córdoba", "La Plata"));
        itemS = new String[a.size()];
        for (int i=0;i<a.size();i++) {
            itemS[i]=a.get(i);
        }
        this.tittle = tit;
    }
    public DialogSelection(ArrayList<ArrayList<String>> a, String tit, String tip){
        //items = new ArrayList<String>( Arrays.asList("Buenos Aires", "Córdoba", "La Plata"));
        itemS = new String[a.size()];
        for (int j = 0;j<a.size();j++) {
            for (int i = 0; i < a.get(j).size(); i++) {
                itemS2[j][i] = a.get(j).get(i);
            }
        }
        this.tittle = tit;
        this.tipo = tip;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder build = new AlertDialog.Builder(getActivity());
        build.setTitle(tittle);

        build.setItems(itemS, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int item) {
                Log.i("Dialogos", "Opción elegida: " + itemS[item]);
                }
            }).setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                }
            });

        return build.create();
    }
}
