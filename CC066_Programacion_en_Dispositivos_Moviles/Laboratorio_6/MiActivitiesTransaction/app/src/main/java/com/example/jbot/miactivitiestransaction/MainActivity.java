package com.example.jbot.miactivitiestransaction;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    public void forwardZoom(View button){
        startActivity(new Intent(this, SegundaActivity.class));
        overridePendingTransition(R.anim.zoom_forward_in,R.anim.zoom_forward_out);
    }
    public void left(View button){
        startActivity(new Intent(this, SegundaActivity.class));
        overridePendingTransition(R.anim.left_in, R.anim.left_out);
    }
    public void fade(View button){
        startActivity(new Intent(this, SegundaActivity.class));
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
    public void byDefault(View button){
        startActivity(new Intent(this, SegundaActivity.class));
    }
}
