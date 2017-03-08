package com.example.jbot.mimenu;


import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /*
    private Button btnSalir;
    private Button btnAcerca;
    private Button btnConfig;
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        btnAcerca = (Button) findViewById(R.id.btnAcerca);
        btnSalir = (Button) findViewById(R.id.btnSalir);
        btnConfig = (Button) findViewById(R.id.btnConfig);

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ConfigActivity.class);
                startActivity(intent);
            }
        });
        btnAcerca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(MainActivity.this);

// Setting Dialog Title
                alertDialog2.setTitle("Mis Datos");

// Setting Dialog Message
                alertDialog2.setMessage("Nombres: Felipe Adrian\nApellidos: Moreno Vera\nCodigo: 20120354I");

// Setting Icon to Dialog
                alertDialog2.setIcon(R.mipmap.ic_launcher);

// Setting Positive "Yes" Btn
                alertDialog2.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to execute after dialog
                        //Toast.makeText(getApplicationContext(), "You clicked on YES", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                });
// Showing Alert Dialog
                alertDialog2.show();
            }
        });
        */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_info:
                final AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(MainActivity.this);

                // Setting Dialog Title
                alertDialog2.setTitle("Mis Datos");

                // Setting Dialog Message
                alertDialog2.setMessage("Nombres: Felipe Adrian\nApellidos: Moreno Vera\nCodigo: 20120354I");

                // Setting Icon to Dialog
                alertDialog2.setIcon(R.mipmap.ic_launcher);

                // Setting Positive "Yes" Btn
                alertDialog2.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to execute after dialog
                        //Toast.makeText(getApplicationContext(), "You clicked on YES", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                });
                // Showing Alert Dialog
                alertDialog2.show();
                // User chose the "Settings" item, show the app settings UI...
                return true;

            case R.id.action_config:
                Intent intent = new Intent(getApplicationContext(), ConfigActivity.class);
                startActivity(intent);
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;

            case R.id.action_salir:
                finish();
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }
}
