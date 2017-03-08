package com.example.jbot.miprogressbar;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.annotation.MainThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private Button btnSinHilos;
    private Button btnHilo;
    private Button btnAsyncTask;
    private Button btnCancelar;
    private Button btnAsyncDialog;
    private ProgressBar pbarProgreso;
    private ProgressDialog pDialog;
    private MiTareaAsincrona tarea1;
    private MiTareaAsincronaDialog tarea2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSinHilos = (Button)findViewById(R.id.btnSinHilos);
        btnHilo = (Button)findViewById(R.id.btnHilo);
        btnAsyncTask = (Button)findViewById(R.id.btnAsyncTask);
        btnCancelar = (Button)findViewById(R.id.btnCancelar);
        btnAsyncDialog = (Button)findViewById(R.id.btnAsyncDialog);
        pbarProgreso = (ProgressBar)findViewById(R.id.pbarProgreso);

        btnSinHilos.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                pbarProgreso.setMax(100);
                pbarProgreso.setProgress(0);
                for(int i=1;i<=10;i++){
                    tareaLarga();
                    pbarProgreso.incrementProgressBy(10);
                }
                Toast.makeText(MainActivity.this,"Tarea Finalizada!", Toast.LENGTH_SHORT).show();
            }
        });

        btnHilo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    public void run() {
                        pbarProgreso.post(new Runnable() {
                            public void run() {
                                pbarProgreso.setProgress(0);
                            }
                        });

                        for (int i = 1; i <= 10; i++) {
                            tareaLarga();
                            pbarProgreso.post(new Runnable() {
                                public void run() {
                                    pbarProgreso.incrementProgressBy(10);
                                }
                            });
                        }

                        runOnUiThread(new Runnable() {
                            public void run() {
                                Toast.makeText(MainActivity.this, "Tarea Finalizada!", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).start();
            }
        });

        btnAsyncTask.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                tarea1 = new MiTareaAsincrona();
                tarea1.execute();
            }
        });

        btnAsyncDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Instancia un nuevo ProgressDialog
                pDialog = new ProgressDialog(MainActivity.this);
                // setea el estilo del ProgressDialog
                pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                // Setea el mensaje que va a mosrrar mientras se ejecuta
                pDialog.setMessage("Procesando...");
                // Setea la opción de ser cancelable (con true) si fuera falsse, no se cancela.
                pDialog.setCancelable(true);
                // Le da el máximo numero, similar al PrograssBar
                pDialog.setMax(100);
                // Instancia la clase MiTareaAsincronaDialog
                tarea2 = new MiTareaAsincronaDialog();
                tarea2.execute();  // La ejecuta
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                tarea1.cancel(true);
            }
        });
    }

    private void tareaLarga(){
        try{
            Thread.sleep(1000);
        } catch(InterruptedException e){}
    }
    private class MiTareaAsincrona extends AsyncTask<Void, Integer, Boolean> {
        @Override
        protected Boolean doInBackground(Void... params) {
            for(int i=1; i<=10; i++) {
                tareaLarga();
                publishProgress(i*10);
                if(isCancelled())
                    break;
            }
            return true;
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            int progreso = values[0].intValue();
            pbarProgreso.setProgress(progreso);
        }
        @Override
        protected void onPreExecute() {
            pbarProgreso.setMax(100);
            pbarProgreso.setProgress(0);
        }
        @Override
        protected void onPostExecute(Boolean result) {
            if(result)
                Toast.makeText(MainActivity.this, "Tarea finalizada!",
                        Toast.LENGTH_SHORT).show();
        }
        @Override
        protected void onCancelled() {
            Toast.makeText(MainActivity.this, "Tarea cancelada!",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private class MiTareaAsincronaDialog extends AsyncTask <Void,Integer, Boolean> {
        @Override
        protected Boolean doInBackground(Void... params) {
            for(int i=1; i<=10; i++) {
                tareaLarga();
                publishProgress(i*10);
                if(isCancelled())
                    break;
            }
            return true;
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            int progreso = values[0].intValue();
            pDialog.setProgress(progreso);
        }
        @Override
        protected void onPreExecute() {
            pDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    MiTareaAsincronaDialog.this.cancel(true);
                }
            });
            pDialog.setProgress(0);
            pDialog.show();
        }
        @Override
        protected void onPostExecute(Boolean result) {
            if(result) {
                pDialog.dismiss();
                Toast.makeText(MainActivity.this, "Tarea finalizada!",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
}