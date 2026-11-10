package com.example.calculadorasimples.dbHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class ConexaoSQLite extends SQLiteOpenHelper {
    private static ConexaoSQLite INSTANCIA_CONEXAO;
    private static final int VERSION_DB = 1;
    private static final String NOME_DB = "contadorTruco";

    public ConexaoSQLite( Context context) {
        super(context, NOME_DB, null, VERSION_DB);
    }

    public static synchronized ConexaoSQLite getInstance(Context context){
        if(INSTANCIA_CONEXAO == null){
            INSTANCIA_CONEXAO = new ConexaoSQLite(context.getApplicationContext());
        }

        return INSTANCIA_CONEXAO;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlTabelaPartidas =
                "CREATE TABLE IF NOT EXISTS partidas" +
                        "(" +
                        " id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "data TEXT," +
                        "jogador1 TEXT, " +
                        "jogador2 TEXT," +
                        "pontos1 INTEGER," +
                        "pontos2 INTEGER," +
                        "vencedor TEXT" +
                        ")";
        db.execSQL(sqlTabelaPartidas);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}