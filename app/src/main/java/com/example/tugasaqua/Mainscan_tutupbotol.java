package com.example.tugasaqua;

import android.content.Intent;
import android.content.pm.PackageManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.budiyev.android.codescanner.AutoFocusMode;
import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.budiyev.android.codescanner.ErrorCallback;
import com.budiyev.android.codescanner.ScanMode;
import android.widget.ImageView;
import android.view.View;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

public class Mainscan_tutupbotol extends AppCompatActivity {

    private static final int CAMERA_REQUEST_CODE = 101;
    private static final long SCAN_DELAY = 3000L; // Delay in milliseconds (3 seconds)

    private CodeScanner codeScanner;
    private CodeScannerView scannerView;
    private TextView tv_textView;
    private Set<String> scannedCodes = new HashSet<>();
    private boolean isScanEnabled = true; // Flag to control barcode scanning
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scan_tutup_botol);

        ImageView imageView = findViewById(R.id.img_textView);
        imageView.setVisibility(View.INVISIBLE);

        Button menu = findViewById(R.id.vector);
        menu.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainBerandaUtama2.class);
            startActivity(intent);
        });

        Button btnlogin = findViewById(R.id.btnlogin);
        btnlogin.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainBerandaUtama2.class);
            startActivity(intent);
        });

        tv_textView = findViewById(R.id.btnlogin);

        setupPermission();
        scannerView = findViewById(R.id.scanner_view);
        codeScanner();
    }

    private void codeScanner() {
        ImageView imageView = findViewById(R.id.img_textView);
        ImageView imageViewWarning = findViewById(R.id.img_textView_w);
        imageViewWarning.setVisibility(View.INVISIBLE);

        codeScanner = new CodeScanner(this, scannerView);

        codeScanner.setCamera(CodeScanner.CAMERA_BACK);
        codeScanner.setFormats(CodeScanner.ALL_FORMATS);

        codeScanner.setAutoFocusMode(AutoFocusMode.SAFE);
        codeScanner.setScanMode(ScanMode.CONTINUOUS);
        codeScanner.setAutoFocusEnabled(true);
        codeScanner.setFlashEnabled(false);

        codeScanner.setDecodeCallback(result -> runOnUiThread(() -> {
            String scannedCode = result.getText();
            if (isScanEnabled) {
                if (scannedCodes.contains(scannedCode)) {
                    imageView.setVisibility(View.INVISIBLE);
                    imageViewWarning.setVisibility(View.VISIBLE);
                    tv_textView.setText("submit");
                } else {
                    scannedCodes.add(scannedCode);
                    imageView.setVisibility(View.VISIBLE);
                    imageViewWarning.setVisibility(View.INVISIBLE);
                    tv_textView.setText("save");
                    // Update the score
                    updateScore();

                    delayScan();
                }
            }
        }));

        codeScanner.setErrorCallback(error -> runOnUiThread(() -> {
            Log.e("main", "camera error, " + error.getMessage());
        }));

        scannerView.setOnClickListener(view -> {
            if (isScanEnabled) {
                codeScanner.startPreview();
            }
        });
    }

    private void updateScore() {
        // Get the current score from SharedPreferences
        SharedPreferences prefs = getSharedPreferences("myPrefs", MODE_PRIVATE);
        int score = prefs.getInt("score", 30);

        // Add 100 to the score
        score += 1;

        // Save the updated score back to SharedPreferences
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("score", score);
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isScanEnabled) {
            codeScanner.startPreview();
        }
    }

    @Override
    protected void onPause() {
        codeScanner.releaseResources();
        super.onPause();
    }

    private void setupPermission() {
        int permission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            makeRequest();
        }
    }

    private void makeRequest() {
        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CAMERA}, CAMERA_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                codeScanner.startPreview();
            } else {
                Toast.makeText(this, "You need camera permission", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Method to delay barcode scanning for SCAN_DELAY
    private void delayScan() {
        isScanEnabled = false;
        handler.postDelayed(() -> {
            isScanEnabled = true;
            codeScanner.startPreview(); // Restart the preview after delay
        }, SCAN_DELAY);
    }
}
