package com.example.echeverria.lbasededatos.modelos;

public class estudiantes {
    private String id_a, nombre, apellidop, apellidom, email, telefono, edad;

    public estudiantes(String id_a, String nombre, String apellidop, String apellidom, String email, String telefono, String edad) {
        this.id_a = id_a;
        this.nombre = nombre;
        this.apellidop = apellidop;
        this.apellidom = apellidom;
        this.email = email;
        this.telefono = telefono;
        this.edad = edad;
    }
    public String getId_a() {
        return id_a;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidop() {
        return apellidop;
    }

    public String getApellidom() {
        return apellidom;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEdad() {
        return edad;
    }
}
