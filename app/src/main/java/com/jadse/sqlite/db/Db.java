package com.jadse.sqlite.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class Db extends SQLiteOpenHelper {
    SQLiteDatabase db;
    String _SQL;

    public Db(@Nullable Context context) {
        super( context, "sqliteApp.db", null, 1 );
        db = getWritableDatabase();
    }

    public void DbCloseOpen() {
        db.close();
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( "create table Usuario ( id INTEGER PRIMARY KEY, Nombres text, Apellidos text, Telefono text, Correo text, Passwordd text, Session text )" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) { }

    public void Sentencia( String _SQL ) {
        this._SQL = _SQL;
    }

    public void Ejecutar() {
        db.execSQL( _SQL );
    }

    public Cursor getCursor( ) {
        return db.rawQuery( _SQL, null );
    }

    public String getCampo() {
        String sCampo = null;
        Cursor cursor = getCursor();
        if ( cursor.moveToFirst() ) sCampo = cursor.getString(0);

        cursor.close();
        return sCampo;
    }

    public int Insert(String sTabla, ContentValues values ) {
        return (int) db.insert( sTabla, null, values );
    }

    public long Update( String sTabla, ContentValues values, String sCondicion) {
        return db.update( sTabla, values, sCondicion, null );
    }

    public int Delete( String sTabla ) {
        return db.delete(sTabla, null, null );
    }

}
