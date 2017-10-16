package com.example.usuario.goutuadministra;

/**
 * Created by usuario on 27/08/2017.
 */

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.PopupMenu;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.fragment;
import static android.R.attr.onClick;
import static com.example.usuario.goutuadministra.R.drawable.imagen;

public class Empresa_Adapter extends RecyclerView.Adapter<Empresa_Adapter.ViewHolder> {
    private List<Empresa_Dato> listItems;
    private Context mContext;
    public Empresa_Adapter(List<Empresa_Dato> listItems, Context mContext) {
        this.listItems = listItems;
        this.mContext = mContext;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_lead_empresa, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final Empresa_Dato itemList = listItems.get(position);
        holder.txtNombreEmpresa.setText(itemList.getNombreEmpresa());

        Glide.with(mContext.getApplicationContext())
                .load(listItems.get(position).getImagen()).fitCenter().error(R.drawable.error)
                .into(holder.imgImagenEmpresa);
        holder.txtDescripcionEmpresa.setText(itemList.getDescripcionEmpresa());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "iTEM " + position, Toast.LENGTH_SHORT).show();

                FragmentManager fragmentManager = ((AppCompatActivity)mContext).getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.contenedor, new Productos())
                        .addToBackStack(null)
                        .commit();
            }
        });


        holder.txtOptionDigit.setOnClickListener(new View.OnClickListener() {
            Fragment fragment = null;

            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(mContext, holder.txtOptionDigit);
                popupMenu.inflate(R.menu.main_empresa);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {


                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_ver_productos:
                                   FragmentManager fragmentManager = ((AppCompatActivity)mContext).getSupportFragmentManager();
                                    fragmentManager.beginTransaction()
                                            .replace(R.id.contenedor, new Productos())
                                            .addToBackStack(null)
                                            .commit();

                                break;
                            case R.id.action_editar_empresa:
//                                listItems.remove(position);
//                                notifyDataSetChanged();
                                Toast.makeText(mContext, "Editar empresa", Toast.LENGTH_LONG).show();

                                Intent intent = new Intent (mContext, Agregar_Empresa.class);
                                mContext.startActivity(intent);
                                break;
                            default:
                                break;
                        }
                        return false;
                    }

                });
                popupMenu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtNombreEmpresa;
        public ImageView imgImagenEmpresa;
        public TextView txtDescripcionEmpresa;
        public TextView txtOptionDigit;

        public ViewHolder(View itemView) {
            super(itemView);
            txtNombreEmpresa = (TextView) itemView.findViewById(R.id.nombreEmpresa);
            imgImagenEmpresa = (ImageView) itemView.findViewById(R.id.imagenEmpresa);
            txtDescripcionEmpresa = (TextView) itemView.findViewById(R.id.descripcionEmpresa);
            txtOptionDigit = (TextView) itemView.findViewById(R.id.txtOptionDigit);
        }
    }

}
