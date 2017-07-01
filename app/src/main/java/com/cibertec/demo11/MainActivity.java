package com.cibertec.demo11;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final View.OnClickListener btGetOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
            String preference = sharedPreferences.getString("PRUEBA2", "No  Existe");
            tvPreferences.setText(preference);
        }
    };
    private final View.OnClickListener btSaveOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
            sharedPreferences
                    .edit()
                    .putString("PRUEBA2", etPreferences.getText().toString().trim())
                    .commit();
        }
    };

    private EditText etPreferences;
    //    private Button btGet, btSave;
    private TextView tvPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //region Example
        //CREA U OBTIENE EL ARCHIVO DE PREFERENCIAS POR DEFECTO
        SharedPreferences prefernceManager = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        //CREA U OBTIENE EL ARCHIVO DE PREFERENCIAS CON EL NOMBRE INDICADO
        SharedPreferences sharedPreferences = getSharedPreferences("ABC", MODE_PRIVATE);

        //LECTURA
        String nombre = sharedPreferences.getString("NOMBRE", null);
        String nombreOther = prefernceManager.getString("NOMBRE", null);
        //ESCRITURA
        sharedPreferences
                .edit()
                .putString("APELLIDO", "RIOS")
                .commit();

        prefernceManager
                .edit()
                .putString("APELLIDO", "RIOS")
                .commit();

        //endregion

        etPreferences = (EditText) findViewById(R.id.etPreferences);
        tvPreferences = (TextView) findViewById(R.id.tvPreferences);

        findViewById(R.id.btGet).setOnClickListener(btGetOnClickListener);

        findViewById(R.id.btSave).setOnClickListener(btSaveOnClickListener);

        try {
            //INICIALIANDO EL DATABASE HELPER
            DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);
            //SI NO EXISTE LA BD EN EL MÓVIL LA COPIA DEL ASSETS SINO IGNORA EL PASO
            dataBaseHelper.createDataBase();

            //OBTENEMOS TODA LA TABLA PERSONA Y LA LISTAMOS EN CONSOLA
            List<Persona> lstPersona = new PersonaDAO(MainActivity.this).listPersonas();
            TextView tvDatabase = (TextView) findViewById(R.id.tvDatabase);
            for (Persona persona : lstPersona) {
                Log.d("PERSONA", persona.getNombre() + " " + persona.getApellido());
                tvDatabase.setText(tvDatabase.getText().toString() + persona.getNombre() + " " + persona.getApellido() + "\n");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
