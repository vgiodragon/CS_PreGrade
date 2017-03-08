package com.example.jbot.miintent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Tercera extends AppCompatActivity {

    private Button btnMuestra;
    private ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tercera);

        // Descomentar para hacer la actividad 3.
        //String varString = getIntent().getStringExtra("valorTest");
        //Log.d("HelloWorld,Second Acti", varString);
        img= (ImageView) findViewById(R.id.image);
        btnMuestra = (Button) findViewById(R.id.btnMuestra);
        btnMuestra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                muestra(v);
            }
        });
    }
    public void muestra(View view){
        img.setImageResource(R.drawable.dragon_inferno);
    }
}
