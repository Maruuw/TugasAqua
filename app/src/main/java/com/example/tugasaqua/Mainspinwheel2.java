package com.example.tugasaqua;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import java.util.Random;

public class Mainspinwheel2 extends AppCompatActivity {

    Button btnSpin;
    ImageView ivWheel;
    Handler handler;
    Runnable runnable;
    int rotationIncrement;
    int totalRotation;
    boolean isSpinning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spinwheelperiode);

        btnSpin = findViewById(R.id.rectangle_11);
        ivWheel = findViewById(R.id.wheel);
        handler = new Handler();

        btnSpin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnSpin.setEnabled(false);
                startSpin();
            }
        });
    }

    private void startSpin() {
        Random random = new Random();
        int partialRotation = random.nextInt(6);

        // Tambahkan rotasi parsial ke dalam 6 putaran penuh, plus tambahan 15-30 derajat
        int additionalRotation = random.nextInt(16) + 15; // Random between 15 and 30
        totalRotation = (6 * 360) + (partialRotation * 60) + additionalRotation;

        rotationIncrement = 15;  // Initial speed
        isSpinning = true;

        runnable = new Runnable() {
            @Override
            public void run() {
                if (isSpinning) {
                    float rotation = ivWheel.getRotation() + rotationIncrement;
                    ivWheel.setRotation(rotation);

                    totalRotation -= rotationIncrement;
                    if (totalRotation <= 0) {
                        isSpinning = false;
                        rotationIncrement = 0;
                        btnSpin.setEnabled(true);

                        // Offset untuk menghentikan sedikit ke bawah
                        ivWheel.setRotation(ivWheel.getRotation() + 10); // Adjust the offset here

                        // Intent to move to the next activity
                        Intent intent = new Intent(Mainspinwheel2.this, Mainscan_KTP.class);
                        startActivity(intent);
                        finish(); // Optional: Close the current activity

                        return;
                    }

                    if (totalRotation < 360) { // Slow down when close to stop
                        rotationIncrement = Math.max(2, rotationIncrement - 1);
                    }

                    handler.postDelayed(this, 20); // Delay for smooth animation
                }
            }
        };

        handler.post(runnable);
    }
}
