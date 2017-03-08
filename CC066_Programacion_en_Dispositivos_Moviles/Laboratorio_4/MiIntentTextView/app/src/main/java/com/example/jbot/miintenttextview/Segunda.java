package com.example.jbot.miintenttextview;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Segunda extends AppCompatActivity {

    private TextView textUser;
    private TextView textPass;
    private Button btnLlamar;
    String user;
    String pass;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);
        btnLlamar = (Button) findViewById(R.id.btnLlamar);
        textUser = (TextView) findViewById(R.id.textUser);
        textPass = (TextView) findViewById(R.id.textPasswd);
        user = getIntent().getStringExtra("User");
        pass = getIntent().getStringExtra("Pass");
        textUser.setText("Mr. "+user);
        textPass.setText("With phone "+pass);
        btnLlamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:" +pass));
                startActivity(i);
            }
        });
    }
}
