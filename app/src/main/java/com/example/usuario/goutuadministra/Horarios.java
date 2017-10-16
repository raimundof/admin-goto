package com.example.usuario.goutuadministra;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

// TODO: Horarios
public class Horarios extends Fragment {

    TextView tvHora;
    Button btnPonerHora;

    private  int horas,minutos ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Horarios");
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_horarios, container, false);

        tvHora=(TextView)view.findViewById(R.id.txthora);

        btnPonerHora=(Button)view.findViewById(R.id.btnHora);

        btnPonerHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar c = Calendar.getInstance();
                horas=c.get(Calendar.HOUR_OF_DAY);
                minutos=c.get(Calendar.MINUTE);

                TimePickerDialog ponerHora = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        btnPonerHora.setText(hourOfDay+":"+minute);
                    }
                },horas,minutos,false);
                ponerHora.show();
            }
        });


        return  view;

    }

}
