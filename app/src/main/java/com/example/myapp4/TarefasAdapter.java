package com.example.myapp4;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.hardware.camera2.CameraManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
            holder.status.setCompoundDrawablesWithIntrinsicBounds(0, 0, android.R.drawable.checkbox_on_background, 0);
        }



        holder.status.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                final AlertDialog.Builder dialogo = new AlertDialog.Builder(v.getContext());
                dialogo.setTitle("Alterar status");
                dialogo.setCancelable(true);
                dialogo.setIcon(R.drawable.gradientoval);
                dialogo.setMessage("Tarefa realizada?");
                dialogo.setPositiveButton("Sim",new DialogInterface.OnClickListener() {

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

                dialogo.setNegativeButton("Não",null);

                dialogo.show();

                return true;
            }
        });

    }


}
