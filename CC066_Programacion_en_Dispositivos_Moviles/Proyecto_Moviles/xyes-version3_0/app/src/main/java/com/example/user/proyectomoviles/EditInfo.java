package com.example.user.proyectomoviles;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.user.proyectomoviles.Fragments.InfoFragment;

public class EditInfo extends AppCompatActivity {

    private EditText cel;
    private EditText mail;
    private TextView nombres,apellidoP,apellidoM,codigo,ciclo,facultad,especialidad;
    String celAux;
    String mailAux;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_datos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nombres = (TextView) findViewById(R.id.textNombre1);
        apellidoP = (TextView) findViewById(R.id.textApellidoP1);
        apellidoM = (TextView) findViewById(R.id.textApellidoM1);
        codigo = (TextView) findViewById(R.id.textCodigo1);
        ciclo = (TextView) findViewById(R.id.textCiclo1);
        facultad = (TextView) findViewById(R.id.textFacultad1);
        especialidad = (TextView) findViewById(R.id.textEsp1);
        cel = (EditText) findViewById(R.id.textCel1);
        mail = (EditText) findViewById(R.id.textEmail1);

        nombres.setText(getIntent().getStringExtra("Nombre1"));
        apellidoP.setText(getIntent().getStringExtra("ApellidoP1"));
        apellidoM.setText(getIntent().getStringExtra("ApellidoM1"));
        codigo.setText(getIntent().getStringExtra("Codigo1"));
        ciclo.setText(getIntent().getStringExtra("Ciclo1"));
        facultad.setText(getIntent().getStringExtra("Facultad1"));
        especialidad.setText(getIntent().getStringExtra("Especialidad1"));
        cel.setText(getIntent().getStringExtra("Cel1"));
        mail.setText(getIntent().getStringExtra("Mail1"));

        celAux = cel.getText().toString();
        mailAux = mail.getText().toString();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_modify, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.MnuSave:
                guardar();
                return true;
            case R.id.MnuCancel:
                cancelar();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    public void guardar(){
        intent = new Intent(getApplicationContext(), Sesion.class);
        intent.putExtra("botonApretado","Guardar");
        intent.putExtra("Cel",cel.getText().toString());
        intent.putExtra("Mail",mail.getText().toString());
        startActivity(intent);
        finish();
    }

    public void cancelar(){
        intent = new Intent(getApplicationContext(), Sesion.class);
        intent.putExtra("botonApretado","Cancelar");
        intent.putExtra("Cel",celAux);
        intent.putExtra("Mail",mailAux);
        startActivity(intent);
        finish();
    }

}
