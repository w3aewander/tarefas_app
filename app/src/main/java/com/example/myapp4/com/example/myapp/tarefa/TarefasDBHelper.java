package com.example.myapp4.com.example.myapp.tarefa;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class TarefasDBHelper extends SQLiteOpenHelper {

    //Sempre que houver alteração no esquema do banco deve alterar a versão do banco.
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "db_tarefas.db";

    /**
     * Cria a tabela
     */
    private static final String SQL_CRIAR_TABELA =
            "CREATE TABLE " + TarefasDBContrato.TabTarefas.TABLE_NAME + " (" +
                    TarefasDBContrato.TabTarefas.COLUNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    TarefasDBContrato.TabTarefas.COLUNA_DESCRICAO + " TEXT," +
                    TarefasDBContrato.TabTarefas.COLUNA_STATUS + " TEXT)";

    /**
     * Dropa a tabela (exclui)
     */
    private static final String SQL_EXCLUIR_TABELA =
            "DROP TABLE IF EXISTS " + TarefasDBContrato.TabTarefas.TABLE_NAME;

    /**
     *
     * @param context
     * @param name
     * @param version
     */
    public TarefasDBHelper(@Nullable Context context, @Nullable String name, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     *
     * @param db
     */
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SQL_CRIAR_TABELA);
    }

    /**
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Ao atualizar a versão excluir todos os dados e cria uma nova estrutura limpa da tabela.
        db.execSQL(SQL_EXCLUIR_TABELA);
        onCreate(db);
    }

    /**
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }



}
