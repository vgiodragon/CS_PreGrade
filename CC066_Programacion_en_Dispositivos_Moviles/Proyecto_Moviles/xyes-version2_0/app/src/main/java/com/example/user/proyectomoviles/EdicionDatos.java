package com.example.user.proyectomoviles;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class EdicionDatos extends AppCompatActivity {

    private TextView nombres,apellidoP,apellidoM,codigo,ciclo,facultad,especialidad,cel,mail;
    private Button btnModificar;
    String cadenaBoton="Guardar";
    String cadenaBoton1="Cancelar";
    private static String INFO_URL = "http://gaTotesrcc.esy.es/Xyes/get_info_alumno.php";
    private String ID_Alumno;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edicion_datos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nombres = (TextView) findViewById(R.id.textNombre1);
        apellidoP = (TextView) findViewById(R.id.textApellidoP1);
        apellidoM = (TextView) findViewById(R.id.textApellidoM1);
        codigo = (TextView) findViewById(R.id.textCodigo1);
        ciclo = (TextView) findViewById(R.id.textCiclo1);
        facultad = (TextView) findViewById(R.id.textFacultad1);
        especialidad = (TextView) findViewById(R.id.textEsp1);
        cel = (TextView) findViewById(R.id.textCel1);
        mail = (TextView) findViewById(R.id.textEmail1);
        btnModificar = (Button) findViewById(R.id.btnModificar);

        ID_Alumno = getIntent().getStringExtra("IdAlumno");

        mostrarInfoAlumno();
        modificarValores();

        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modificar(v);
            }
        });
    }

    private void mostrarInfoAlumno() {
        ConnexionTask.TaskListener listener = new ConnexionTask.TaskListener() {
            @Override
            public void onFinished(String result) {
                String json_str = String.valueOf(result);
                try {
                    JSONObject my_obj = new JSONObject(json_str);
                    Log.d("my_obj", String.valueOf(my_obj));
                    nombres.setText(my_obj.getString("nombres"));
                    apellidoP.setText(my_obj.getString("apellidoP"));
                    apellidoM.setText(my_obj.getString("apellidoM"));
                    codigo.setText(my_obj.getString("codigo"));
                    ciclo.setText(my_obj.getString("ciclo"));
                    facultad.setText(my_obj.getString("facultad"));
                    especialidad.setText(my_obj.getString("especialidad"));
                    cel.setText(my_obj.getString("cel"));
                    mail.setText(my_obj.getString("mail"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        new ConnexionTask(EdicionDatos.this,INFO_URL,listener,1).execute(ID_Alumno,null);
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
                go_Notas();
                return true;
            case R.id.MnuEdit:
                //Se encuentra en EdicionDatos
                return true;
            case R.id.MnuLogout:
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
        Intent intent= new Intent(EdicionDatos.this, StartApp.class);
        startActivity(intent);
        finish();
    }

    public void modificarValores(){
        String cadenaB = getIntent().getStringExtra("botonApretado");
        if (cadenaBoton.equals(cadenaB)){
            Toast t = Toast.makeText(EdicionDatos.this,"Se guardo correctamente",Toast.LENGTH_SHORT);
            t.show();
            cel.setText(getIntent().getStringExtra("Cel"));
            mail.setText(getIntent().getStringExtra("Mail"));
        }
        if(cadenaBoton1.equals(cadenaB)){
            Toast t = Toast.makeText(EdicionDatos.this,"No se modifico ningun dato",Toast.LENGTH_SHORT);
            t.show();
            cel.setText(getIntent().getStringExtra("Cel"));
            mail.setText(getIntent().getStringExtra("Mail"));
        }
    }

    public void modificar(View view){
        Intent intent = new Intent(getApplicationContext(), ModificarDatos.class);
        intent.putExtra("Nombre1",nombres.getText().toString());
        intent.putExtra("ApellidoP1",apellidoP.getText().toString());
        intent.putExtra("ApellidoM1",apellidoM.getText().toString());
        intent.putExtra("Codigo1",codigo.getText().toString());
        intent.putExtra("Ciclo1",ciclo.getText().toString());
        intent.putExtra("Facultad1",facultad.getText().toString());
        intent.putExtra("Especialidad1",especialidad.getText().toString());
        intent.putExtra("Cel1",cel.getText().toString());
        intent.putExtra("Mail1", mail.getText().toString());
        startActivity(intent);
        finish();
    }

}
