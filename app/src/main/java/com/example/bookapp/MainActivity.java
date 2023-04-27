package com.example.bookapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton btnEkle;
    Database database;

    ArrayList<String> columnId, columnTitle, columnAuthor,columnPages;

    Donustur donustur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        btnEkle = findViewById(R.id.btnEkle);
        btnEkle.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, EkleActivity.class);
            startActivity(intent);
        });


        database = new Database(MainActivity.this);

        columnId = new ArrayList<>();
        columnTitle = new ArrayList<>();
        columnAuthor = new ArrayList<>();
        columnPages = new ArrayList<>();
        donustur = new Donustur(MainActivity.this,columnId,columnTitle,columnAuthor,columnPages,this);
        recyclerView.setAdapter(donustur);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        BilgiListele();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1){
            recreate();
        }
    }

    void BilgiListele(){
        Cursor cursor = database.BilgiGetir();
        if (cursor.getCount()==0){
            Toast.makeText(this, "Veri Yok", Toast.LENGTH_SHORT).show();

        }else{
            while (cursor.moveToNext()){
                columnId.add(cursor.getString(0));
                columnTitle.add(cursor.getString(1));
                columnAuthor.add(cursor.getString(2));
                columnPages.add(cursor.getString(3));
            }
        }
    }


}