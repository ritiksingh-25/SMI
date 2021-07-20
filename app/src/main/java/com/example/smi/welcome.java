package com.example.smi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        getSupportActionBar().hide();
        //().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Thread t=new Thread(){
            public void run(){
                try {
                    sleep(5000);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    Intent intent=new Intent(welcome.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }}; t.start();
    }
}