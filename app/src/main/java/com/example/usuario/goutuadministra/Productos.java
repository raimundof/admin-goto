package com.example.usuario.goutuadministra;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class Productos extends Fragment {

    private RecyclerView recyclerView;
    private Productos_Adapter adapter;
    private List<Productos_Dato> albumList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    // TODO: Productos
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Mis Productos");
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_productos, container, false);

        View view = inflater.inflate(R.layout.fragment_productos, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        albumList = new ArrayList<>();
        adapter = new Productos_Adapter( albumList, getContext());

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        getListaProductos();
        return view;
    }


    private void getListaProductos()
    {
        for (int i = 0; i<20; i++) {
            albumList.add(new Productos_Dato ("Producto1", 1 ,"https://www.asuaire.com/wp-content/uploads/2015/09/26-300x300.png" ));
        }

        adapter = new Productos_Adapter(albumList , getContext());
        recyclerView.setAdapter(adapter);
    }



}
