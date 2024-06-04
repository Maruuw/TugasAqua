package com.example.tugasaqua;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Mainlogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login2 (View view) {
        Intent intent = new Intent(Mainlogin.this,Mainenterlogin.class);
        startActivity(intent );

    }

    public void login3 (View view) {
        Intent intent = new Intent(Mainlogin.this,Mainregister.class);
        startActivity(intent );

    }
}