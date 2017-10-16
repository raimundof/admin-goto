package com.example.usuario.goutuadministra;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.Console;
import java.util.List;

/**
 * Created by usuario on 16/09/2017.
 */

class SpinnerAdapter extends BaseAdapter {

    List <Categoria> values;
    Context context;
    public SpinnerAdapter(Context context, List<Categoria> values) {
        this.context = context;
        this.values = values;
    }

    @Override
    public int getCount() {
        return values.size();
    }

    @Override
    public Object getItem(int position) {
        return values.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int pos, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.lista_lead_categoria_spinner, null);
       // LinearLayout In = (LinearLayout) view.findViewById(R.id.idLinearSpinner);
        TextView txtTam = (TextView) view.findViewById(R.id.txtCategoria);
        txtTam.setText(values.get(pos).getNombre_categoria());

        return view;
    }
}
