package com.example.jbot.midbaplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText usernameField,passwordField;
    private TextView status,role,method;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        usernameField = (EditText)findViewById(R.id.editUser);
        passwordField = (EditText)findViewById(R.id.editKey);
        status = (TextView)findViewById(R.id.textIniSes);
        role = (TextView)findViewById(R.id.textAsignado);
        method = (TextView)findViewById(R.id.textMetodo);
    }

    public void login(View view){
        String username = usernameField.getText().toString();
        String password = passwordField.getText().toString();
        method.setText("Get Metodo");
        new SigninActivity(this,status,role,0).execute(username,password);
    }

    public void loginPost(View view){
        String username = usernameField.getText().toString();
        String password = passwordField.getText().toString();
        method.setText("Post Metodo");
        new SigninActivity(this,status,role,1).execute(username,password);
    }
}
