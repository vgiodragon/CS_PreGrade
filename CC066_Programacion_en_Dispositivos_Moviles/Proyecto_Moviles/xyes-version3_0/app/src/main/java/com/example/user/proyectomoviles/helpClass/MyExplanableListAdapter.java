package com.example.user.proyectomoviles.helpClass;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;
import android.app.FragmentManager;

import com.example.user.proyectomoviles.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyExplanableListAdapter extends BaseExpandableListAdapter {

    private Context _context;
    public Activity _activity;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<String>> _listDataChild;

    public MyExplanableListAdapter(Activity act, List<String> listDataHeader, HashMap<String, List<String>> listChildData) {
        this._activity = act;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item, null);
        }

        TextView txtListChild = (TextView) convertView.findViewById(R.id.lblListItem);

        txtListChild.setText(childText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._activity
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, null);
        }

        TextView lblListHeader = (TextView) convertView.findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
    /*
    private final SparseArray<GroupItems> grupos;
    public LayoutInflater inflater;
    public Activity activity;
    String tittle;
    int pulse;
    String tipo="predeter";

    private ArrayList<ArrayList<String>> dataComplex =new ArrayList<ArrayList<String>>();
    private ArrayList<String> data = new ArrayList<String>();
    // Constructor
    public Adapter(Activity act, SparseArray<GroupItems> grupos, String tit, int pul) {
        activity = act;
        this.grupos = grupos;
        this.pulse = pul;
        this.tittle = tit;
        inflater = act.getLayoutInflater();
    }

    public Adapter(Activity act, SparseArray<GroupItems> grupos, ArrayList<String> st, String tit, int pul) {
        activity = act;
        this.grupos = grupos;
        this.pulse = pul;
        this.tittle = tit;
        for (int i=0;i<st.size();i++) {
            this.data.add(st.get(i));
        }
        inflater = act.getLayoutInflater();
    }

    public Adapter(Activity act, SparseArray<GroupItems> grupos, ArrayList<ArrayList<String>> st, String tit, int pul, String tip) {
        activity = act;
        this.grupos = grupos;
        this.pulse = pul;
        this.tittle = tit;
        for (int i=0;i<st.size();i++) {
            this.dataComplex.add(st.get(i));
        }
        inflater = act.getLayoutInflater();
        this.tipo = tip;
    }

    // Nos devuelve los datos asociados a un subitem en base
    // a la posición
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return grupos.get(groupPosition).children.get(childPosition);
    }
    // Devuelve el id de un item o subitem en base a la
    // posición de item y subitem
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }
    // En base a la posición del item y de subitem nos devuelve
    // el objeto view correspondiente y el layout para los subitems
    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String children = (String) getChild(groupPosition, childPosition);
        TextView textvw = null;

        // Aqui decidimos si al pulsar en el texo muestra menu o no
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.lists_item, null);
        }
        textvw = (TextView) convertView.findViewById(R.id.textItemsGroup);
        textvw.setText(children);
        if(pulse==1){
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentManager fragmentManager = activity.getFragmentManager();
                    if(tipo.equals("predeter")) {
                        DialogSelection dialogo = new DialogSelection(data, tittle);
                        dialogo.show(fragmentManager, "tagAlerta");
                    } else if (tipo.equals("complex")){
                        DialogSelection dialogo = new DialogSelection(dataComplex, tittle,"complex");
                        dialogo.show(fragmentManager, "tagAlerta");
                    }
                    //Toast.makeText(activity, children, Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            // nothing
        }
        return convertView;
    }
    // Nos devuelve la cantidad de subitems que tiene un ítem
    @Override
    public int getChildrenCount(int groupPosition) {
        return grupos.get(groupPosition).children.size();
    }
    //Los datos de un ítem especificado por groupPosition
    @Override
    public Object getGroup(int groupPosition) {
        return grupos.get(groupPosition);
    }
    //La cantidad de ítem que tenemos definidos
    @Override
    public int getGroupCount() {
        return grupos.size();
    }
    //Método que se invoca al contraer un ítem
    @Override
    public void onGroupCollapsed(int groupPosition) {
        super.onGroupCollapsed(groupPosition);
    }
    //Método que se invoca al expandir un ítem
    @Override
    public void onGroupExpanded(int groupPosition) {
        super.onGroupExpanded(groupPosition);
    }
    //Devuelve el id de un ítem
    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }
    //Obtenemos el layout para los ítems
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.group_item, null);
        }
        GroupItems grupo = (GroupItems) getGroup(groupPosition);
        ((CheckedTextView) convertView).setText(grupo.string);
        ((CheckedTextView) convertView).setChecked(isExpanded);
        return convertView;
    }
    @Override
    public boolean hasStableIds() {
        return false;
    }
    //Nos informa si es seleccionable o no un ítem o subitem
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
*/