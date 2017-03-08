package com.example.jbot.miactivitiestransaction;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class SegundaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    public void zoomBack(View button){
        startActivity(new Intent(this, MainActivity.class));
        overridePendingTransition(R.anim.zoom_back_in,
                R.anim.zoom_back_out);
    }
    public void fade1(View button){
        startActivity(new Intent(this, MainActivity.class));
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
    public void right(View button){
        startActivity(new Intent(this, MainActivity.class));
        overridePendingTransition(R.anim.right_in, R.anim.right_out);
    }
    public void back(View button){
        super.onBackPressed();
    }
}
