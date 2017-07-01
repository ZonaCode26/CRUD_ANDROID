package com.cibertec.demo11;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex.ae on 28/06/2017.
 */

public class LVMainAdapterListView extends ArrayAdapter<Persona>{


    public LVMainAdapterListView(Context context) {
        super(context, 0,new ArrayList<Persona>());
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null)
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.activity_two,parent,false);

            TextView tvTwoItemFull,tvTwoItemDato,tvTwoInfo;

        tvTwoItemFull= (TextView) convertView.findViewById(R.id.tvTwoItemFull);

         tvTwoItemDato= (TextView) convertView.findViewById(R.id.tvTwoItemDato);
        tvTwoInfo= (TextView) convertView.findViewById(R.id.tvTwoInfo);

                Persona persona= getItem(position);

                     tvTwoItemFull.setText(persona.getNombre()+" "+persona.getApellido());
                    tvTwoItemDato.setText(persona.getTelefono()+" "+persona.getDocumento());
                    tvTwoInfo.setText( String.valueOf(persona.getEdad()));

        return convertView;
    }
}
