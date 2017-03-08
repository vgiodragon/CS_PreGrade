package com.example.jbot.mibroadcastreceiverdinamic;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String CUSTOM_INTENT ="dispositivosmoviles.broadcastreceiver.vibracion";
    private final IntentFilter intentFilter = new IntentFilter(CUSTOM_INTENT);
    private final VibrateReceiver receiver = new VibrateReceiver();
    private LocalBroadcastManager mBroadcastMgr;
    Button btnReceive;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBroadcastMgr = LocalBroadcastManager.getInstance(getApplicationContext());
        mBroadcastMgr.registerReceiver(receiver, intentFilter);
        btnReceive = (Button) findViewById(R.id.buttonReceive);
        btnReceive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendBroadcast(new Intent(CUSTOM_INTENT));
            }
        });
    }
}
