package com.example.jbot.midbaplication;

import android.content.Context;
import android.os.AsyncTask;

import android.util.Log;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;

/**
 * Created by jbot on 28/02/16.
 */
public class SigninActivity  extends AsyncTask<String,Void,String>{
    private TextView statusField,roleField;
    private Context context;
    private int byGetOrPost = 0;

    //flag 0 means get and 1 means post.(By default it is get.)
    public SigninActivity(Context context,TextView statusField,TextView roleField,int flag) {
        this.context = context;
        this.statusField = statusField;
        this.roleField = roleField;
        byGetOrPost = flag;
    }

    protected void onPreExecute(){

    }

    @Override
    protected String doInBackground(String... arg0) {
        String stream = null;
        JSONObject json = new JSONObject();
        if(byGetOrPost == 0){ //means by Get Method
            try{
                String username = (String)arg0[0];
                String password = (String)arg0[1];
                String link = "http://gatotesrcc.esy.es/laboratorio8/methodGET.php?usuario="+username+"&clave="+password;
                URL url = new URL(link);
                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet();
                request.setURI(new URI(link));
                HttpResponse response = client.execute(request);
                if(response.getStatusLine().getStatusCode() == 200){
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        InputStream instream = entity.getContent();
                        String result= convertStreamToString(instream);
                        Log.d("result ****", String.valueOf((result)));
                        json.put("response_", new JSONObject(result));
                        instream.close();
                    }
                } else {
                    Log.d("result **** error", String.valueOf((0)));
                }
                return String.valueOf(json);
            } catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }
        } else {
            try{
                String username = (String)arg0[0];
                String password = (String)arg0[1];
                String link = "http://gatotesrcc.esy.es/laboratorio8/methodPOST.php";
                String data = URLEncoder.encode("usuario", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");
                data += "&" + URLEncoder.encode("clave", "UTF-8") + "=" +  URLEncoder.encode(password, "UTF-8");
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
    }

    @Override
    protected void onPostExecute(String result){
        this.statusField.setText("Entrar con éxito");

        String json_str = String.valueOf(result);
        try {
            JSONObject my_obj = new JSONObject(json_str);
            String response = my_obj.getString("response_");
            JSONObject response_ = new JSONObject(response);
            String data = response_.getString("data");
            Log.d("resultado", String.valueOf(data));
            this.roleField.setText(data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}