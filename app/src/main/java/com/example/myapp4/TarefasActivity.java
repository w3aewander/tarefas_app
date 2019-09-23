package com.example.myapp4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuCompat;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class TarefasActivity extends AppCompatActivity {


    private RecyclerView listView;
    private EditText edtTarefa;
    private static ArrayList<Tarefa> tarefas;
    private TarefasAdapter  tarefasAdapter;
    private CheckBox chkConcluida;
    private static int feito = 0;
    private static int naoFeito = 0;
    private TarefasDBHelper dbHelper;

    //Cria o banco de dados caso não exista
    private TarefasDB db ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarefas);

        setTitle("Minhas tarefas");
        Intent i = getIntent();


        //agora tá pegando do banco de dados.
        //tarefas = new ArrayList<Tarefa>();
        //tarefas.add ( new Tarefa(1, "Aprender JAVA", Status.Fazer) );
        //tarefas.add ( new Tarefa(2, "Aprender ANDROID", Status.Fazer));

        //Puxando os dados do banco de dados...
        db = new TarefasDB(getApplicationContext(),"db_tarefas",1);

        tarefas = db.getTarefas();

        tarefasAdapter = new TarefasAdapter(tarefas,this);

        Toast.makeText(this,
                i.getStringExtra("mensagem"),
                Toast.LENGTH_LONG).show();

        edtTarefa = findViewById(R.id.edtTarefa);
        RecyclerView listview  = (RecyclerView) findViewById(R.id.lstTarefas);


        listview.setAdapter( tarefasAdapter);

        RecyclerView.LayoutManager layout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        listview.setLayoutManager(layout);


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu, menu);
            return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.status:

                if (item.isChecked()) {item.setChecked(false);}
                else { item.setChecked(true);}

                irParaStatus(null);

                return true;

            case R.id.fechar:

                if (item.isChecked()) {item.setChecked(false);}
                else { item.setChecked(true);}

                fechar(null);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void addTarefa(View v){



        //Se o campo edtTarefa não estiver vazio, adciona a tarefa;
        //e depois limpe a caixa de texto (EditText))
        if ( ! edtTarefa.getText().toString().isEmpty() ){

            //atribui o tamanho da lista de tarefas ao id
            //long id = tarefas.size() + 1;
            //atribui à variável descricao o que o usuário digitou
            String descricao = edtTarefa.getText().toString();

            //Instancia a classe tarefa...
            Tarefa tarefa = new Tarefa(descricao,Status.Fazer);

            db.inserirTarefa(tarefa);

            //Limpara o texto na caixa de texto de tarefas
            edtTarefa.setText("");

            //Exibe uma mensagem na tela a partir do contexto atual.
            Toast.makeText(v.getContext(), "Tarefa adicionada com sucesso.",Toast.LENGTH_SHORT).show();
            tarefas = db.getTarefas();
            tarefasAdapter.notifyDataSetChanged();
            this.recreate();


        } else {
            //Exibe uma mensagem na tela a partir do contexto atual.
            Toast.makeText(v.getContext(), "Informe uma tarefa.", Toast.LENGTH_SHORT).show();
        }

    }

    public void excluir(long id){
        db.excluir(id);
        tarefasAdapter.notifyDataSetChanged();
        this.recreate();
    }

    public void atualizar(Tarefa tarefa){
        db.atualizarTarefa(tarefa);
        tarefasAdapter.notifyDataSetChanged();
        this.recreate();
    }

    public void irParaStatus(View v){

        //Cria um nova intent
        Intent i = new Intent();

        //Onde estou e para onde vou - qual a activity que quer iniciar.
        i.setClass(TarefasActivity.this,StatusActivity.class);

        //envia a quantide de tarefas cadastradas
        i.putExtra("qtdeTarefas",tarefasAdapter.getItemCount());

        //enviar a quantidade de tarefas conclúidas

        for( Tarefa t: tarefas ){


            if (t.getStatus().equals(Status.Feito)){
                feito++;
            } else if ( t.getStatus().equals(Status.Fazer)){
                naoFeito++;
            }

        };

        i.putExtra("tarefasConcluidas",feito);

        //enviar a quantidade de tarefas não concluídas
        i.putExtra("tarefasNaoConcluidas",naoFeito);

        //Iniciar a activity
        startActivity(i);

        feito = 0;
        naoFeito = 0;

    }

    public void fechar(View v){

        //finalizar a activity
        finish();

    }
}
