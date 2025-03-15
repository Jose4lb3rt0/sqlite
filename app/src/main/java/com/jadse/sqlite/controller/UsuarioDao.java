package com.jadse.sqlite.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.jadse.sqlite.MainActivity;
import com.jadse.sqlite.db.Db;
import com.jadse.sqlite.model.Usuario;

public class UsuarioDao {
    Db db;

    public UsuarioDao(Context context) {
        db = new Db(context);
    }

    public void Guardar(Usuario usuario) {
        db.Delete("Usuario");
        ContentValues values = new ContentValues();
        values.put("Nombres", usuario.getNombres());
        values.put("Apellidos", usuario.getApellidos());
        values.put("Telefono", usuario.getTelefono());
        values.put("Correo", usuario.getCorreo());
        values.put("Passwordd", usuario.getPasswordd());
        values.put("Session", true);
        db.Insert("Usuario", values);
    }

    public void getUsuario(String correo, String passwordd) {
        db.Sentencia("select * from Usuario where Correo=" + correo);
        Cursor cursor = db.getCursor();
        if ( cursor.moveToFirst() )
            MainActivity.usuario = cursor.isNull(6) ? null : new Usuario( cursor );
        cursor.close();
    }
}
