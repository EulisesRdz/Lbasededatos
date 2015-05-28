package com.example.echeverria.lbasededatos.adaptadores;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.echeverria.lbasededatos.R;
import com.example.echeverria.lbasededatos.modelos.estudiantes;
import java.util.List;

public class estudiAdapter extends RecyclerView.Adapter<estudiAdapter.estudiViewHolder> {
    private List<estudiantes> items;

    public static class estudiViewHolder extends RecyclerView.ViewHolder {

        //Se declaran las variables.
        public TextView id_a;
        public TextView nombre;
        public TextView apellido_p;
        public TextView apellido_m;
        public TextView email;
        public TextView telefono;
        public TextView edad;

        //Constructor de la clase estudianteViewHolder.
        public estudiViewHolder(View v) {
            super(v);
            id_a = (TextView) v.findViewById(R.id.id);
            nombre = (TextView) v.findViewById(R.id.nombre);
            apellido_p = (TextView) v.findViewById(R.id.apep);
            apellido_m = (TextView) v.findViewById(R.id.apem);
            email = (TextView) v.findViewById(R.id.email);
            telefono = (TextView) v.findViewById(R.id.tel);
            edad = (TextView) v.findViewById(R.id.edad);
        }
    }
    public estudiAdapter(List<estudiantes> items) {this.items = items;}

    //Se obtiene la tarjeta del layout.
    @Override
    public estudiViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card, viewGroup, false);
        return new estudiViewHolder(v);
    }

    //Se obtiene los datos que tengan los campos de la BD, depues mandarlos a las variables locales.
    @Override
    public void onBindViewHolder(estudiViewHolder viewHolder, int i) {
        viewHolder.id_a.setText(String.valueOf(items.get(i).getId_a()));
        viewHolder.nombre.setText(items.get(i).getNombre());
        viewHolder.apellido_p.setText(items.get(i).getApellidop());
        viewHolder.apellido_m.setText(items.get(i).getApellidom());
        viewHolder.email.setText(items.get(i).getEmail());
        viewHolder.telefono.setText(String.valueOf(items.get(i).getTelefono()));
        viewHolder.edad.setText("Edad: "+ (String.valueOf(items.get(i).getEdad())));
        //Sirve para mostrar con mensaje + el registro indicado.
        //viewHolder.calificacion.setText("Calificacion:"+String.valueOf(items.get(i).getCalificacion()));
    }
    //Obtiene la posicion.
    @Override
    public int getItemCount() {
        return items.size();
    }
}
