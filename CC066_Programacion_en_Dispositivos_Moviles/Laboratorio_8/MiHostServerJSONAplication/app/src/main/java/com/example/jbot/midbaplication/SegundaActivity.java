package com.example.jbot.midbaplication;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SegundaActivity extends AppCompatActivity {


    private ProgressDialog pDialog;
    JSONParser jParser = new JSONParser();
    ArrayList<HashMap<String, String>> empresaList;
    private static String url_all_empresas = "http://gatotesrcc.esy.es/laboratorio8/get_all_empresas.php";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_PRODUCTS = "empresas";
    private static final String TAG_ID = "id";
    private static final String TAG_NOMBRE = "nombre";
    JSONArray products = null;
    ListView lista;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        empresaList = new ArrayList<HashMap<String, String>>();
        new LoadAllProducts().execute();
        lista = (ListView) findViewById(R.id.listAllProducts);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        //client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private class LoadAllProducts extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(SegundaActivity.this);
            pDialog.setMessage("Cargando comercios. Por favor espere...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... args) {
            List params = new ArrayList();
            JSONObject json = jParser.makeHttpRequest(url_all_empresas, "POST", params);
            Log.d("All Products: ", json.toString());
            try {
                int success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                    products = json.getJSONArray(TAG_PRODUCTS);
                    for (int i = 0; i < products.length(); i++) {
                        JSONObject c = products.getJSONObject(i);
                        String id = c.getString(TAG_ID);
                        String name = c.getString(TAG_NOMBRE);
                        HashMap map = new HashMap();
                        map.put(TAG_ID, id);
                        map.put(TAG_NOMBRE, name);
                        empresaList.add(map);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String file_url) {
            pDialog.dismiss();
            runOnUiThread(new Runnable() {
                public void run() {
                    ListAdapter adapter = new SimpleAdapter(SegundaActivity.this, empresaList, R.layout.single_post,
                            new String[]{
                                    TAG_ID,
                                    TAG_NOMBRE,
                            },
                            new int[]{
                                    R.id.single_post_tv_id,
                                    R.id.single_post_tv_nombre,
                            });
                    //setListAdapter(adapter);
                    lista.setAdapter(adapter);
                }
            });
        }
    }


}
