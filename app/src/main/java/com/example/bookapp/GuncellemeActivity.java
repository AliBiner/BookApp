package com.example.bookapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class GuncellemeActivity extends AppCompatActivity {
    EditText etKitapAdGuncelle,etYazarGuncelle,etSayfaGuncelle;
    Button btnGuncelle;

    String id,kitapAd,yazar, sayfa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guncelleme);

        etKitapAdGuncelle= findViewById(R.id.etKitapAdGuncelle);
        etYazarGuncelle= findViewById(R.id.etYazarGuncelle);
        etSayfaGuncelle= findViewById(R.id.etSayfaGuncelle);
        btnGuncelle= findViewById(R.id.btnGuncelle);
        BilgiGonder();
        btnGuncelle.setOnClickListener(v -> {
            Database database = new Database(GuncellemeActivity.this);
            database.BilgiGuncelle(id,kitapAd,yazar,sayfa);

        });

        


    }

    void BilgiGonder(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("kitapAd") && getIntent().hasExtra("yazar")
        && getIntent().hasExtra("sayfa")){
                this.id = getIntent().getStringExtra("id");
            this.kitapAd = getIntent().getStringExtra("kitapAd");
            this.yazar = getIntent().getStringExtra("yazar");
            this.sayfa = getIntent().getStringExtra("sayfa");

            etKitapAdGuncelle.setText(kitapAd);
            etYazarGuncelle.setText(yazar);
            etSayfaGuncelle.setText(sayfa);
        }else{
            Toast.makeText(this, "Veri Yok", Toast.LENGTH_SHORT).show();
        }
    }

}