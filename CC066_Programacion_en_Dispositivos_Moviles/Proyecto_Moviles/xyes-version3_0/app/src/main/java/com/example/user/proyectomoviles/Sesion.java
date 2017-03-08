package com.example.user.proyectomoviles;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;

import com.example.user.proyectomoviles.Fragments.MiFragmentPagerAdapter;

public class Sesion extends AppCompatActivity {

    private String ID_Alumno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sesion);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ID_Alumno = getIntent().getStringExtra("IdAlumno");

        // appbartabs se encuentra en content_sesion
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new MiFragmentPagerAdapter(getSupportFragmentManager(),ID_Alumno));

        // CON ESTO CREO LOS BOTONES DE TABS
        // appbartabs se encuentra en content_sesion
        TabLayout tabLayout = (TabLayout) findViewById(R.id.appbartabs);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        // muestra los tabs
        tabLayout.setupWithViewPager(viewPager);
        // mostrando los iconos del fragment
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_menu_home);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_menu_info_details);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_menu_moreoverflow);

        // Muestra el titulo
        getSupportActionBar().setDisplayShowTitleEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sesion, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            //noinspection SimplifiableIfStatement
            //case R.id.MnuEditarInfo:
            //    return true;
            case R.id.MnuLogout:
                return_back();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void return_back(){
        Intent intent= new Intent(Sesion.this, StartApp.class);
        startActivity(intent);
        finish();
    }
}
