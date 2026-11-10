package com.example.calculadorasimples.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.calculadorasimples.dbHelper.ConexaoSQLite;
import com.example.calculadorasimples.model.Partida;

import java.util.ArrayList;
import java.util.List;

public class PartidaDAO {
    private final ConexaoSQLite conexao;

    public PartidaDAO(Context context) {
        this.conexao = ConexaoSQLite.getInstance(context);
    }

    public long inserir(String data, String jogador1, String jogador2, int pontos1,
                        int pontos2, String vencedor){

        SQLiteDatabase db = conexao.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("data", data);
        valores.put("jogador1", jogador1);
        valores.put("jogador2", jogador2);
        valores.put("pontos1", pontos1);
        valores.put("pontos2", pontos2);
        valores.put("vencedor", vencedor);

        return db.insert("partidas", null, valores);
    }


    public List<Partida> listarTodas(){
        List<Partida> lista = new ArrayList<>();

        SQLiteDatabase db = conexao.getReadableDatabase();
        try (Cursor c = db.query("partidas", null,
                null, null, null,
                null, "id DESC")) {
            while (c.moveToNext()) {
                lista.add(new Partida(
                        c.getLong(c.getColumnIndexOrThrow("id")),
                        c.getString(c.getColumnIndexOrThrow("data")),
                        c.getString(c.getColumnIndexOrThrow("jogador1")),
                        c.getString(c.getColumnIndexOrThrow("jogador2")),
                        c.getInt(c.getColumnIndexOrThrow("pontos1")),
                        c.getInt(c.getColumnIndexOrThrow("pontos2")),
                        c.getString(c.getColumnIndexOrThrow("vencedor"))
                ));
            }
        }
        return lista;
    }
}
