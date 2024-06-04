package com.example.tugasaqua;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Mainenterlogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enterlogin);
    }

    public void backlogin (View view) {
        Intent intent = new Intent(Mainenterlogin.this,Mainlogin.class);
        startActivity(intent );

    }

    public void berandautama (View view) {
        Intent intent = new Intent(Mainenterlogin.this,MainBerandaUtama.class);
        startActivity(intent );

    }
}