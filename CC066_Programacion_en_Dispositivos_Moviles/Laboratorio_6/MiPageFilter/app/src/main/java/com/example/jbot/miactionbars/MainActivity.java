package com.example.jbot.miactionbars;


import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements enviarMensaje {

    Toast t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        //Appbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new MiFragmentPagerAdapter(getSupportFragmentManager()));

        TabLayout tabLayout = (TabLayout) findViewById(R.id.appbartabs);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setupWithViewPager(viewPager);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //Appbar page filter
        Spinner cmbToolbar = (Spinner) findViewById(R.id.CmbToolbar);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getSupportActionBar().getThemedContext(),
                R.layout.appbar_filter_title,
                new String[]{"Opción 1 ", "Opción 2 ", "Opción 3 "});
        adapter.setDropDownViewResource(R.layout.appbar_filter_list);
        cmbToolbar.setAdapter(adapter);
        cmbToolbar.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view,
                                               int i, long l) {
                        //... Acciones al seleccionar una opción de la lista
                        Log.i("Toolbar 3", "Seleccionada opción " + i);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        //... Acciones al no existir ningún elemento seleccionado
                    }
                });

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
