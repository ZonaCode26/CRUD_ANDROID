package com.cibertec.demo11;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Alex.ae on 28/06/2017.
 */

public class Activity_One extends AppCompatActivity {


    private Button btAgregar;
    private int REQUEST_CODE=1;
    private int REQUEST_UPDATE=2;


    private ListView lvMain;
    private LVMainAdapterListView mLVMainAdapter;
    private String codi;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);



        lvMain= (ListView) findViewById(R.id.lvPersona);


        btAgregar= (Button) findViewById(R.id.btAgregar);
        mLVMainAdapter= new LVMainAdapterListView(Activity_One.this);


        btAgregar.setOnClickListener(btAgregarOnClickListener);
        lvMain.setOnItemClickListener(lvMainOnItemClickListener);
        try {
            //INICIALIANDO EL DATABASE HELPER
            DataBaseHelper dataBaseHelper = new DataBaseHelper(Activity_One.this);
            //SI NO EXISTE LA BD EN EL MÓVIL LA COPIA DEL ASSETS SINO IGNORA EL PASO
            dataBaseHelper.createDataBase();

            //OBTENEMOS TODA LA TABLA PERSONA Y LA LISTAMOS EN CONSOLA
            List<Persona> lstPersona = new PersonaDAO(Activity_One.this).listPersonas();
            TextView tvDatabase = (TextView) findViewById(R.id.tvDatabase);

            for (Persona persona : lstPersona) {

                mLVMainAdapter.add(persona);
                lvMain.setAdapter(mLVMainAdapter);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //codigo a prueba
        lvMain.setAdapter(mLVMainAdapter);

    }

    public final AdapterView.OnItemClickListener lvMainOnItemClickListener= new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            Toast.makeText(Activity_One.this, "posiciÃ³n -> " +position,Toast.LENGTH_SHORT).show();

            if (parent == lvMain) {
                Persona obj;

                obj = (Persona) lvMain.getItemAtPosition(position);

                Intent intent = new Intent(Activity_One.this, Activity_Three.class);

                codi= obj.getId().toString();
                intent.putExtra("etNombre", obj.getNombre());
                intent.putExtra("etApellido",obj.getApellido());
                intent.putExtra("etDocumento",obj.getDocumento());
                intent.putExtra("etEdad", String.valueOf(obj.getEdad()));
                intent.putExtra("etTelefono",obj.getTelefono());
                startActivityForResult(intent,REQUEST_UPDATE);


            }


        }
    };


    public final View.OnClickListener btAgregarOnClickListener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent = new Intent(Activity_One.this,Activity_Three.class);
            startActivityForResult(intent,REQUEST_CODE);
        }
    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK){

            if(requestCode==REQUEST_CODE){

                Persona persona= new Persona();
                    persona.setNombre(data.getStringExtra("etNombre"));
                    persona.setApellido(data.getStringExtra("etApellido"));
                    persona.setDocumento(data.getStringExtra("etDocumento"));
                    persona.setEdad(Integer.valueOf(data.getStringExtra("etEdad")));
                    persona.setTelefono(data.getStringExtra("etTelefono"));

                    PersonaDAO personaDAO= new PersonaDAO(Activity_One.this);

                    personaDAO.insert(persona);

                Intent intent = new Intent(Activity_One.this,Activity_One.class);
                startActivity(intent);
                finish();

            }else
            if(requestCode==REQUEST_UPDATE){

                Persona persona= new Persona();
                persona.setId(codi);
                persona.setNombre(data.getStringExtra("etNombre"));
                persona.setApellido(data.getStringExtra("etApellido"));
                persona.setDocumento(data.getStringExtra("etDocumento"));
                persona.setEdad(Integer.valueOf(data.getStringExtra("etEdad")));
                persona.setTelefono(data.getStringExtra("etTelefono"));

                PersonaDAO personaDAO= new PersonaDAO(Activity_One.this);

                personaDAO.update(persona);

                Intent intent = new Intent(Activity_One.this,Activity_One.class);
                startActivity(intent);
                finish();


            }

        }else if(resultCode==10){

            Persona persona= new Persona();
            persona.setId(codi);
            persona.setNombre(data.getStringExtra("etNombre"));
            persona.setApellido(data.getStringExtra("etApellido"));
            persona.setDocumento(data.getStringExtra("etDocumento"));
            persona.setEdad(Integer.valueOf(data.getStringExtra("etEdad")));
            persona.setTelefono(data.getStringExtra("etTelefono"));

            PersonaDAO personaDAO= new PersonaDAO(Activity_One.this);

            personaDAO.delete(persona);
            Intent intent = new Intent(Activity_One.this,Activity_One.class);
            startActivity(intent);
            finish();

        }





    }
}
