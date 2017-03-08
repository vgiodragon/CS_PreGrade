package com.example.jbot.mitextfile;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private Button btnCFI;
    private Button btnLFI;
    private Button btnCFE;
    private Button btnLFE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        btnCFI = (Button) findViewById(R.id.btnCFI);
        btnLFI = (Button) findViewById(R.id.btnLFI);
        btnCFE = (Button) findViewById(R.id.btnCFE);
        btnLFI = (Button) findViewById(R.id.btnLFE);
    }

    public void crearFicheroInterno(View view){
        try{
            OutputStreamWriter fout= new OutputStreamWriter(openFileOutput("prueba_int.txt", Context.MODE_PRIVATE));
            fout.write("Texto de prueba Interna.");
            fout.close();
            Toast.makeText(this,"se Creo fichero interno", Toast.LENGTH_LONG).show();
        }
        catch (Exception ex){
            Log.e("Ficheros", "Error al escribir fichero a memoria interna");
        }
    }

    public void leerFicheroInterno(View view){
        try
        {
            BufferedReader fin =new BufferedReader( new InputStreamReader(openFileInput("prueba_int.txt")));
            String texto = fin.readLine();
            Toast.makeText(this,texto, Toast.LENGTH_LONG).show();
            fin.close();
        }
        catch (Exception ex){
            Log.e("Ficheros", "Error al leer desde memoria interna");
        }
    }

    public void crearFicheroExterno(View view){
        boolean sdDisponible = false;
        boolean sdAccesoEscritura = false;

        //Comprobamos el estado de la memoria externa (tarjeta SD)
        String estado = Environment.getExternalStorageState();

        if (estado.equals(Environment.MEDIA_MOUNTED))
        {
            sdDisponible = true;
            sdAccesoEscritura = true;
        }
        else if (estado.equals(Environment.MEDIA_MOUNTED_READ_ONLY))
        {
            sdDisponible = true;
            sdAccesoEscritura = false;
        }
        else
        {
            sdDisponible = false;
            sdAccesoEscritura = false;
        }

        //Si la memoria externa está disponible y se puede escribir
        if (sdDisponible && sdAccesoEscritura)
        {
            try
            {
                File ruta_sd_global = Environment.getExternalStorageDirectory();
                //File ruta_sd_app_musica = getExternalFilesDir(Environment.DIRECTORY_MUSIC);

                File f = new File(ruta_sd_global.getAbsolutePath(), "prueba_sd.txt");

                OutputStreamWriter fout = new OutputStreamWriter(new FileOutputStream(f));

                fout.write("Texto de prueba Externa.");
                fout.close();
                Toast.makeText(this,"se Creo fichero externo", Toast.LENGTH_LONG).show();
            }
            catch (Exception ex)
            {
                Log.e("Ficheros", "Error al escribir fichero a tarjeta SD");
            }
        }
    }

    public void leerFicheroExterno(View view){
        try
        {
            File ruta_sd = Environment.getExternalStorageDirectory();
            File f = new File(ruta_sd.getAbsolutePath(), "prueba_sd.txt");
            BufferedReader fin = new BufferedReader( new InputStreamReader(new FileInputStream(f)));
            String texto = fin.readLine();
            Toast.makeText(this,texto, Toast.LENGTH_LONG).show();
            fin.close();
        }
        catch (Exception ex)
        {
            Log.e("Ficheros", "Error al leer fichero desde tarjeta SD");
        }
    }

}
