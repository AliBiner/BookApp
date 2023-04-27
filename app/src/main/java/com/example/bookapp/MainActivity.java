package com.example.bookapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton btnEkle;
    ImageView imgVeriYok;
    TextView tvVeriYok;
    Database database;

    ArrayList<String> columnId, columnTitle, columnAuthor,columnPages;

    Donustur donustur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        btnEkle = findViewById(R.id.btnEkle);
        imgVeriYok = findViewById(R.id.imgVeriYok);
        tvVeriYok = findViewById(R.id.tvVeriYok);
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
            imgVeriYok.setVisibility(View.VISIBLE);
            tvVeriYok.setVisibility(View.VISIBLE);

        }else{
            while (cursor.moveToNext()){
                columnId.add(cursor.getString(0));
                columnTitle.add(cursor.getString(1));
                columnAuthor.add(cursor.getString(2));
                columnPages.add(cursor.getString(3));
            }
            imgVeriYok.setVisibility(View.GONE);
            tvVeriYok.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.HepsiniSil){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("!Hespi!");
            builder.setMessage("Hepsini mi silmek istiyorsunuz?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Database database1 = new Database(MainActivity.this);
                    database1.HepsiniSil();
                    Intent intent = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.create().show();

        }
        return super.onOptionsItemSelected(item);
    }
}