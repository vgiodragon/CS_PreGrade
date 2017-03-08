package com.example.user.proyectomoviles.Fragment;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.proyectomoviles.R;


public class NotasFragment extends Fragment{

    public static NotasFragment newInstance() {
        NotasFragment fragment = new NotasFragment();
        return fragment;
    }
    public NotasFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        //return inflater.inflate(R.layout.notas_fragment, container, false);
        return null;
    }
}
