package com.example.tugasaqua;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Mainroyalvaganza extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.redeem_vaganza_poin);
    }

    public void main_menu(View view) {
        Intent intent = new Intent(Mainroyalvaganza.this,MainBerandaUtama.class);
        startActivity(intent );

    }
    public void redeemv2(View view) {
        Intent intent = new Intent(Mainroyalvaganza.this,Main_redeemvgkatalog.class);
        startActivity(intent );

    }
}