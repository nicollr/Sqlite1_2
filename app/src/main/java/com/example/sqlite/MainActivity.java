package com.example.sqlite;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btninsertar,btnSelect,btnDelete,btnUpdate,actividad2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btninsertar=findViewById(R.id.insert);
        btnSelect=findViewById(R.id.select);
        btnDelete=findViewById(R.id.delete);
        btnUpdate=findViewById(R.id.update);
        actividad2=findViewById(R.id.actividad2);

        btninsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBaseHelper helper = new DataBaseHelper(MainActivity.this,"Alumnos",null,1);
                SQLiteDatabase db= helper.getWritableDatabase();

                if(db!=null){
                    db.execSQL("INSERT INTO ALUMNOS values(1,'BRISA','RAMIREZ')");

                    Toast.makeText(MainActivity.this,"Registro Guardado",Toast.LENGTH_SHORT).show();

                    db.close();

                }

            }
        });

        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String registros="";
                DataBaseHelper helper = new DataBaseHelper(MainActivity.this,"Alumnos",null,1);
                SQLiteDatabase db= helper.getWritableDatabase();
                if(db!=null){
                    Cursor cursor = db.rawQuery("SELECT * FROM Alumnos",null);
                    if(cursor.moveToFirst()){
                        do{
                            registros += cursor.getInt(cursor.getColumnIndex("no_control"));
                            registros += cursor.getString(cursor.getColumnIndex("nombre"));
                            registros+="\n";
                        }while (cursor.moveToNext());
                    }
                    if(registros.equals("")){
                        Toast.makeText(MainActivity.this,"No hay registros",Toast.LENGTH_SHORT).show();
                    }else
                    Toast.makeText(MainActivity.this,registros,Toast.LENGTH_SHORT).show();

                    db.close();

                }
            }
        });


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBaseHelper helper = new DataBaseHelper(MainActivity.this,"Alumnos",null,1);
                SQLiteDatabase db= helper.getWritableDatabase();

                if(db!=null){
                    db.execSQL("DELETE FROM  ALUMNOS WHERE nombre='BRISA'");

                    Toast.makeText(MainActivity.this,"Registro eliminado",Toast.LENGTH_SHORT).show();

                    db.close();

                }
            }
        });


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBaseHelper helper = new DataBaseHelper(MainActivity.this,"Alumnos",null,1);
                SQLiteDatabase db= helper.getWritableDatabase();

                if(db!=null){
                    db.execSQL("UPDATE ALUMNOS  set nombre='BRISA2'WHERE nombre='BRISA'");

                    Toast.makeText(MainActivity.this,"Registro actualizado",Toast.LENGTH_SHORT).show();

                    db.close();

                }
            }
        });

        actividad2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,actividad2.class);
                startActivityForResult(intent,0);
            }
        });

    }
}
