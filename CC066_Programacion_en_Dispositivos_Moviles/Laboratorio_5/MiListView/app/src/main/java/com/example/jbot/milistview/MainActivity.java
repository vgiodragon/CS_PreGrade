package com.example.jbot.milistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String[] COMIDAS = new String[] { "arroz con pollo","ají de gallina","lomo saltado",
            "tacu tacu", "ceviche","cau cau" };
    public static final String[] ingrediente = new String[] { "pollo, arroz","ají, galletas","papa, carne",
            "mezclar comidas", "pescado, cebolla","res, papa" };
    public static final String[] tiempoCoc = new String[] { "50 min","20 min","30 min",
            "45 min", "5 min","35 min" };
    private ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview = (ListView) findViewById(R.id.listview);

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, COMIDAS);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Ingredientes: " + ingrediente[position] + "\ntiempo de cocina: " + tiempoCoc[position], Toast.LENGTH_SHORT).show();
            }
        });
    }
}
