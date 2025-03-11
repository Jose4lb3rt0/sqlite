package com.jadse.sqlite.controller;

import android.content.Context;
import android.database.Cursor;

import com.jadse.sqlite.db.Db;
import com.jadse.sqlite.model.Usuario;

public class DemoController {
    Db db;

    public DemoController(Context context) { db = new Db(context); }

    public boolean Login(Usuario usuario) {
        db.Sentencia( String.format("select * from Usuario where Dni='%s' and Passwordd='%s'", usuario.getDni(), usuario.getPasswordd()) );
        Cursor cursor = db.getCursor();
        if ( cursor.moveToFirst() )
            usuario.setRegistro( cursor );

        cursor.close();
        return usuario.getId() > 0;
    }

}
