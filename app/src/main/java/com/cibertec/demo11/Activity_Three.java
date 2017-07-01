package com.cibertec.demo11;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Alex.ae on 29/06/2017.
 */

public class Activity_Three extends AppCompatActivity{


    private int RESULT_DELETE=10;
        Button btAgregar,btActualizar,btEliminar;
        EditText etNombre,etApellido,etDocumento,etEdad,etTelefono;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);

        etNombre= (EditText) findViewById(R.id.etNombre);
        etApellido= (EditText) findViewById(R.id.etApellido);
        etDocumento= (EditText) findViewById(R.id.etDocumento);
        etEdad= (EditText) findViewById(R.id.etEdad);
        etTelefono= (EditText) findViewById(R.id.etTelefono);


        Intent intent= getIntent();


        etNombre.setText(intent.getStringExtra("etNombre"));
        etApellido.setText(intent.getStringExtra("etApellido"));
        etDocumento.setText(intent.getStringExtra("etDocumento"));
        etEdad.setText(intent.getStringExtra("etEdad"));
        etTelefono.setText(intent.getStringExtra("etTelefono"));



        btActualizar= (Button) findViewById(R.id.btActualizar);
        btAgregar= (Button) findViewById(R.id.btAgregar);
        btEliminar= (Button) findViewById(R.id.btEliminar);

        btAgregar.setOnClickListener(btAgregarOnClickListener);
        btActualizar.setOnClickListener(btActualizarOnClickListener);
        btEliminar.setOnClickListener(btEliminarOnClickListener);
        if(intent!=null){
            btAgregar.setEnabled(true);
        }

    }
    public final View.OnClickListener btEliminarOnClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            Intent intent = new Intent();

            intent.putExtra("etNombre",etNombre.getText().toString());
            intent.putExtra("etApellido",etApellido.getText().toString());
            intent.putExtra("etDocumento",etDocumento.getText().toString());
            intent.putExtra("etEdad", etEdad.getText().toString());
            intent.putExtra("etTelefono",etTelefono.getText().toString());
            setResult(RESULT_DELETE,intent);
            finish();
        }
    };

    public final View.OnClickListener btActualizarOnClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent = new Intent();


            intent.putExtra("etNombre",etNombre.getText().toString());
            intent.putExtra("etApellido",etApellido.getText().toString());
            intent.putExtra("etDocumento",etDocumento.getText().toString());
            intent.putExtra("etEdad", etEdad.getText().toString());
            intent.putExtra("etTelefono",etTelefono.getText().toString());
            setResult(RESULT_OK,intent);

            finish();


        }
    };

    public final View.OnClickListener btAgregarOnClickListener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();

            intent.putExtra("etNombre",etNombre.getText().toString());
            intent.putExtra("etApellido",etApellido.getText().toString());
            intent.putExtra("etDocumento",etDocumento.getText().toString());
            intent.putExtra("etEdad", etEdad.getText().toString());
            intent.putExtra("etTelefono",etTelefono.getText().toString());
            setResult(RESULT_OK,intent);

            finish();

        }
    };



}
