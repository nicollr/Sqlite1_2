package com.example.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {
    String slqcreate= "CREATE TABLE ALUMNOS (no_control INTEGER, nombre TEXT, aPaterno TEXT)";

    public DataBaseHelper (Context context, String nombre, SQLiteDatabase.CursorFactory factory, int version){
        super(context,nombre,factory,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(slqcreate);
    }

    public void onUpgrade(SQLiteDatabase db,int versionAnterior, int versionNueva){
        db.execSQL(("DROP TABLE IF EXISTS Alumnos"));

        db.execSQL(slqcreate);
    }

}
