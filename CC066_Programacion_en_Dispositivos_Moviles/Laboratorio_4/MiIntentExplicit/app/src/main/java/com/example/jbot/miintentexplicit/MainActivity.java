package com.example.jbot.miintentexplicit;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.net.Uri;
import android.provider.AlarmClock;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnWeb;
    private Button btnMarcar;
    private Button btnLlamar;
    private Button btnAnadir;
    private Button btnAlarma;
    private Button btnCorreo;
    private Button btnCam;
    private Button btnVideo;
    private Button btnGPS;
    private Button btnComparte;
    private Intent i;
    private LocationManager locationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        btnWeb = (Button) findViewById(R.id.btnWeb);
        btnMarcar = (Button) findViewById(R.id.btnMarcar);
        btnLlamar = (Button) findViewById(R.id.btnLlamar);
        btnAnadir = (Button) findViewById(R.id.btnAnadir);
        btnAlarma = (Button) findViewById(R.id.btnAlarma);
        btnCorreo = (Button) findViewById(R.id.btnCorreo);
        btnCam = (Button) findViewById(R.id.btnCam);
        btnVideo = (Button) findViewById(R.id.btnVideo);
        btnGPS = (Button) findViewById(R.id.btnGPS);
        btnComparte = (Button) findViewById(R.id.btnComparte);

        // BOTON 1 Abrir una web
        btnWeb.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://www.uni.edu.pe"));
                startActivity(i);
            }
        });

        // BOTON 2 Marcar un numero
        btnMarcar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                i = new Intent(Intent.ACTION_DIAL);
                startActivity(i);
            }
        });

        // BOTON 3 Llamar numeros
        btnLlamar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                i = new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:" + getResources().getString(R.string.telefono)));
                startActivity(Intent.createChooser(i, "Call Using..."));
            }
        });

        // BOTON 4 Anadir contactos
        btnAnadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(Intent.ACTION_INSERT);
                i.setType(ContactsContract.Contacts.CONTENT_TYPE);
                startActivity(i);
            }
        });

        // BOTON 5 crear o modificar alarmas
        btnAlarma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(AlarmClock.ACTION_SET_ALARM);
                i.putExtra(AlarmClock.EXTRA_MESSAGE, "Prueba Custom Alarm Clock");
                i.putExtra(AlarmClock.EXTRA_HOUR, 9);
                i.putExtra(AlarmClock.EXTRA_MINUTES, 37);
                startActivity(i);
            }
        });

        // BOTON 6 Enviar Correo electronico
        btnCorreo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", getResources().getString(R.string.mail), null));
                i.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.subject));
                startActivity(Intent.createChooser(i, getResources().getString(R.string.envio)));
            }
        });

        // BOTON 7 Camara
        btnCam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(i);
            }
        });

        // BOTON 8 Video
        btnVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(MediaStore.INTENT_ACTION_VIDEO_CAMERA);
                startActivity(i);
            }
        });


        // BOTON 9 GPS
        btnGPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("google.streetview:cbll=416.414382,100.013988");
                i = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                i.setPackage("com.google.android.apps.maps");
//                i.setData(Uri.parse(getResources().getString(R.string.coord)));
                startActivity(i);
            }
        });



        // BOTON 10 Compartir
        btnComparte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent();
                i.setAction(Intent.ACTION_SEND);
                i.putExtra(Intent.EXTRA_TEXT, getResources().getString(R.string.text));
                i.setType("text/plain");
                startActivity(i);
            }
        });

    }
}
