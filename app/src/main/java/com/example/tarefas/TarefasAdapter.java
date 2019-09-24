package com.example.tarefas;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.main.Status;
import com.example.myapp4.R;

import java.util.ArrayList;


public class TarefasAdapter extends RecyclerView.Adapter {

    private TarefasDB db ;

    private ArrayList<Tarefa> tarefas;
    private Context context;

    public TarefasAdapter(ArrayList<Tarefa> tarefas,Context context){
        this.tarefas = tarefas;
        this.context = context;
        db = new TarefasDB( context, TarefasDBContrato.TabTarefas.TABLE_NAME,2);
    }

    @Override
    public int getItemCount() {

        return tarefas.size();
    }


    public ArrayList<Tarefa> getTarefas(){
        ArrayList<Tarefa> tarefas = db.getTarefas();
        notifyDataSetChanged();

        return tarefas;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.tarefa_item, parent, false);
        TarefasAdapterViewHolder holder = new TarefasAdapterViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        final TarefasAdapterViewHolder holder =  (TarefasAdapterViewHolder) viewHolder;
        final Tarefa tarefa = tarefas.get(position) ;
        final Switch swConcluida = holder.swConcluida;

        holder.id.setText(String.valueOf( tarefa.getId() ));
        holder.descricao.setText(tarefa.getDescricao());
        holder.status.setText(tarefa.getStatus().toString());

        if ( holder.status.getText().toString().equals("Feito")) {
            holder.status.setCompoundDrawablesWithIntrinsicBounds(0,0,android.R.drawable.checkbox_on_background,0);
        } else {
            holder.status.setCompoundDrawablesWithIntrinsicBounds(0,0,android.R.drawable.checkbox_off_background,0);
        }


        holder.status.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                final AlertDialog.Builder dialogo = new AlertDialog.Builder(v.getContext());

                View view = LayoutInflater.from(v.getContext()).inflate(R.layout.dialogo,null);


                final Button btnFeito = view.findViewById(R.id.btnFeito);
                btnFeito.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        db.atualizarTarefa(tarefa);
                        tarefas = db.getTarefas();
                        holder.status.setText(tarefa.getStatus().toString());
                        holder.status.setCompoundDrawablesWithIntrinsicBounds(0,0,android.R.drawable.checkbox_on_background,0);

                        notifyDataSetChanged();
                        dialogo.setView(null);
                    }
                });

                final Button btnExcluir = view.findViewById(R.id.btnExcluir);
                btnExcluir.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        db.excluir(tarefa.getId());
                        tarefas = db.getTarefas();
                        notifyDataSetChanged();

                    }
                });


                dialogo.setCancelable(true);
                dialogo.setView(view);

                dialogo.show();


                return true;

                //dialogo.setTitle("Alterar status");


                //dialogo.setIcon(R.drawable.gradientoval);
                //dialogo.setMessage("Tarefa realizada?");
                //dialogo.setMessage("O que deseja?");



                /*
                dialogo.setNegativeButton("Excluir",new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        db.excluir(tarefa.getId());


                        holder.status.setCompoundDrawablesWithIntrinsicBounds(0,0,android.R.drawable.checkbox_on_background,0);
                        tarefas = db.getTarefas();

                        notifyDataSetChanged();


                    }
                });

                dialogo.setPositiveButton("Atualizar",new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        tarefa.setStatus(Status.Feito);
                        holder.status.setText(tarefa.getStatus().toString());
                        //int d =  android.R.drawable.checkbox_off_background;

                        db.atualizarTarefa(tarefa);
                        notifyDataSetChanged();

                        holder.status.setCompoundDrawablesWithIntrinsicBounds(0,0,android.R.drawable.checkbox_on_background,0);


                    }
                });


                dialogo.setNeutralButton("Cancelar",null);


                 */

            }
        });


    }


}
