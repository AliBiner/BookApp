package com.example.bookapp;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class EkleActivity extends AppCompatActivity {
    EditText etKitapAd, etYazar, etSayfa;
    Button btnEkle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ekle);

        etKitapAd=findViewById(R.id.etKitapAd);
        etYazar=findViewById(R.id.etYazar);
        etSayfa=findViewById(R.id.etSayfa);
        btnEkle = findViewById(R.id.btnEkle);
        btnEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Database database = new Database(EkleActivity.this);
                database.KitapEkle(Integer.valueOf(etSayfa.getText().toString()),etKitapAd.getText().toString(),etYazar.getText().toString());

            }
        });
    }
}