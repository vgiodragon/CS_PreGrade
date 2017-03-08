package com.example.jbot.mibroadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;

/**
 * Created by jbot on 23/01/16.
 */

// esta clase pertenece a la mini actividad 2


public class VibrateReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent){
        Vibrator mVibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        mVibrator.vibrate(500);
    }
}
