package com.jadse.sqlite.model;

import android.database.Cursor;

import java.io.Serializable;

public class Usuario implements Serializable {
    int id;
    String Nombres, Apellidos, Dni, Passwordd;

    public void setRegistro(Cursor cursor) {
        this.id = cursor.getInt(0);
        Nombres = cursor.getString(1);
        Apellidos = cursor.getString(2);
        Dni = cursor.getString(3);
        Passwordd = cursor.getString(4);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String nombres) {
        Nombres = nombres;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }

    public String getDni() {
        return Dni;
    }

    public void setDni(String dni) {
        Dni = dni;
    }

    public String getPasswordd() {
        return Passwordd;
    }

    public void setPasswordd(String passwordd) {
        Passwordd = passwordd;
    }
}
