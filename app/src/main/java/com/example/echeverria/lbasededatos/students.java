package com.example.echeverria.lbasededatos;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.RecyclerView;

import com.example.echeverria.lbasededatos.adaptadores.estudiAdapter;
import com.example.echeverria.lbasededatos.modelos.AdminSQLiteOpenHelper;
import com.example.echeverria.lbasededatos.modelos.estudiantes;

import java.util.ArrayList;
import java.util.List;


public class students extends ActionBarActivity {

    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);

        //Se crea un arreglo, despues se manda llamar la tabla de la base de datos.
        List<estudiantes> items = new ArrayList<>();
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "contactos", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        //Se realiza la consulta en la base de datos.
        Cursor fila = bd.rawQuery("select id_A, nombre, apellido_p, apellido_m, email, telefono, edad from contactos", null);

        //ciclo para ir moviendo los resultados de la busqueda.
        for(fila.moveToFirst(); !fila.isAfterLast(); fila.moveToNext()){
            items.add(new estudiantes(fila.getString(0), fila.getString(1),fila.getString(2),fila.getString(3),fila.getString(4),fila.getString(5),(fila.getString(6))));
        }

        // Obtener el Recycler
        recycler = (RecyclerView) findViewById(R.id.r_students);
        recycler.setHasFixedSize(true);

        // Usar un administrador para LinearLayout
        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);

        // Crear un nuevo adaptador
        adapter = new estudiAdapter(items);
        recycler.setAdapter(adapter);
    }
}
