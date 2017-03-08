package com.example.jbot.migridview;

import android.content.pm.LabeledIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String[] datos = new String[] { "arroz con pollo","ají de gallina","lomo saltado",
            "tacu tacu", "ceviche","cau cau" };
    private GridView grdOpciones;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datos);
        grdOpciones = (GridView)findViewById(R.id.GridOpciones);

        grdOpciones.setAdapter(adaptador);
        grdOpciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent,android.view.View v, int position, long id) {
                        Toast.makeText(getApplicationContext(),"Opción seleccionada: "+ parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
