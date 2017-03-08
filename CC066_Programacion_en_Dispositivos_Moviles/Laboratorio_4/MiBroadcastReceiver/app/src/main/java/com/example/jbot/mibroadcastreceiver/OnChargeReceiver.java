package com.example.jbot.mibroadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by jbot on 23/01/16.
 */

// esta clase pertenece a la mini actividad 1

public class OnChargeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context ctx, Intent i) {
        Log.d("App", "Recibido!");
        Toast.makeText(ctx, "Ha conectado el cargador.",Toast.LENGTH_SHORT).show();
    }
}
