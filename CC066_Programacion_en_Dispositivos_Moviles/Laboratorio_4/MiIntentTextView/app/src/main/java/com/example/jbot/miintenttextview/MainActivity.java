package com.example.jbot.miintenttextview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btnIngreso;
    private EditText textUser;
    private EditText textPasswd;
    String user;
    String pass;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textUser = (EditText) findViewById(R.id.user);
        textPasswd = (EditText) findViewById(R.id.passwd);
        btnIngreso = (Button) findViewById(R.id.btnIngreso);
        btnIngreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = textUser.getText().toString();
                pass = textPasswd.getText().toString();
                i = new Intent(getApplicationContext(), Segunda.class);
                i.putExtra("User",user);
                i.putExtra("Pass",pass);
                startActivity(i);
            }
        });

    }
}
