package com.example.user.proyectomoviles;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;

import com.example.user.proyectomoviles.Model.Adaptador;
import com.example.user.proyectomoviles.Model.GrupoDeItems;

public class VerNotas extends AppCompatActivity {
    SparseArray<GrupoDeItems> grupos = new SparseArray<GrupoDeItems>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_notas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        crearDatos();
        ExpandableListView listView = (ExpandableListView) findViewById(R.id.listViewexp);
        Adaptador adapter = new Adaptador(this, grupos);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.MnuHome:
                return_home();
                return true;
            case R.id.MnuInfo:
                //Se encuentra en VerNotas
                return true;
            case R.id.MnuEdit:
                go_Edicion();
                return true;
            case R.id.MnuLogout:
                logout();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    public void return_home(){
        Intent intent= new Intent(VerNotas.this, HomePage.class);
        startActivity(intent);
    }

    public void go_Edicion(){
        Intent intent= new Intent(VerNotas.this, EdicionDatos.class);
        startActivity(intent);
    }

    public void logout(){
        Intent intent= new Intent(VerNotas.this, StartApp.class);
        startActivity(intent);
        finish();
    }

    public void crearDatos() {
        GrupoDeItems grupo0 = new GrupoDeItems("2016-1");
        grupo0.children.add("Curso 1");
        grupo0.children.add("Curso 2");
        grupo0.children.add("Curso 3");
        grupo0.children.add("Curso 4");
        grupos.append(0, grupo0);
        GrupoDeItems grupo1 = new GrupoDeItems("Historial");
        grupo1.children.add("2015-2");
        grupo1.children.add("2015-1");
        grupo1.children.add("2014-2");
        grupo1.children.add("2014-1");
        grupos.append(1, grupo1);
        GrupoDeItems grupo2 = new GrupoDeItems("Información General");
        grupo2.children.add("Creditos llevados:");
        grupo2.children.add("Creditos aprobados:");
        grupo2.children.add("Creditos jalados:");
        grupos.append(2, grupo2);
    }

}
