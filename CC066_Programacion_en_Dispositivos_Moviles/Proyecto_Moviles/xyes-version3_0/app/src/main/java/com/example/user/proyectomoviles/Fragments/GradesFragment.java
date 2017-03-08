package com.example.user.proyectomoviles.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;

import com.example.user.proyectomoviles.R;
import com.example.user.proyectomoviles.helpClass.MyExplanableListAdapter;
import com.example.user.proyectomoviles.hostRequest.ConnexionTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by jbot on 11/03/16.
 */
public class GradesFragment extends Fragment {
    MyExplanableListAdapter listAdapterCursos;
    MyExplanableListAdapter listAdapterHist;
    MyExplanableListAdapter listAdapterCred;
    ExpandableListView expListViewCursos;
    ExpandableListView expListViewHist;
    ExpandableListView expListViewCred;

    ArrayList<HashMap<String, String>> cursosCiclo;
    List<String> listDataHeaderCursos = new ArrayList<String>(){{add("Notas Ciclo Actual");}};
    List<String> listDataHeaderHist = new ArrayList<String>(){{add("Historial");}};
    List<String>listDataHeaderCred = new ArrayList<String>(){{add("Informacion General");}};
    HashMap<String, List<String>> listDataChildCursos = new HashMap<String, List<String>>();
    HashMap<String, List<String>> listDataChildHist = new HashMap<String, List<String>>();
    HashMap<String, List<String>> listDataChildCred = new HashMap<String, List<String>>();

    List<String> Cursos;
    List<String> Historial;
    List<String> Creditos;

    View rootView;
    JSONArray cursoActual = null;

    private static String INFO_URL = "http://gaTotesrcc.esy.es/Xyes/get_notas_alumno.php";
    private String ID_Alumno;
    String total,aprobado,jalado;

    // pruebas
    String[] itemS = new String[]{
        "Practica 1: ",
        "Practica 2: ",
        "Practica 3:",
        "Practica 4:",
        "Parcial:",
        "Final:"
    };

    boolean logeado=false;

    public static GradesFragment newInstance(String cd) {
        GradesFragment fragment = new GradesFragment(cd);
        return fragment;
    }
    public GradesFragment(String cadena) {
        this.ID_Alumno = cadena;
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        prepararDatos();
        if(logeado==false) {
            obtenerInfoAlumno();
            logeado=true;
        }

        AlertDialog.Builder builderNotas = new AlertDialog.Builder(getActivity());
        builderNotas.setTitle("Notas.");

        builderNotas.setItems(itemS, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                Log.i("Dialogos", "Opción elegida: " + itemS[item]);
            }
        }).setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });

        rootView = inflater.inflate(R.layout.notas_fragment, container, false);

        expListViewCursos = (ExpandableListView) rootView.findViewById(R.id.listViewCursos);
        expListViewHist = (ExpandableListView) rootView.findViewById(R.id.listViewHistorial);
        expListViewCred = (ExpandableListView) rootView.findViewById(R.id.listViewCreditos);
        listAdapterCursos = new MyExplanableListAdapter(getActivity(), listDataHeaderCursos, listDataChildCursos);
        listAdapterHist = new MyExplanableListAdapter(getActivity(), listDataHeaderHist, listDataChildHist);
        listAdapterCred = new MyExplanableListAdapter(getActivity(), listDataHeaderCred, listDataChildCred);
        expListViewCursos.setAdapter(listAdapterCursos);
        expListViewHist.setAdapter(listAdapterHist);
        expListViewCred.setAdapter(listAdapterCred);

        // Listview Group cuando se clickea al grupo
        expListViewCursos.setOnGroupClickListener(new OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
               // AlertDialog alert11 = builderNotas.create();
                //alert11.show();
                // Toast.makeText(getApplicationContext(),
                // "Group Clicked " + listDataHeader.get(groupPosition),
                // Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // Listview Group se expande
        expListViewCursos.setOnGroupExpandListener(new OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                //AlertDialog alert11 = builderNotas.create();
                //alert11.show();
                //Toast.makeText(getActivity(),listDataHeaderNot.get(groupPosition) + " Expanded",Toast.LENGTH_SHORT).show();
            }
        });

        // Listview Group se cierra
        expListViewCursos.setOnGroupCollapseListener(new OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                //Toast.makeText(getActivity(),listDataHeaderNot.get(groupPosition) + " Collapsed",Toast.LENGTH_SHORT).show();
            }
        });

        // Listview on child cuando clickea hijo
        expListViewCursos.setOnChildClickListener(new OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                //builderNotas.show();
                // TODO Auto-generated method stub
                //Toast.makeText(getActivity(),listDataHeaderNot.get(groupPosition) + " : "+ listDataChildNot.get(listDataHeaderNot.get(groupPosition)).get(childPosition), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        return rootView;
    }

    public void prepararDatos() {

        // Adding child data

        Historial = new ArrayList<String>();
        Historial.add("2015-1");
        Historial.add("2015-2");
        listDataChildHist.put(listDataHeaderHist.get(0), Historial);
    }

    public void obtenerInfoAlumno() {
        ConnexionTask.TaskListener listener = new ConnexionTask.TaskListener() {
            @Override
            public void onFinished(String result) {
                String json_str = String.valueOf(result);
                try {
                    // Reconoce la cadena de tipo json
                    JSONObject my_obj = new JSONObject(json_str);
                    Log.d("Informacion", String.valueOf(my_obj));
                    Cursos = new ArrayList<String>();
                    // JSON object array
                    cursoActual = my_obj.getJSONArray("cursos");
                    for (int i = 0; i < cursoActual.length(); i++) {
                        JSONObject c = cursoActual.getJSONObject(i);
                        String nombCru = c.getString("nombreCurso");
                        String pc1 = c.getString("practica_1");
                        String pc2 = c.getString("practica_2");
                        String pc3 = c.getString("practica_3");
                        String pc4 = c.getString("practica_4");
                        String parcial = c.getString("parcial");
                        String finall = c.getString("final");
                        //HashMap map = new HashMap();
                        Cursos.add(nombCru);
                        //map.put("practica_1", pc1);
                        //map.put("practica_2", pc2);
                        //map.put("practica_3", pc3);
                        //map.put("practica_4", pc4);
                        //map.put("parcial", parcial);
                        //map.put("final", finall);
                        //cursosCiclo.add(map);
                    }
                    listDataChildCursos.put(listDataHeaderCursos.get(0), Cursos);

                    aprobado = my_obj.getString("aprobados");
                    Log.d("Aprobado", aprobado);
                    jalado = my_obj.getString("jalado");
                    Log.d("jalado", jalado);
                    total = my_obj.getString("total");
                    Log.d("total", total);
                    Creditos = new ArrayList<String>();
                    Creditos.add("Creditos llevados: " + total);
                    Creditos.add("Creditos aprobados: "+aprobado);
                    Creditos.add("Creditos jalados: " + jalado);
                    listDataChildCred.put(listDataHeaderCred.get(0), Creditos);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        new ConnexionTask(getActivity(), INFO_URL, listener, 1).execute(ID_Alumno, null);
    }
}
