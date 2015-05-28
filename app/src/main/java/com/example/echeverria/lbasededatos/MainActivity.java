package com.example.echeverria.lbasededatos;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.echeverria.lbasededatos.modelos.AdminSQLiteOpenHelper;

public class MainActivity extends ActionBarActivity {
    EditText id_a, nombre, apellidop, apellidom, email, telefono, edad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Obtener instancia de los controles de la vista.
        id_a = (EditText)findViewById(R.id.id_A);
        nombre = (EditText) findViewById(R.id.nombre);
        apellidop = (EditText) findViewById(R.id.apellidop);
        apellidom = (EditText) findViewById(R.id.apellidom);
        email = (EditText) findViewById(R.id.email);
        telefono = (EditText)findViewById(R.id.telefono);
        edad=(EditText)findViewById(R.id.edad);
    }
    //Metodo que nos permite registrar datos en la base de datos.
    public void registrar (View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "contactos", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        //Se declaran las variables y se igualan al campo para obtener los datos.
        String e_id_a = id_a.getText().toString();
        String e_nombre = nombre.getText().toString();
        String e_apellidop = apellidop.getText().toString();
        String e_apellidom = apellidom.getText().toString();
        String e_email = email.getText().toString();
        String e_telefono = telefono.getText().toString();
        String e_edad = edad.getText().toString();

        //Condicion que indica si el campo esta vacio que muestre un mensaje.
        if (e_id_a.matches("")) {
            Toast.makeText(this, "Debes de indicar un ID!", Toast.LENGTH_SHORT).show();
            return;
        } else {
            //Se realiza una consulta a la DB.
            Cursor fila = bd.rawQuery("select nombre, apellido_p, apellido_m, email, telefono, edad from contactos where id_A=" + e_id_a, null);
            //Condicion que permite comprobar si el dato ya se encuentra registrado.
            if (fila.getCount() >= 1) {
                Toast.makeText(this, "Ingrese un ID diferente", Toast.LENGTH_SHORT).show();
            } else {
                //Creamos el registro a insertar como objeto ContentValues
                ContentValues registro = new ContentValues();
                registro.put("id_A", e_id_a);
                registro.put("nombre", e_nombre);
                registro.put("apellido_p", e_apellidop);
                registro.put("apellido_m", e_apellidom);
                registro.put("email", e_email);
                registro.put("telefono", e_telefono);
                registro.put("edad", e_edad);
                //permite insertar en la DB, en donde se indica que tabla y de donde obtendra los datos.
                bd.insert("contactos", null, registro);
                //Se cierra la base de datos.
                bd.close();
                //Codigo que nos permite limpiar los editText.
                id_a.setText("");
                nombre.setText("");
                apellidop.setText("");
                apellidom.setText("");
                email.setText("");
                telefono.setText("");
                edad.setText("");
                //Se situal el cursor en el campo indicado.
                id_a.requestFocus();
                //Muestra un mensaje corto.
                Toast.makeText(this, "Se agrego un nuevo estudiante", Toast.LENGTH_SHORT).show();
            }
        }
    }
    //metodo para realizar consultas a la base de datos.
    public void consulta(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "contactos", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String e_id_a = id_a.getText().toString();
        //Condicion que indica si el campo esta vacio que muestre un mensaje.
        if (e_id_a.matches("")) {
            Toast.makeText(this, "Debes de indicar un ID!", Toast.LENGTH_SHORT).show();
            return;
        } else {
            //Se realiza una consulta a la DB.
            Cursor fila = bd.rawQuery("select id_A, nombre, apellido_p, apellido_m, email, telefono, edad from contactos where id_A=" + e_id_a, null);
            //Condicion que permite mostrar el primer registro de la consulta realizada.
            if (fila.moveToFirst()) {
                //Se indica el orden que devolvera los datos a los campos de la vista.
                id_a.setText(fila.getString(0));
                nombre.setText(fila.getString(1));
                apellidop.setText(fila.getString(2));
                apellidom.setText(fila.getString(3));
                email.setText(fila.getString(4));
                telefono.setText(fila.getString(5));
                edad.setText(fila.getString(6));
            } else {
                Toast.makeText(this, "No existe el estudiante", Toast.LENGTH_SHORT).show();
            }
            //Se cierra la base de datos.
            bd.close();
        }
    }
    //Metodo para eliminar un registro de la base de datos.
    public void baja(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "contactos", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String e_id_a = id_a.getText().toString();
        //Condicion que indica si el campo esta vacio que muestre un mensaje.
        if (e_id_a.matches("")) {
            Toast.makeText(this, "Debes de indicar un ID!", Toast.LENGTH_SHORT).show();
            return;
        } else {
            //Permite eliminar los registros de la tabla mediante una condicion.
            int cant = bd.delete("contactos", "id_A=" + e_id_a, null);
            //Se cierra la base de datos.
            bd.close();
            //Codigo que nos permite limpiar los editText.
            id_a.setText("");
            nombre.setText("");
            apellidop.setText("");
            apellidom.setText("");
            email.setText("");
            telefono.setText("");
            edad.setText("");
            //Cant Devuelve dos valores si es 1 muestra un mensaje satisfactorio.
            if (cant == 1) {
                Toast.makeText(this, "Se borró el estudiante", Toast.LENGTH_SHORT).show();
                id_a.requestFocus();
                //Si no lo es muestra un mensaje de error.
            } else {
                Toast.makeText(this, "No existe el estudiante, ingrese un ID registrado", Toast.LENGTH_SHORT).show();
                id_a.requestFocus();
            }
        }
    }
    //Metodo para la modificacion de los registros almacenados.
    public void modificacion (View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "contactos", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        //Se declaran las variables y se igualan al campo para obtener los datos.
        String e_id_a = id_a.getText().toString();
        String e_nombre = nombre.getText().toString();
        String e_apellidop = apellidop.getText().toString();
        String e_apellidom = apellidom.getText().toString();
        String e_email = email.getText().toString();
        String e_telefono = telefono.getText().toString();
        String e_edad = edad.getText().toString();
        //Creamos el registro a insertar como objeto ContentValues
        ContentValues registro = new ContentValues();
        registro.put("id_A", e_id_a);
        registro.put("nombre", e_nombre);
        registro.put("apellido_p", e_apellidop);
        registro.put("apellido_m", e_apellidom);
        registro.put("email", e_email);
        registro.put("telefono", e_telefono);
        registro.put("edad", e_edad);
        //Condicion que indica si el campo esta vacio que muestre un mensaje.
        if (e_id_a.matches("")) {
            Toast.makeText(this, "Debes de indicar un ID!", Toast.LENGTH_SHORT).show();
            return;
        } else {
            //Permite actualizar los registros de la tabla mediante una condicion.
            int cant = bd.update("contactos", registro, "id_A=" + e_id_a, null);
            //Se cierra la base de datos.
            bd.close();
            //Cant Devuelve dos valores si es 1 muestra un mensaje satisfactorio.
            if (cant == 1) {
                Toast.makeText(this, "Se modificaron los datos del estudiante", Toast.LENGTH_SHORT).show();
                id_a.requestFocus();
                //Si no lo es muestra un mensaje de error.
            } else {
                Toast.makeText(this, "No existe el estudiante, ingrese un ID registrado", Toast.LENGTH_SHORT).show();
                id_a.requestFocus();
            }
        }
    }

    public void limpia (View v){
        //Codigo que nos permite limpiar los editText.
        id_a.setText("");
        nombre.setText("");
        apellidop.setText("");
        apellidom.setText("");
        email.setText("");
        telefono.setText("");
        edad.setText("");
        id_a.requestFocus();
    }
    //Metodo buscar, el cual nos permite abrir otra actividad.
    public void buscar (View v){
        Intent a = new Intent(this, students.class);
        startActivity(a);
    }
}
