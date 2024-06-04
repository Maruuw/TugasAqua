package com.example.tugasaqua;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Mainundian extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mendapatkan_undian_evg);
    }

    public void wheel1(View view) {
        Intent intent = new Intent(Mainundian.this,Mainspinwheel1.class);
        startActivity(intent );

    }

}