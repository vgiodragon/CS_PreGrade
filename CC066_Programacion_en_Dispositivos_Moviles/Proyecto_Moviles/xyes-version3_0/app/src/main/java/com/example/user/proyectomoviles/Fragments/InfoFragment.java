package com.example.user.proyectomoviles.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.example.user.proyectomoviles.EditInfo;
import com.example.user.proyectomoviles.hostRequest.ConnexionTask;
import com.example.user.proyectomoviles.R;

import org.json.JSONException;
import org.json.JSONObject;

public class InfoFragment extends Fragment {

	private static String INFO_URL = "http://gaTotesrcc.esy.es/Xyes/get_info_alumno.php";
	private String ID_Alumno;
	View rootView;
	String cadenaBoton="Guardar";
	String cadenaBoton1="Cancelar";

	public static InfoFragment newInstance(String cd) {
		InfoFragment fragment = new InfoFragment(cd);
		return fragment;
	}

	public InfoFragment(String cadena) {
		this.ID_Alumno = cadena;
	// Required empty public constructor
	}
/*	private ArrayList<String> informacionAlumno;
	public static InfoFragment newInstanceArray(ArrayList<String> mensaje) {
		InfoFragment fragment = new InfoFragment(mensaje);
		return fragment;
	}
	public InfoFragment(ArrayList<String> mensaje)
	{
		this.informacionAlumno = new ArrayList<String>();
		for (int i=0;i<mensaje.size();i++) {
			this.informacionAlumno.add(mensaje.get(i));
		}
		// Required empty public constructor
	}*/

	private TextView nombres, apellidoP,apellidoM,codigo,ciclo,facultad,especialidad,cel,mail;
	String nombre, apellP, apellM, cod, cicl, facu, esp, cll, ml;
	private Button btnModificar;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.info_fragment, container, false);
		nombres = (TextView) rootView.findViewById(R.id.textNombre1);
		apellidoP = (TextView) rootView.findViewById(R.id.textApellidoP1);
		apellidoM = (TextView) rootView.findViewById(R.id.textApellidoM1);
		codigo = (TextView) rootView.findViewById(R.id.textCodigo1);
		ciclo = (TextView) rootView.findViewById(R.id.textCiclo1);
		facultad = (TextView) rootView.findViewById(R.id.textFacultad1);
		especialidad = (TextView) rootView.findViewById(R.id.textEsp1);
		cel = (TextView) rootView.findViewById(R.id.textCel1);
		mail = (TextView) rootView.findViewById(R.id.textEmail1);
		/*btnModificar = (Button) rootView.findViewById(R.id.btnModificar);
		btnModificar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				modificar(v);
			}
		});*/

		obtenerInfoAlumno();
		//modificarValores();

		return rootView;
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

					// lena la info
					nombre = my_obj.getString("nombres");
					Log.d("Mail-Nombre", nombre);
					apellP = my_obj.getString("apellidoP");
					apellM = my_obj.getString("apellidoM");
					cod = my_obj.getString("codigo");
					cicl = my_obj.getString("ciclo");
					facu = my_obj.getString("facultad");
					esp = my_obj.getString("especialidad");
					cll = my_obj.getString("cel");
					ml = my_obj.getString("mail");
					Log.d("Mail-Recibido", ml);
					nombres.setText(nombre);
					apellidoP.setText(apellP);
					apellidoM.setText(apellM);
					codigo.setText(cod);
					ciclo.setText(cicl);
					facultad.setText(facu);
					especialidad.setText(esp);
					cel.setText(cll);
					mail.setText(ml);

				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		};
		new ConnexionTask(getActivity(),INFO_URL,listener,1).execute(ID_Alumno, null);
	}
/*
	public void modificarValores(){
		String cadenaB = getActivity().getIntent().getStringExtra("botonApretado");
		if (cadenaBoton.equals(cadenaB)){
			Toast t = Toast.makeText(getActivity(),"Se guardo correctamente",Toast.LENGTH_SHORT);
			t.show();
			cel.setText(getActivity().getIntent().getStringExtra("Cel"));
			mail.setText(getActivity().getIntent().getStringExtra("Mail"));
		}
		if(cadenaBoton1.equals(cadenaB)){
			Toast t = Toast.makeText(getActivity(),"No se modifico ningun dato",Toast.LENGTH_SHORT);
			t.show();
			cel.setText(getActivity().getIntent().getStringExtra("Cel"));
			mail.setText(getActivity().getIntent().getStringExtra("Mail"));
		}
	}

	public void modificar(View view){
		Intent intent = new Intent(getActivity(), EditInfo.class);
		intent.putExtra("Nombre1",nombres.getText().toString());
		intent.putExtra("ApellidoP1",apellidoP.getText().toString());
		intent.putExtra("ApellidoM1",apellidoM.getText().toString());
		intent.putExtra("Codigo1",codigo.getText().toString());
		intent.putExtra("Ciclo1",ciclo.getText().toString());
		intent.putExtra("Facultad1",facultad.getText().toString());
		intent.putExtra("Especialidad1",especialidad.getText().toString());
		intent.putExtra("Cel1",cel.getText().toString());
		intent.putExtra("Mail1", mail.getText().toString());
		startActivity(intent);
		getActivity().finish();
	}
*/
}
