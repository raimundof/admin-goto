package com.example.usuario.goutuadministra;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Empresa extends Fragment {
    private RecyclerView listaEmpresa = null;
    private Empresa_Adapter adapter = null;
    private List<Empresa_Dato> arrayEmpresa = null;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
    // TODO: Empresa
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Mi Empresa");


        View view = inflater.inflate(R.layout.fragment_empresa, container, false);
        if (view == null) {
            view = inflater.inflate(R.layout.lista_item_empresa, container, false);
        }


        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) view.findViewById(R.id.collapsingToolbar);
        collapsingToolbar.setTitle("Goutu");

        listaEmpresa = (RecyclerView) view.findViewById(R.id.recyclerView);
        listaEmpresa.setHasFixedSize(true);
        listaEmpresa.setLayoutManager(new LinearLayoutManager(getContext()));

        arrayEmpresa = new ArrayList<>();
        getListaEmpresa();
       return view;
    }


    private void getListaEmpresa()
    {
        for (int i = 0; i<1; i++) {
            arrayEmpresa.add(new Empresa_Dato ("http://lorempixel.com/output/food-q-c-640-480-"+ (i + 2)+".jpg", "Nombre de la empresa  "+ (i + 1),"Descripcion de Empresa restaurantes " + (i + 1)));
        }
        adapter = new Empresa_Adapter( arrayEmpresa , getContext());
        listaEmpresa.setAdapter(adapter);
    }


}
