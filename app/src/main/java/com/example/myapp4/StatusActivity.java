package com.example.myapp4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class StatusActivity extends AppCompatActivity {

    private TextView qtdeTarefas;
    private TextView tarefasConcluidas;
    private TextView tarefasNaoConcluidas;
    private TextView resultadoStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_status);

        qtdeTarefas = findViewById(R.id.qtdeTarefas);
        tarefasConcluidas = findViewById(R.id.tarefasConcluidas);
        tarefasNaoConcluidas = findViewById(R.id.tarefasNaoConcluidas);
        resultadoStatus = findViewById(R.id.txtResutado);


        Intent i = getIntent();

        qtdeTarefas.setText( String.valueOf(i.getIntExtra("qtdeTarefas",0)));
        tarefasConcluidas.setText( String.valueOf(i.getIntExtra("tarefasConcluidas",0)));
        tarefasNaoConcluidas.setText( String.valueOf(i.getIntExtra("tarefasNaoConcluidas",0)));

        if ( i.getIntExtra("tarefasNaoConcluidas",0) == 0 ){
            resultadoStatus.setText("Parabéns, todas as tarefas concluídas!");
        } else {
            resultadoStatus.setText("Vamos lá, tente realizar todas as tarefas...");
        }

    }

    public void fechar(View v){

        finish();
    }
}
