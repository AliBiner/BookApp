package com.example.bookapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class GuncellemeActivity extends AppCompatActivity {
    EditText etKitapAdGuncelle,etYazarGuncelle,etSayfaGuncelle;
    Button btnGuncelle,btnSil;
    String id,kitapAd,yazar, sayfa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guncelleme);
        etKitapAdGuncelle= findViewById(R.id.etKitapAdGuncelle);
        etYazarGuncelle= findViewById(R.id.etYazarGuncelle);
        etSayfaGuncelle= findViewById(R.id.etSayfaGuncelle);
        btnGuncelle= findViewById(R.id.btnGuncelle);
        btnSil= findViewById(R.id.btnSil);
        BilgiGonder();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(kitapAd);
        }
        btnGuncelle.setOnClickListener(v -> {
            kitapAd=etKitapAdGuncelle.getText().toString();
            yazar=etYazarGuncelle.getText().toString();
            sayfa=etSayfaGuncelle.getText().toString();
            Database database = new Database(GuncellemeActivity.this);
            database.BilgiGuncelle(id,kitapAd,yazar,sayfa);
        });

        btnSil.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Kitap: "+ kitapAd);
            builder.setMessage("Silmek istediğinize emin misiniz?");
            builder.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Database database = new Database(GuncellemeActivity.this);
                    database.Sil(id);
                    finish();
                }
            });
            builder.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            builder.create().show();
        });
    }

    void BilgiGonder(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("kitapAd") && getIntent().hasExtra("yazar")
        && getIntent().hasExtra("sayfa")){
                id = getIntent().getStringExtra("id");
            kitapAd = getIntent().getStringExtra("kitapAd");
            yazar = getIntent().getStringExtra("yazar");
            sayfa = getIntent().getStringExtra("sayfa");

            etKitapAdGuncelle.setText(kitapAd);
            etYazarGuncelle.setText(yazar);
            etSayfaGuncelle.setText(sayfa);

        }else{
            Toast.makeText(this, "Veri Yok", Toast.LENGTH_SHORT).show();
        }
    }

}