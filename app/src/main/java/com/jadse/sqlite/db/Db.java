package com.jadse.sqlite.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class Db extends SQLiteOpenHelper {
    SQLiteDatabase db;
    String sql;

    public Db(@Nullable Context context) {
        super(context, "demo", null, 1);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table usuario( id integer, Nombres text, Apellidos text, Dni text, Passwordd text )");
        db.execSQL("insert into usuario values(1, 'José', 'Salcedo Escobar', '72039485', '1234')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void Sentencia(String sql) {
        this.sql = sql;
    }

    public Cursor getCursor() {
        return db.rawQuery( this.sql, null );
    }
}
