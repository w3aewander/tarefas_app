package com.example.myapp4.com.example.myapp.tarefa;

import android.provider.BaseColumns;

/**
 * Define constantes para os nomes das colunas
 */
public final class TarefasDBContrato {

    /**
     * Inicia um construtor padrão
     */
    private TarefasDBContrato() {}

        /* Define os nomes da tabela e das colunas */
        public static class TabTarefas implements BaseColumns {
            public static final String TABLE_NAME = "tb_tarefas";
            public static final String COLUNA_ID = "_ID";

            public static final String COLUNA_DESCRICAO = "descricao";
            public static final String COLUNA_STATUS = "status";
        }


}


