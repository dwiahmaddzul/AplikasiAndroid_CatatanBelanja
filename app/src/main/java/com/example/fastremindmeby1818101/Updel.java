package com.example.fastremindmeby1818101;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Updel extends AppCompatActivity {

    EditText judul_input, keterangan_input, jumlah_input;
    Button update_button, delete_button;

    String id, judul, keterangan, jumlah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        judul_input = findViewById(R.id.judul_inputupdate);
        keterangan_input = findViewById(R.id.keterangan_inputupdate);
        jumlah_input = findViewById(R.id.jumlah_inputupdate);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        //First we call this
        getAndSetIntentData();

        //Set actionbar title after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(judul);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this
                MyDatabaseHelper myDB = new MyDatabaseHelper(Updel.this);
                judul = judul_input.getText().toString().trim();
                keterangan = keterangan_input.getText().toString().trim();
                jumlah = jumlah_input.getText().toString().trim();
                myDB.updateData(id, judul, keterangan, jumlah);

                Intent intent = new Intent(Updel.this, com.example.fastremindmeby1818101.MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("judul") &&
                getIntent().hasExtra("keterangan") && getIntent().hasExtra("jumlah")){
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            judul = getIntent().getStringExtra("judul");
            keterangan = getIntent().getStringExtra("keterangan");
            jumlah = getIntent().getStringExtra("jumlah");

            //Setting Intent Data
            judul_input.setText(judul);
            keterangan_input.setText(keterangan);
            jumlah_input.setText(jumlah);
            Log.d("stev", judul+" "+keterangan+" "+ jumlah);
        }else{
            Toast.makeText(this, "Tidak ada catatan.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Hapus catatan ( " + judul + " ) ?");
        builder.setMessage("Anda yakin ingin menghapusnya ?");
        builder.setPositiveButton("Ya!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(Updel.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("Tidak!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}
