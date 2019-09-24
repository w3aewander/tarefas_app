package com.example.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.se.omapi.Session;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myapp4.R;

public class LoginActivity extends AppCompatActivity {

    private ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_login);

        pb = findViewById(R.id.progressBar);
        pb.setVisibility(View.INVISIBLE);


    }

    public void abrirTarefas(View v){

        EditText email = findViewById(R.id.edtEmail);
        EditText senha = findViewById(R.id.edtSenha);



        if ( autenticar(email.getText().toString(), senha.getText().toString())) {

            LoginSession.LOGADO = true;

            pb.setVisibility(View.VISIBLE);

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent();
                    i.setClass(LoginActivity.this, TarefasActivity.class);
                    i.putExtra("mensagem", "Seja bem vindo!");
                    startActivity(i);
                    pb.setVisibility(View.INVISIBLE);
                    finish();
                }
            }, 500);

            //startActivity(new Intent(LoginActivity.this, TarefasActivity.class));

        }
    }

    /**
     *
     * @param email
     * @param senha
     * @return boolean - falso ou verdadeiro
     */
    private boolean autenticar(String email, String senha){
        //    && => and
        //    || => ou
        boolean isAutenticado = false;
        if ( email.isEmpty() || senha.isEmpty() ){
            Toast.makeText(
                    getApplicationContext(),
                    "E-mail e senha devem ser informados",
                    Toast.LENGTH_SHORT).show();
            isAutenticado = false;
        } else {
            if ( email.equals("wander.silva@gmail.com") && senha.equals("123456")){
                isAutenticado = true;
            } else {
                Toast.makeText(
                        getApplicationContext(),
                        "Acesso inválido. E-mail ou senha incorreto",
                        Toast.LENGTH_LONG).show();
                isAutenticado = false;
            }
        }
        return isAutenticado;
    }

}
