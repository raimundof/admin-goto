package com.example.usuario.goutuadministra.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.usuario.goutuadministra.R;

public class Usuario_Fragment extends Fragment {

    // TODO: Usuario_Fragment
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Usuario");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_usuario, container, false);
    }
}
