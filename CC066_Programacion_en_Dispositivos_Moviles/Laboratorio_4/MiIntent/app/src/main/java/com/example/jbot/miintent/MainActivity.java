package com.example.jbot.miintent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnSalta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // descomentar para ahcer la actividad 3 con la clase Tercera.class
        //Intent i = new Intent(getApplicationContext(), Tercera.class);
        //i.putExtra("valorTest","Meow");
        //startActivity(i);

        btnSalta = (Button) findViewById(R.id.btnSalta);
        btnSalta.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                salta(v);
            }
        });
    }
    public void salta(View view){
        Log.d("App","se ha hecho click en salta del Activiity 1");
        Intent intent = new Intent(getApplicationContext(), Segunda.class);
        startActivity(intent);
    }
}
