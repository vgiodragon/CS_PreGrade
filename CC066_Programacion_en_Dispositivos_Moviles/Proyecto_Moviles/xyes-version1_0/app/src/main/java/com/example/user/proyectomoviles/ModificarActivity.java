package com.example.user.proyectomoviles;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ModificarActivity extends AppCompatActivity {

    private EditText telFijo;
    private EditText cel;
    private EditText dom;
    private EditText mail;
    private Button btnCancelar;
    private Button btnGuardar;
    String telAux;
    String celAux;
    String domAux;
    String mailAux;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        telFijo = (EditText) findViewById(R.id.textTel1);
        cel = (EditText) findViewById(R.id.textCel1);
        dom = (EditText) findViewById(R.id.textDom1);
        mail = (EditText) findViewById(R.id.textEmail1);
        btnCancelar = (Button) findViewById(R.id.btnCancelar);
        btnGuardar = (Button) findViewById(R.id.btnGuardar);

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelar(v);
            }
        });
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardar(v);
            }
        });

        telFijo.setText(getIntent().getStringExtra("TelFijo1"));
        cel.setText(getIntent().getStringExtra("Cel1"));
        dom.setText(getIntent().getStringExtra("Dom1"));
        mail.setText(getIntent().getStringExtra("Mail1"));

        telAux = telFijo.getText().toString();
        celAux = cel.getText().toString();
        domAux = dom.getText().toString();
        mailAux = mail.getText().toString();

    }

    public void guardar(View view){
        intent = new Intent(getApplicationContext(), EdicionDatos.class);
        intent.putExtra("botonApretado","Guardar");
        intent.putExtra("TelFijo",telFijo.getText().toString());
        intent.putExtra("Cel",cel.getText().toString());
        intent.putExtra("Dom", dom.getText().toString());
        intent.putExtra("Mail",mail.getText().toString());
        startActivity(intent);
        finish();
    }

    public void cancelar(View view){
        intent = new Intent(getApplicationContext(), EdicionDatos.class);
        intent.putExtra("botonApretado","Cancelar");
        intent.putExtra("TelFijo",telAux);
        intent.putExtra("Cel",celAux);
        intent.putExtra("Dom", domAux);
        intent.putExtra("Mail",mailAux);
        startActivity(intent);
        finish();
    }

}
