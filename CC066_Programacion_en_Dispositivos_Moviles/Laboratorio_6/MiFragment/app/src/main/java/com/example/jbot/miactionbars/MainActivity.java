package com.example.jbot.miactionbars;


import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements enviarMensaje {

    Toast t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle aiFramction bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                t = Toast.makeText(MainActivity.this,"Apreto en Settings",Toast.LENGTH_SHORT);
                t.show();
                return true;

            case R.id.action_volver:
                t = Toast.makeText(MainActivity.this,"Apreto en Back",Toast.LENGTH_SHORT);
                t.show();
                return true;

            case R.id.action_modificar:
                t = Toast.makeText(MainActivity.this,"Apreto en Modify",Toast.LENGTH_SHORT);
                t.show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void enviarDatos(String mensaje) {
        Derecha derecha = (Derecha) getSupportFragmentManager().findFragmentById(R.id.derecho);
        derecha.obtenerDatos(mensaje);
    }
}
