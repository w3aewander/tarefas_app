package com.example.tarefas;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp4.R;

public class TarefasAdapterViewHolder extends RecyclerView.ViewHolder {

        final TextView id;
        final TextView descricao;
        final TextView status;
        final Switch swConcluida;

        public TarefasAdapterViewHolder(View view) {

            super(view);
            id = view.findViewById(R.id.txtId);
            descricao  = view.findViewById(R.id.txtDescricao);
            swConcluida = view.findViewById(R.id.swCocluida);
            status = view.findViewById(R.id.txtStatus);


        }




}
