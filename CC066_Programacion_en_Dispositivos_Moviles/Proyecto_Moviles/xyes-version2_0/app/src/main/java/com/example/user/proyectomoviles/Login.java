package com.example.user.proyectomoviles;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Login extends AppCompatActivity{

    private EditText user, pass;
    private Button bLogin;
    private static String LOGIN_URL = "http://gaTotesrcc.esy.es/Xyes/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        user = (EditText)findViewById(R.id.username);
        pass = (EditText)findViewById(R.id.password);
        bLogin = (Button)findViewById(R.id.login);

        bLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!compruebaConexion(Login.this)) {
                    Toast.makeText(getBaseContext(),"Necesaria conexión a internet ", Toast.LENGTH_SHORT).show();
                } else {
                    if ((!user.getText().toString().equals("")) && (!pass.getText().toString().equals(""))) {
                        login(view);
                    } else if ((!user.getText().toString().equals(""))) {
                        Toast.makeText(getApplicationContext(),
                                "Falta ingresar contraseña", Toast.LENGTH_SHORT).show();
                    } else if ((!pass.getText().toString().equals(""))) {
                        Toast.makeText(getApplicationContext(),
                                "Falta ingresar usuario", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(),
                                "Falta ingresar usuario y contraseña", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    // comprueba la conexi[on a internet

    public static boolean compruebaConexion(Context context) {

        boolean connected = false;

        ConnectivityManager connec = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        // Recupera todas las redes (tanto móviles como wifi)
        NetworkInfo[] redes = connec.getAllNetworkInfo();

        for (int i = 0; i < redes.length; i++) {
            // Si alguna red tiene conexión, se devuelve true
            if (redes[i].getState() == NetworkInfo.State.CONNECTED) {
                connected = true;
            }
        }
        return connected;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.MnuBack:
                return_back();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    public void return_back(){
        Intent intent= new Intent(Login.this, StartApp.class);
        startActivity(intent);
        finish();
    }

    public void login(View v) {
        String username = user.getText().toString();
        String password = pass.getText().toString();
        ConnexionTask.TaskListener listener = new ConnexionTask.TaskListener() {
            @Override
            public void onFinished(String result) {
                Intent ii = new Intent(Login.this,EdicionDatos.class);
                ii.putExtra("IdAlumno",user.getText().toString());
                finish();
                // this finish() method is used to tell android os that we are done with current //activity now! Moving to other activity
                startActivity(ii);
            }
        };
        new ConnexionTask(Login.this,LOGIN_URL,listener,0).execute(username, password);
    }
}
