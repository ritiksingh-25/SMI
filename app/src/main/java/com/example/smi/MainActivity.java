package com.example.smi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity {
    private GifImageView fbgif;
    private GifImageView googlegif;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        fbgif=findViewById(R.id.fbgif);
        googlegif=findViewById(R.id.googlegif);
        fbgif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,facebook.class);
                startActivity(intent);
                finish();
            }
        });

        googlegif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2=new Intent(MainActivity.this,google.class);
                startActivity(intent2);
                finish();
            }
        });
    }
}



