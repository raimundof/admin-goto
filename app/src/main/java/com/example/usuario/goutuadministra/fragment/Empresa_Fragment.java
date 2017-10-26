package com.example.usuario.goutuadministra.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
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

import com.example.usuario.goutuadministra.Editar_Empresa;
import com.example.usuario.goutuadministra.R;

import java.util.ArrayList;
import java.util.List;

public class Empresa_Fragment extends Fragment {

    ImageView imagen;
    FloatingActionButton fab;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
    // TODO: Empresa_Fragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Mi Empresa");

        View view = inflater.inflate(R.layout.fragment_empresa, container, false);

        fab = (FloatingActionButton) view.findViewById(R.id.floEditarEmpresa);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Editar_Empresa.class);
                startActivity(intent);
            }
        });

       return view;
    }

}
