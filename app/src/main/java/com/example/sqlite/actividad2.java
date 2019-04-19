package com.example.sqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class actividad2 extends AppCompatActivity {

    private Button btninsertar, btnSelect, btnDelete, btnUpdate;
    private EditText txtNControl, txtNombre, txtAPaterno;
    private String nombre, apellido;


    boolean NO = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad2);
        btninsertar = findViewById(R.id.insert);
        btnSelect = findViewById(R.id.select);
        btnDelete = findViewById(R.id.delete);
        btnUpdate = findViewById(R.id.update);
        txtNControl = findViewById(R.id.edtNControl);
        txtNombre = findViewById(R.id.edtNombre);
        txtAPaterno = findViewById(R.id.edtAPaterno);


        btninsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nombre = txtNombre.getText().toString();

                apellido = txtAPaterno.getText().toString();

                DataBaseHelper helper = new DataBaseHelper(actividad2.this, "Alumnos", null, 1);

                SQLiteDatabase db = helper.getWritableDatabase();
                if(COMP())
                {
                    Toast.makeText(getApplicationContext(), "LLENE TODOS LOS CAMPOS", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if (db != null) {

                        db.execSQL("INSERT INTO ALUMNOS(no_control, nombre, aPaterno ) values(" + txtNControl.getText().toString()+ ",'" + nombre + "','" + apellido + "')");

                        Toast.makeText(actividad2.this, "REGISTRO GUARDADO", Toast.LENGTH_SHORT).show();

                        db.close();


                    }
                }



            }
        });

        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String registros = "";
                DataBaseHelper helper = new DataBaseHelper(actividad2.this, "Alumnos", null, 1);
                SQLiteDatabase db = helper.getWritableDatabase();
                if (db != null) {
                    Cursor cursor = db.rawQuery("SELECT * FROM ALUMNOS", null);
                    if (cursor.moveToFirst()) {
                        do {
                            registros += cursor.getInt(cursor.getColumnIndex("no_control"));
                            registros += " ";
                            registros += cursor.getString(cursor.getColumnIndex("nombre"));
                            registros += " ";
                            registros += cursor.getString(cursor.getColumnIndex("aPaterno"));
                            registros += "\n";
                        } while (cursor.moveToNext());
                    }
                    if (registros.equals("")) {
                        Toast.makeText(actividad2.this, "NO HAY REGISTROS", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(actividad2.this, registros, Toast.LENGTH_SHORT).show();

                    db.close();

                }
            }
        });


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

        DataBaseHelper helper = new DataBaseHelper(actividad2.this, "Alumnos", null, 1);
        SQLiteDatabase db = helper.getWritableDatabase();

                    //SELECT2();
                if(COMP2())
                {
                    Toast.makeText(getApplicationContext(), "INGRESE NUM DE CONTROL", Toast.LENGTH_SHORT).show();
                }
                else {
                        SELECT2();
                    if (db != null) {
                        if(NO == false) {
                            db.execSQL("DELETE FROM  ALUMNOS WHERE no_control=" + txtNControl.getText().toString());

                            Toast.makeText(actividad2.this, "REGISTRO ELIMINADO", Toast.LENGTH_SHORT).show();
                        }
                        else if(NO)
                        {
                            Toast.makeText(getApplicationContext(), "NO HAY REGISTRO CON ESE NUM DE CONTROL", Toast.LENGTH_SHORT).show();
                        }

                        db.close();

                    }

                }
    }
});


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nombre = txtNombre.getText().toString();
                apellido = txtAPaterno.getText().toString();

                DataBaseHelper helper = new DataBaseHelper(actividad2.this, "Alumnos", null, 1);
                SQLiteDatabase db = helper.getWritableDatabase();

                if(COMP2())
                {
                    Toast.makeText(getApplicationContext(), "INGRESE NUM DE CONTROL", Toast.LENGTH_SHORT).show();
                }
                else {

                    SELECT2();
                    if (db != null) {
                        if(NO==false) {
                            db.execSQL("UPDATE ALUMNOS  set nombre='" + nombre + "',aPaterno='" + apellido + "' WHERE no_control=" + txtNControl.getText().toString());
                            Toast.makeText(actividad2.this, "REGISTRO ACTUALIZADO", Toast.LENGTH_SHORT).show();
                        }
                        else if(NO) {

                            Toast.makeText(actividad2.this, "NO HAY REGISTRO CON ESE NUM DE CONTROL", Toast.LENGTH_SHORT).show();
                        }

                            db.close();

                    }
                }
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }

    public boolean COMP2() {
        boolean bool = false;

        if (txtNControl.getText().length()==0 ) {
            //  Toast.makeText(getApplicationContext(), "FAVOR DE LLENAR TODS LOS CAMPOS", Toast.LENGTH_SHORT).show();
            bool=true;
        }
        else
        {
            bool=false;
        }
        return bool;

    }
    public boolean COMP() {
        boolean bool = false;

        if (txtNControl.getText().length()==0 || nombre.equals("") || apellido.equals("")) {
          //  Toast.makeText(getApplicationContext(), "FAVOR DE LLENAR TODS LOS CAMPOS", Toast.LENGTH_SHORT).show();
           bool=true;
        }
       else
        {
            bool=false;
        }
        return bool;

    }
    public void SELECT2()
    {
        String registros = "";
        DataBaseHelper helper = new DataBaseHelper(actividad2.this, "Alumnos", null, 1);
        SQLiteDatabase db = helper.getWritableDatabase();
        if (db != null) {
            Cursor cursor = db.rawQuery("SELECT * FROM ALUMNOS WHERE no_control = "+txtNControl.getText().toString(), null);
                if (cursor.moveToFirst()) {
                    do {
                        registros += cursor.getInt(cursor.getColumnIndex("no_control"));
                        registros += " ";
                        registros += cursor.getString(cursor.getColumnIndex("nombre"));
                        registros += " ";
                        registros += cursor.getString(cursor.getColumnIndex("aPaterno"));
                        registros += "\n";
                    } while (cursor.moveToNext());
                }
                if (registros.equals("")) {
               // Toast.makeText(actividad2.this, "NO HAY REGISTROS", Toast.LENGTH_SHORT).show();
                NO = true;
            } else
               // Toast.makeText(actividad2.this, registros, Toast.LENGTH_SHORT).show();
                NO = false;

            db.close();

        }
    }
}