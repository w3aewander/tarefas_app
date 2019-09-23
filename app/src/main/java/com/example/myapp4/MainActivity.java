package com.example.myapp4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CountDownTimer cd = new CountDownTimer(5000,1) {
            @Override
            public void onTick(long millisUntilFinished) {

                ProgressBar pb = findViewById(R.id.progressBar2);
                pb.setProgress( Integer.parseInt(String.valueOf( millisUntilFinished  )));
            }

            @Override
            public void onFinish() {

                irParaTarefas(null);
            }
        }.start();
    }

    public void irParaTarefas(View v){

        startActivity(new Intent(MainActivity.this,LoginActivity.class));
    }
}
