package com.example.jbot.misharedpreferences;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MisPreferencias extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // esto es si esque iniciaramos el layout
        //setContentView(R.layout.activity_mis_preferencias);

        // version menor a API 11 de android, aparece deprecated, pero aun se puede usar
        addPreferencesFromResource(R.xml.preferences);

    }

}
