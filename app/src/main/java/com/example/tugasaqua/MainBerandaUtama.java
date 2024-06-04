package com.example.tugasaqua;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainBerandaUtama extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private Button groupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beranda_utama);

        drawerLayout = findViewById(R.id.drawer_layout);
        groupButton = findViewById(R.id.id_sidebar);

        groupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.END);
            }
        });

    }

    public void scan(View view) {
        Intent intent = new Intent(MainBerandaUtama.this, Mainscan_tutupbotol.class);
        startActivity(intent );

    }

    public void scantp(View view) {
        Intent intent = new Intent(MainBerandaUtama.this, Mainscan_tutupbotol.class);
        startActivity(intent );

    }


    public void reedemv(View view) {
        Intent intent = new Intent(MainBerandaUtama.this,Main_redeemvgkatalog.class);
        startActivity(intent );

    }

}