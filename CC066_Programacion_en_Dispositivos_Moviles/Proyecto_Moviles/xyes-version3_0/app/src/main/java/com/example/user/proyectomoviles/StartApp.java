package com.example.user.proyectomoviles;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class StartApp extends AppCompatActivity {
    private Button btnIntranet;
    private Button btnAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_app);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        btnIntranet = (Button) findViewById(R.id.btnIntranet);
        btnIntranet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                go_login();
            }
        });
    }

    public void go_login(){
        Intent intent= new Intent(StartApp.this, Login.class);
        startActivity(intent);
        finish();
    }

}
