package com.example.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.se.omapi.Session;
import android.view.View;
import android.widget.ProgressBar;

import com.example.myapp4.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if ( !LoginSession.LOGADO ) {
            CountDownTimer cd = new CountDownTimer(5000, 1) {
                @Override
                public void onTick(long millisUntilFinished) {

                    ProgressBar pb = findViewById(R.id.progressBar2);
                    pb.setProgress(Integer.parseInt(String.valueOf(millisUntilFinished)));
                }

                @Override
                public void onFinish() {

                    irParaTarefas(null);
                }

            }.start();
        }
    }

    public void irParaTarefas(View v){

        //só vai para a tela de login se não estiver autenticado
        if ( ! LoginSession.LOGADO ) {

            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        } else {
            startActivity(new Intent(MainActivity.this, TarefasActivity.class));
        }
    }
}
