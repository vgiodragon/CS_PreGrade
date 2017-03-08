package com.example.jbot.miaplicaciontoast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnAlerta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAlerta = (Button) findViewById(R.id.btnAlerta);
        btnAlerta.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
             mostrarAlerta(view);
            }
        });
    }
    public void mostrarAlerta(View view){
        Toast toast = Toast.makeText(getApplicationContext(),
         "Has Pulsado el boton!", Toast.LENGTH_LONG);
        toast.show();
    }
}
