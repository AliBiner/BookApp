package com.example.bookapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    private final Context context;
    private static final String dbName = "KitapUygulaması.db";
    private static final int dbVersion=1;

    private static final String tableName = "kitaplar2";
    private static final String columnId = "_id";
    private static final String columnTitle = "baslik";
    private static final String columnAuthor = "yazar";
    private static final String columnPages="sayfalar";


    Database(@Nullable Context context) {
        super(context,dbName,null,dbVersion);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "Create TABLE " + tableName + " (" + columnId + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + columnTitle + " TEXT, " + columnAuthor + " TEXT, " + columnPages + " INTEGER);";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("drop table if exists "+ tableName);
            onCreate(sqLiteDatabase);
    }

    void  KitapEkle(int sayfalar,String baslik,String yazar){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(columnTitle,baslik);
        contentValues.put(columnAuthor,yazar);
        contentValues.put(columnPages,sayfalar);

        long result = sqLiteDatabase.insert(tableName,null,contentValues);
        if(result==-1){
            Toast.makeText(context, "İşlem Başarısız!", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "İşlem Başarılı :)", Toast.LENGTH_SHORT).show();
        }
    }
    Cursor BilgiGetir(){
        String query = "SELECT * FROM " + tableName;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = null;
        if (sqLiteDatabase != null){
            cursor = sqLiteDatabase.rawQuery(query,null);
        }
        return cursor;
    }
    public void BilgiGuncelle(String id ,String kitapAd, String yazar, String sayfalar ) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(columnTitle, kitapAd);
        contentValues.put(columnAuthor, yazar);
        contentValues.put(columnPages, sayfalar);

        long result = sqLiteDatabase.update(tableName, contentValues, "_id=?", new String[]{id});

        if (result == -1) {
            Toast.makeText(context, "Güncelleme Başarısız :(", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Güncelleme Başarılı :)", Toast.LENGTH_SHORT).show();
        }
    }

    public void Sil(String id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        long result = sqLiteDatabase.delete(tableName, "_id=?", new String[]{id});
        if (result == -1){
            Toast.makeText(context, "Silme İşlemi Başarısız :(", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Silme İşlemi Başarılı :)", Toast.LENGTH_SHORT).show();
        }
    }

    public void HepsiniSil(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.execSQL("DELETE FROM " + tableName);
    }

}