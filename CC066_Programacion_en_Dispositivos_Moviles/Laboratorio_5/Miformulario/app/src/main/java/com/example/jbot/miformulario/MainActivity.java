package com.example.jbot.miformulario;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private ImageButton imageButton;
    private Button btnVolver;
    private Button btnSalvar;
    private ToggleButton togglebutton;
    private CheckBox checkbox;
    private EditText edittext;
    private RadioButton radio_red;
    private RadioButton radio_blue;
    private RatingBar ratingbar;
    // otra manera
    private RadioGroup radio_group;
    private RadioButton radio_aux;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnVolver = (Button) findViewById(R.id.btnVolver);
        btnSalvar = (Button) findViewById(R.id.btnSalvar);
        togglebutton = (ToggleButton) findViewById(R.id.toggleButton);
        checkbox = (CheckBox) findViewById(R.id.checkBox);
        edittext = (EditText) findViewById(R.id.editText);
        radio_red = (RadioButton) findViewById(R.id.radioBtnRed);
        radio_blue = (RadioButton) findViewById(R.id.radioBtnBlue);
        ratingbar = (RatingBar) findViewById(R.id.ratingBar);
        setUpViews();
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void setUpViews(){
        imageButton = (ImageButton) findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                new Thread(new Runnable() {
                    public void run() {
                        runOnUiThread(new Runnable() {
                            public void run() {
                                Toast.makeText(MainActivity.this, "ImageButton seleccionado", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).start();
            }
        });
    }

    public void presionado(View v){
        new Thread(new Runnable() {
            public void run() {
                runOnUiThread(new Runnable() {
                    public void run() {
                        /*
                        int selectedId = radio_group.getCheckedRadioButtonId();
                        radio_aux = (RadioButton) findViewById(selectedId);
                        Toast.makeText(MainActivity.this,radio_aux.getText(), Toast.LENGTH_SHORT).show();
                        */
                        String cad = "";
                        if (radio_blue.isChecked()) {
                            cad = "azul";
                        } else if (radio_red.isChecked()) {
                            cad = "rojo";
                        }
                        Toast.makeText(MainActivity.this, "Radio button " + cad + " seleccionado", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).start();
    }

    public void vibra(View v){
        new Thread(new Runnable() {
            public void run() {
                runOnUiThread(new Runnable() {
                    public void run() {
                        String cad="";
                        if(togglebutton.isChecked()){
                            cad="On";
                        }
                        else{
                            cad="Off";
                        }
                        Toast.makeText(MainActivity.this,"Vibrate "+cad, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).start();
    }

    public void sendClick(View v) {
// Perform action on clicks
        String allText = new String("campo:" + edittext.getText()+"\n");
        allText = allText + "checkbox:";
        if (checkbox.isChecked()) {
            allText = allText + "Checked\n";
        } else {
            allText = allText + "Not Checked\n";
        }

        allText = allText + "radios:rojo:";
        String redtext = "";
        if (radio_red.isChecked()) {
            redtext = "pulsado:\n";
        } else {
            redtext = "no pulsado:\n";
        }
        allText = allText + redtext;

        allText = allText + "radios:azul:";
        String bluetext = "";
        if (radio_blue.isChecked()) {
            bluetext = "pulsado\n";
        } else {
            bluetext = "no pulsado\n";
        }
        allText = allText + bluetext;


        allText = allText + "toggle:vibrate:";
        if (togglebutton.isChecked()) {
            allText = allText + "On\n";
        } else {
            allText = allText + "Off\n";
        }

        allText = allText + "rating:";
        float f = ratingbar.getRating();
        allText = allText + Float.toString( f ) + "\n";
        Log.d("app", allText);
        Toast.makeText(this, allText, Toast.LENGTH_SHORT).show();
    }
    public void checkBoxClick(View v) {
        String text = "";
        if (checkbox.isChecked()) {
            text = "check box Seleccionado";
            btnSalvar.setEnabled(true);
            Toast.makeText(this,"Ya puedes Salvar", Toast.LENGTH_SHORT).show();
        } else {
            btnSalvar.setEnabled(false);
            Toast.makeText(this, "Hasta que no marques la casilla no podrás salvar",Toast.LENGTH_SHORT).show();
                    text = "check box No seleccionado";
        }
        Toast.makeText(this,text, Toast.LENGTH_SHORT).show();
    }
}
