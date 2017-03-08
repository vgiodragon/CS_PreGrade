package com.example.user.proyectomoviles;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class EdicionDatos extends AppCompatActivity {
    private TextView telFijo;
    private TextView cel;
    private TextView dom;
    private TextView mail;
    private Button btnModificar;
    String cadenaBoton="Guardar";
    String cadenaBoton1="Cancelar";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edicion_datos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        telFijo = (TextView) findViewById(R.id.textTel1);
        cel = (TextView) findViewById(R.id.textCel1);
        dom = (TextView) findViewById(R.id.textDom1);
        mail = (TextView) findViewById(R.id.textEmail1);
        btnModificar = (Button) findViewById(R.id.btnModificar);

        modificarValores();

        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modificar(v);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.MnuOpc1:
                return_home();
                return true;
            case R.id.MnuOpc2:
                go_Notas();
                return true;
            case R.id.MnuOpc3:
                //Se encuentra en EdicionDatos
                return true;
            case R.id.MnuOpc4:
                logout();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    public void return_home(){
        Intent intent= new Intent(EdicionDatos.this, HomePage.class);
        startActivity(intent);
    }

    public void go_Notas(){
        Intent intent= new Intent(EdicionDatos.this, VerNotas.class);
        startActivity(intent);
    }

    public void logout(){
        Intent intent= new Intent(EdicionDatos.this, InicioApp.class);
        startActivity(intent);
        finish();
    }

    public void modificarValores(){
        String cadenaB = getIntent().getStringExtra("botonApretado");
        if (cadenaBoton.equals(cadenaB)){
            Toast t = Toast.makeText(EdicionDatos.this,"Se guardo correctamente",Toast.LENGTH_SHORT);
            t.show();
            telFijo.setText(getIntent().getStringExtra("TelFijo"));
            cel.setText(getIntent().getStringExtra("Cel"));
            dom.setText(getIntent().getStringExtra("Dom"));
            mail.setText(getIntent().getStringExtra("Mail"));
        }
        if(cadenaBoton1.equals(cadenaB)){
            Toast t = Toast.makeText(EdicionDatos.this,"No se modifico ningun dato",Toast.LENGTH_SHORT);
            t.show();
            telFijo.setText(getIntent().getStringExtra("TelFijo"));
            cel.setText(getIntent().getStringExtra("Cel"));
            dom.setText(getIntent().getStringExtra("Dom"));
            mail.setText(getIntent().getStringExtra("Mail"));
        }
    }

    public void modificar(View view){
        Intent intent = new Intent(getApplicationContext(), ModificarActivity.class);
        intent.putExtra("TelFijo1",telFijo.getText().toString());
        intent.putExtra("Cel1",cel.getText().toString());
        intent.putExtra("Dom1", dom.getText().toString());
        intent.putExtra("Mail1", mail.getText().toString());
        startActivity(intent);
        finish();
    }

}
