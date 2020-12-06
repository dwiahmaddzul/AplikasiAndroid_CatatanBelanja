package com.example.fastremindmeby1818101;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "Fastremindme.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "ingatbelanja";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_JUDUL = "judul_pengingat";
    private static final String COLUMN_KETERANGAN = "keterangan";
    private static final String COLUMN_JUMLAH = "jumlah";

    MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_JUDUL + " TEXT, " +
                        COLUMN_KETERANGAN + " TEXT, " +
                        COLUMN_JUMLAH + " INTEGER);";
        db.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void TambahIngat(String judul, String keterangan, int jumlah){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_JUDUL, judul);
        cv.put(COLUMN_KETERANGAN, keterangan);
        cv.put(COLUMN_JUMLAH, jumlah);
        long result = db.insert(TABLE_NAME,null, cv);
        if(result == -1){
            Toast.makeText(context, "GAGAL DITAMBAHKAN", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Berhasil ditambahkan!", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateData(String row_id, String judul, String keterangan, String jumlah){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_JUDUL, judul);
        cv.put(COLUMN_KETERANGAN, keterangan);
        cv.put(COLUMN_JUMLAH, jumlah);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "GAGAL DIPERBARUI", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Berhasil diperbarui!", Toast.LENGTH_SHORT).show();
        }

    }

    void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "GAGAL DIHAPUS", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Berhasil dihapus", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }

}
