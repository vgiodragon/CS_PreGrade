package com.example.jbot.minotificacion;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
//import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public int numMessages = 0;
    private Button btnLanzaNot;
    private Button btnCambiaNot;
    private Button btnBorraNot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLanzaNot = (Button)findViewById(R.id.btnLanzaNot);
        btnCambiaNot = (Button)findViewById(R.id.btnCambiaNot);
        btnBorraNot = (Button)findViewById(R.id.btnBorraNot);

        btnLanzaNot.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lanza(v);
                //Toast.makeText(MainActivity.this, "Hola Meow me lanzaste Kyaaaa", Toast.LENGTH_SHORT).show();
            }
        });

        btnCambiaNot.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                cambia(v);
            }
        });

        btnBorraNot.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                borra(v);
            }
        });
    }
    public void lanza(View v){
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                .setContentTitle("Mi notificación")
                .setContentText("Hola Mundo!")
                .setSmallIcon(R.mipmap.ic_launcher);

        Intent resultIntent = new Intent(this, MainActivity.class);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(this, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);

        int mNotificationId = 001;

        NotificationManager mNotifyMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mNotifyMgr.notify(mNotificationId, mBuilder.build());
    }

    public void cambia(View v){
        NotificationCompat.Builder mNotifyBuilder = new NotificationCompat.Builder(this)
                        .setContentTitle("Nuevo Mensaje")
                        .setContentText("Tienes mensajes nuevos!")
                        .setSmallIcon(R.mipmap.ic_launcher);

        int notifyID = 1;
        String currentText="Texto";
        mNotifyBuilder.setContentText(currentText).setNumber(++numMessages);

        NotificationManager mNotificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify( notifyID, mNotifyBuilder.build());
    }

    public void borra(View v){
        NotificationManager mNotificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        int notifyID = 1;
        mNotificationManager.cancel(notifyID);
    }
}
