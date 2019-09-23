package com.example.myapp4.com.example.myapp.tarefa;

import android.widget.Switch;

import com.example.myapp4.Status;

public class Tarefa {

    private long id;
    private String descricao;
    private Status status;


    public Tarefa(long id, String descricao, Status status) {
        this.id = id;
        this.descricao = descricao;
        this.status = status;
    }

    public Tarefa(String descricao, Status status) {
        this.descricao = descricao;
        this.status = status;
    }
    @Override
    public String toString() {
        return "Tarefa{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", status=" + status +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


}
