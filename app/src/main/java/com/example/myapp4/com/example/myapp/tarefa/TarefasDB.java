package com.example.myapp4.com.example.myapp.tarefa;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import androidx.annotation.Nullable;

import com.example.myapp4.Status;

import java.util.ArrayList;

public class TarefasDB extends TarefasDBHelper {


    /**
     * @param context
     * @param name
     * @param version
     */
    public TarefasDB(@Nullable Context context, @Nullable String name, int version) {
        super(context, name, version);
    }


    /**
     *
     * @param t
     */
    public void inserirTarefa(@org.jetbrains.annotations.NotNull Tarefa t){

        //Abre o banco no mode de gravação
        SQLiteDatabase db = getWritableDatabase();

//Cria um map contendo valores que serão incluídos no campo
        ContentValues values = new ContentValues();
        values.put(TarefasDBContrato.TabTarefas.COLUNA_DESCRICAO, t.getDescricao());
        values.put(TarefasDBContrato.TabTarefas.COLUNA_STATUS, t.getStatus().toString());

// Insere a linha e retorna o ID do registro inserido
        long newRowId = db.insert(
                  TarefasDBContrato.TabTarefas.TABLE_NAME,
                 null, values);
    }


    /**
     *
     * @return
     */
    public ArrayList<Tarefa> getTarefas(){

        SQLiteDatabase db = getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                TarefasDBContrato.TabTarefas.COLUNA_DESCRICAO,
                TarefasDBContrato.TabTarefas.COLUNA_STATUS
        };

// Filtra resultados com a clausula WHERE "descricao" = 'descricao'
//        String selection = TarefasDBContrato.TabTarefas.COLUNA_DESCRICAO + " = ?";
//        String[] selectionArgs = { "My Title" };

// Ordena a saida de resultados
        String sortOrder =
                BaseColumns._ID + " DESC";

        //Cursor cursor = db.rawQuery("select * from tb_tarefas",null);
        Cursor cursor = db.query(
                TarefasDBContrato.TabTarefas.TABLE_NAME,   // The table to query
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );

        cursor.moveToFirst();
        ArrayList<Tarefa> tarefas = new ArrayList<>();

        while ( cursor.moveToNext()){

            Status status = Status.valueOf( cursor.getString(
                    cursor.getColumnIndex(TarefasDBContrato.TabTarefas.COLUNA_STATUS)));

            Tarefa tarefa = new Tarefa(

                    cursor.getLong(cursor.getColumnIndex(TarefasDBContrato.TabTarefas.COLUNA_ID)),
                    cursor.getString(cursor.getColumnIndex(TarefasDBContrato.TabTarefas.COLUNA_DESCRICAO)),
                    status
            );

            tarefas.add(tarefa);

        }

        cursor.close();
        return tarefas;
    }


    /**
     *
     * @param id
     * @return
     */
    public int excluir(long id){
        SQLiteDatabase db = this.getWritableDatabase();
        //Define a condição para a deleção usando a clausula WHERE.
        String selection = TarefasDBContrato.TabTarefas.COLUNA_ID + " LIKE ?";
// Specify arguments in placeholder order.
        String[] selectionArgs = { String.valueOf(id) };
// Issue SQL statement.
        int deletedRows = db.delete(TarefasDBContrato.TabTarefas.TABLE_NAME, selection, selectionArgs);

        return deletedRows;
    }

    public int atualizarTarefa(Tarefa t){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TarefasDBContrato.TabTarefas.COLUNA_DESCRICAO, t.getDescricao());
        values.put(TarefasDBContrato.TabTarefas.COLUNA_STATUS, t.getStatus().toString());

// Which row to update, based on the title
        String selection = BaseColumns._ID + " = ?";
        String[] selectionArgs = { String.valueOf(t.getId()) };

        int count = db.update(
                TarefasDBContrato.TabTarefas.TABLE_NAME,
                values,
                selection,
                selectionArgs);

        return count;
    }

}
