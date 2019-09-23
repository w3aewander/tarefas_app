package com.example.myapp4;

import android.app.Application;
import android.content.Context;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {

       TarefasDB t = new TarefasDB(null,"db_tarefas",1);
//        ArrayList<Tarefa> tarefas = t.getTarefas();
//
//        for(Tarefa t2: tarefas){
//            System.out.println(t2.getDescricao());
//        }

        Tarefa tarefa = new Tarefa(
                1,"Lavar o carro",Status.Fazer
        );
        t.inserirTarefa(tarefa);
    }
}