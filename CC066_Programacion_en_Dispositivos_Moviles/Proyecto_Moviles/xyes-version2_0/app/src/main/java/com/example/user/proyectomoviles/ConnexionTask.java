package com.example.user.proyectomoviles;

import android.app.ProgressDialog;
import android.content.Context;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by jbot on 10/03/16.
 */
public class ConnexionTask extends AsyncTask<String, Void, String> {
    /**
     * Before starting background thread Show Progress Dialog
     * */

    // Hacemos una interface para pasar por parametro una funcion
    public interface TaskListener {
        public void onFinished(String result);
    }

    // This is the reference to the associated listener
    private final TaskListener taskListener;
    private Context context;
    private String URL_LOGIN;
    private ProgressDialog pDialog;
    String success,message;
    private int info;

    public ConnexionTask(Context con, String url, TaskListener task,int Info){
        this.context=con;
        this.URL_LOGIN=url;
        this.taskListener = task;
        this.info=Info;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pDialog = new ProgressDialog(this.context);
        pDialog.setMessage("Verificando el login ...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
    }

    @Override
    protected String doInBackground(String... arg0) {
        String stream = null;
        JSONObject json = new JSONObject();
        try{
            String username = (String)arg0[0];
            String password = (String)arg0[1];
            String link = URL_LOGIN;
            String data = URLEncoder.encode("IdAlumno", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");
            if(this.info==0){
                data += "&" + URLEncoder.encode("passwd", "UTF-8") + "=" +  URLEncoder.encode(password, "UTF-8");
            }
            URL url = new URL(link);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

            wr.write( data );
            wr.flush();
            // Check the connection status
            if(conn.getResponseCode() == 200)
            {
                // if response code = 200 ok
                InputStream in = new BufferedInputStream(conn.getInputStream());

                // Read the BufferedInputStream
                BufferedReader r = new BufferedReader(new InputStreamReader(in));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = r.readLine()) != null) {
                    sb.append(line);
                }
                stream = sb.toString();

                // End reading...............
                Log.d("result ****", String.valueOf((stream)));

                // todo el valor de stream, lo adjunta al objeto json
                json.put("response_", new JSONObject(stream));
                // Disconnect the HttpURLConnection
                conn.disconnect();
            }
            else
            {
                // Do something
            }
            return String.valueOf(json);
        }
        catch(Exception e){
            return new String("Exception: " + e.getMessage());
        }
    }
    /**
     * Once the background process is done we need to  Dismiss the progress dialog asap
     * **/
    @Override
    protected void onPostExecute(String result) {
        pDialog.dismiss();
            /*
            if (result != null){
                Toast.makeText(Login.this, result, Toast.LENGTH_LONG).show();
            }
            */
        String json_str = String.valueOf(result);
        try {
            // Obtiene el JSON resultante, que es la etiqueta "response_"
            // que contiene todo {"response_":{"data":"20120354","message":"Te logeaste correctamente .","success":1}}
            JSONObject my_obj = new JSONObject(json_str);

            // Aqui filtra el primer campo, es decir devuele lo contenido en "response_"
            String response = my_obj.getString("response_");

            // Tramsforma el primer filtrado en un objeto JSON con dicha info.
            JSONObject responseFiltro = new JSONObject(response);

            // Hace un segundo filtrado, ahora obteniedo el valor de "data"
            //String data = responseFiltro.getString("data");

            Log.d("my_obj", String.valueOf(my_obj));
            Log.d("response", String.valueOf(response));
            Log.d("response__", String.valueOf(responseFiltro));
            //Log.d("resultado", String.valueOf(data));

            success = responseFiltro.getString("success");
            message = responseFiltro.getString("message");

            if (success.equals("1")) {
                if(this.taskListener != null) {
                    // Y si llamamos a la funcion desde atras
                    if (this.info==1) {
                        this.taskListener.onFinished(response);
                    } else if(this.info==0){
                        this.taskListener.onFinished(result);
                    }
                }
            }else if (success.equals("0")){
                Toast.makeText(this.context, message, Toast.LENGTH_LONG).show();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}