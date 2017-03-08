package com.example.jbot.mibroadcastreceiver;

import android.Manifest;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String CUSTOM_INTENT ="dispositivosmoviles.broadcastreceiver.vibracion";
    Button btnReceive;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnReceive = (Button) findViewById(R.id.buttonReceive);
        btnReceive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendBroadcast(new Intent(CUSTOM_INTENT),Manifest.permission.VIBRATE);
            }
        });
    }
}
