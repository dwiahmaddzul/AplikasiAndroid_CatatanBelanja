package com.example.fastremindmeby1818101;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Tambah extends AppCompatActivity {

    EditText judul_input, keterangan_input, jumlah_input;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        judul_input = findViewById(R.id.Judul_pengingat);
        keterangan_input = findViewById(R.id.keterangan);
        jumlah_input = findViewById(R.id.jumlah_input);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new com.example.fastremindmeby1818101.MyDatabaseHelper(Tambah.this);
                myDB.TambahIngat(judul_input.getText().toString().trim(),
                        keterangan_input.getText().toString().trim(),
                        Integer.valueOf(jumlah_input.getText().toString().trim()));
                Intent intent = new Intent(Tambah.this, com.example.fastremindmeby1818101.MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
