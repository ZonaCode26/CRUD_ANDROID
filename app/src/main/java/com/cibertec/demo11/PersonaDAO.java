package com.cibertec.demo11;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by plrios on 17/06/2017.
 */

public class PersonaDAO {
     private final String TABLE = "Persona";
    private final String COL_ID = "id";
    private final String COL_NOMBRE = "nombre";
    private final String COL_APELLIDO = "apellido";
    private final String COL_EDAD = "edad";
    private final String COL_DOCUMENTO = "documento";
    private final String COL_TELEFONO = "telefono";
    private final String COL_DISPONIBLE = "disponible";
    private Context mContext;

    public PersonaDAO(Context context) {
        mContext = context;
    }

    public Persona getFirstPersona() {
        Persona persona = null;
        try {
            DataBaseHelper dataBaseHelper = new DataBaseHelper(mContext);
            SQLiteDatabase sqLiteDatabase = dataBaseHelper.openDataBase();
            Cursor cursor = sqLiteDatabase.query(TABLE, null, null, null, null, null, null, "1");
            if (cursor.moveToFirst())
                persona = transformarCursorAPersona(cursor);

            //DISTINCT (boolean) = true, false o ignorarlo
            //TABLE (string) = el nombre de la tabla, no puede ser null
            //COLUMNS (arreglo string) = las columnas que quieres devolver (el select). Si es null trae toda las columnas de la tabla
            //SELECTION (string) = es el where del query. Si no quieres un where se ingresa null
            //SELECTIONARGS (arreglo string) = son los parámetros (valores) del where. Si no pasas parámetros se ingresa null
            //GROUP BY (string) = columnas separadas por comas para agruparlas. Si no se agrupa se ingresa null
            //Having (string) = Si no se hace un having se ingresa null
            //Order BY (string) = columnas separadas por comas para agruparlas. Si no se quiere ordernar personalizado se ingresa null
            //LIMIT (string) = número de registros a devolver. Si no quieres número máximo de registros se ingresa null

            //EJEMPLO
            //COLUMNS = new String[]{"nombre","apellido", "disponible"}
            //SELECTION = "nombre = ? and edad > ?"
            //SELECTIONARGS = new String[]{"luis alonso","28"}
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return persona;
    }

    public Persona getPersonaById(int id) {
        Persona persona = null;
        try {
            DataBaseHelper dataBaseHelper = new DataBaseHelper(mContext);
            SQLiteDatabase sqLiteDatabase = dataBaseHelper.openDataBase();
            Cursor cursor = sqLiteDatabase.query(TABLE, null, "id = ?", new String[]{String.valueOf(id)}, null, null, null);
            if (cursor.moveToFirst())
                persona = transformarCursorAPersona(cursor);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return persona;
    }

    public List<Persona> listPersonas() {
        List<Persona> lstPersona = new ArrayList<>();
        try {
            DataBaseHelper dataBaseHelper = new DataBaseHelper(mContext);
            SQLiteDatabase sqLiteDatabase = dataBaseHelper.openDataBase();
            Cursor cursor = sqLiteDatabase.query(TABLE, null, null, null, null, null, null);
            if (cursor.moveToFirst()) {
                do {
                    lstPersona.add(transformarCursorAPersona(cursor));
                } while (cursor.moveToNext());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return lstPersona;
    }

    public long insert(Persona persona) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NOMBRE, persona.getNombre());
        contentValues.put(COL_APELLIDO, persona.getApellido());
        contentValues.put(COL_EDAD, persona.getEdad());
        contentValues.put(COL_DOCUMENTO, persona.getDocumento());
        contentValues.put(COL_TELEFONO, persona.getTelefono());
        contentValues.put(COL_DISPONIBLE, persona.isDisponible() ? 1 : 0);

        return new DataBaseHelper(mContext)
                .openDataBase()
                .insert(TABLE, null, contentValues);
    }

    public void update(Persona persona) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NOMBRE, persona.getNombre());
        contentValues.put(COL_APELLIDO, persona.getApellido());
        contentValues.put(COL_EDAD, persona.getEdad());
        contentValues.put(COL_DOCUMENTO, persona.getDocumento());
        contentValues.put(COL_TELEFONO, persona.getTelefono());
        contentValues.put(COL_DISPONIBLE, persona.isDisponible() ? 1 : 0);

        new DataBaseHelper(mContext)
                .openDataBase()
                .update(TABLE, contentValues, COL_ID + " = ?", new String[]{String.valueOf(persona.getId())});
    }

    public void delete(Persona persona) {
        new DataBaseHelper(mContext)
                .openDataBase()
                .delete(TABLE, COL_ID + " = ?", new String[]{String.valueOf(persona.getId())});
    }

    private Persona transformarCursorAPersona(Cursor cursor) {
        //DECLARO E INICIALIZO LA ENTIDAD PERSONA
        Persona persona = new Persona();

        //VALOR DE VERDAD ? VERDADERO : FALSO

        //VERIFICO SI LA COLUMNA ES NULA. SI ES NULA DEVUELVO VACIÓ CASO CONTRARIO DEVUELVO. LA DATA DE LA COLUMNA
        persona.setId(cursor.isNull(cursor.getColumnIndex(COL_ID)) ? "" : cursor.getString(cursor.getColumnIndex(COL_ID)));

        persona.setNombre(cursor.isNull(cursor.getColumnIndex(COL_NOMBRE)) ? "" : cursor.getString(cursor.getColumnIndex(COL_NOMBRE)));
        persona.setApellido(cursor.isNull(cursor.getColumnIndex(COL_APELLIDO)) ? "" : cursor.getString(cursor.getColumnIndex(COL_APELLIDO)));
        persona.setEdad(cursor.isNull(cursor.getColumnIndex(COL_EDAD)) ? 0 : cursor.getInt(cursor.getColumnIndex(COL_EDAD)));
        persona.setTelefono(cursor.isNull(cursor.getColumnIndex(COL_TELEFONO)) ? "" : cursor.getString(cursor.getColumnIndex(COL_TELEFONO)));
        persona.setDocumento(cursor.isNull(cursor.getColumnIndex(COL_DOCUMENTO)) ? "" : cursor.getString(cursor.getColumnIndex(COL_DOCUMENTO)));
        persona.setDisponible(cursor.isNull(cursor.getColumnIndex(COL_DISPONIBLE)) ? false : cursor.getInt(cursor.getColumnIndex(COL_DISPONIBLE)) > 0);
        return persona;
    }
}
