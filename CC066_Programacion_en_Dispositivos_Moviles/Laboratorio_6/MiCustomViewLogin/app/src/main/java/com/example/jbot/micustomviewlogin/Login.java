package com.example.jbot.micustomviewlogin;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by jbot on 25/02/16.
 */
public class Login extends LinearLayout {

    private OnLoginListener listener;
    private EditText txtUsuario;
    private EditText txtPassword;
    private Button btnLogin;
    private TextView lblMensaje; //el mensaje de CustomView

    public Login(Context context) {
        super(context);
        inicializar();
    }
    public Login(Context context, AttributeSet attrs) {
        super(context, attrs);
        inicializar();
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.ControlLogin);
        String textoBoton = a.getString(R.styleable.ControlLogin_login_text);
        btnLogin.setText(textoBoton);
        a.recycle();
    }

    public void setOnLoginListener(OnLoginListener l)
    {
        listener = l;
    }

    private void inicializar()
    {
        //Utilizamos el layout 'control_login' como interfaz del control
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater)getContext().getSystemService(infService);
        li.inflate(R.layout.login, this, true);

        //Obtenemoslas referencias a los distintos control
        txtUsuario = (EditText)findViewById(R.id.TxtUsuario);
        txtPassword = (EditText)findViewById(R.id.TxtPassword);
        btnLogin = (Button)findViewById(R.id.BtnAceptar);
        lblMensaje = (TextView)findViewById(R.id.LblMensaje);

        //Asociamos los eventos necesarios
        asignarEventos();
    }

    private void asignarEventos()
    {
        btnLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onLogin(txtUsuario.getText().toString(),
                        txtPassword.getText().toString());
            }
        });
    }

    public void setMensaje(String msg)
    {
        lblMensaje.setText(msg);
    }
}
