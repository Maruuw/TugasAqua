package com.example.tugasaqua;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Main_redeemvgkatalog extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.redeem_vaganza_katalog);
    }

    public void redeemrvg(View view) {
        Intent intent = new Intent(Main_redeemvgkatalog.this,Mainroyalvaganza.class);
        startActivity(intent );

    }

    public void wheel2(View view) {
        Intent intent = new Intent(Main_redeemvgkatalog.this,Mainspinwheel2.class);
        startActivity(intent );

    }

    public void klaimtb(View view) {
        Intent intent = new Intent(Main_redeemvgkatalog.this,Mainklaimtutup.class);
        startActivity(intent );

    }
}