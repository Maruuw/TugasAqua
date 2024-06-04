package com.example.tugasaqua;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.budiyev.android.codescanner.AutoFocusMode;
import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.budiyev.android.codescanner.ErrorCallback;
import com.budiyev.android.codescanner.ScanMode;

public class Mainscan_KTP extends AppCompatActivity {
    private static final int CAMERA_REQUEST_CODE = 101;
    private CodeScanner codeScanner;
    private CodeScannerView scannerView;
    private TextView tv_textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scan_ktp);

        tv_textView = findViewById(R.id.tv_textView);

        setupPermission();
        scannerView = findViewById(R.id.scanner_view);
        codeScanner();
    }

    private void codeScanner() {
        codeScanner = new CodeScanner(this, scannerView);

        codeScanner.setCamera(CodeScanner.CAMERA_BACK);
        codeScanner.setFormats(CodeScanner.ALL_FORMATS);
        codeScanner.setAutoFocusMode(AutoFocusMode.SAFE);
        codeScanner.setScanMode(ScanMode.CONTINUOUS);
        codeScanner.setAutoFocusEnabled(true);
        codeScanner.setFlashEnabled(true);

        codeScanner.setDecodeCallback(result -> runOnUiThread(() -> tv_textView.setText(result.getText())));

        codeScanner.setErrorCallback(error -> runOnUiThread(() -> Log.e("main", "camera error, " + error.getMessage())));

        scannerView.setOnClickListener(view -> codeScanner.startPreview());
    }

    @Override
    protected void onResume() {
        super.onResume();
        codeScanner.startPreview();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void setupPermission() {
        int permission = ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.CAMERA
        );

        if (permission != PackageManager.PERMISSION_GRANTED) {
            makeRequest();
        }
    }

    private void makeRequest() {
        ActivityCompat.requestPermissions(
                this,
                new String[]{android.Manifest.permission.CAMERA},
                CAMERA_REQUEST_CODE
        );
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            @NonNull String[] permissions,
            @NonNull int[] grantResults
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                codeScanner.startPreview();
            } else {
                Toast.makeText(this, "You need camera permission", Toast.LENGTH_SHORT).show();
            }
        }
    }
}