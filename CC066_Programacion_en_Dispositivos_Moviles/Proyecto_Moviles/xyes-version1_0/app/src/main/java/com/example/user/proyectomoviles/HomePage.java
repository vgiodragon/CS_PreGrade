package com.example.user.proyectomoviles;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.MnuOpc1: {
                //Se encuentra en HomePage
                return true;
            }
            case R.id.MnuOpc2:
                go_Notas();
                return true;
            case R.id.MnuOpc3:
                go_Edicion();
                return true;
            case R.id.MnuOpc4:
                logout();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    public void go_Notas(){
        Intent intent= new Intent(HomePage.this, VerNotas.class);
        startActivity(intent);
    }

    public void go_Edicion(){
        Intent intent= new Intent(HomePage.this, EdicionDatos.class);
        startActivity(intent);
    }

    public void logout(){
        Intent intent= new Intent(HomePage.this, InicioApp.class);
        startActivity(intent);
        finish();
    }

}
