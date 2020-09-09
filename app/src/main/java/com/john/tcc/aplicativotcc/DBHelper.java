package com.john.tcc.aplicativotcc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static int version = 1;
    private static String name = "Banco_de_Dados.dp";

    public DBHelper(Context context) {
        super(context, name, null, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String str = "CREATE TABLE Utilizador(email TEXT PRIMARY KEY, senha TEXT, nome TEXT, telefone TEXT, data TEXT, mensagem TEXT);";
        db.execSQL(str);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Utilizador;");
        onCreate(db);
    }

    public long CriarUtilizador(String email, String senha, String nome, String telefone, String data, String mensagem) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("email", email);
        cv.put("senha", senha);
        cv.put("nome", nome);
        cv.put("telefone", telefone);
        cv.put("data", data);
        cv.put("mensagem", mensagem);
        long result = db.insert ("Utilizador", null,cv);
        return result;
    }

    public String ValidarLogin(String email, String senha) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Utilizador WHERE email=? AND senha=?", new String[] {email, senha});
        if (c.getCount()>0){
            return "OK";
        }
        return "ERRO";
    }










}
