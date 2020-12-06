package com.example.fastremindmeby1818101;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<com.example.fastremindmeby1818101.CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList id_catatan, judul, keterangan, jumlah;

    CustomAdapter(Activity activity, Context context, ArrayList id_catatan, ArrayList judul, ArrayList keterangan,
                  ArrayList jumlah){
        this.activity = activity;
        this.context = context;
        this.id_catatan = id_catatan;
        this.judul = judul;
        this.keterangan = keterangan;
        this.jumlah = jumlah;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        holder.id_catatan_txt.setText(String.valueOf(jumlah.get(position)).substring(0,2));
        holder.judul_txt.setText(String.valueOf(judul.get(position)));
        holder.keterangan_txt.setText(String.valueOf(keterangan.get(position)));
        holder.jumlah_txt.setText("ID : "+String.valueOf(id_catatan.get(position)));
        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Updel.class);
                intent.putExtra("id", String.valueOf(id_catatan.get(position)));
                intent.putExtra("judul", String.valueOf(judul.get(position)));
                intent.putExtra("keterangan", String.valueOf(keterangan.get(position)));
                intent.putExtra("jumlah", String.valueOf(jumlah.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });


    }

    @Override
    public int getItemCount() {
        return id_catatan.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView id_catatan_txt, judul_txt, keterangan_txt, jumlah_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id_catatan_txt = itemView.findViewById(R.id.id_catatan_txt);
            judul_txt = itemView.findViewById(R.id.judulcatat_txt);
            keterangan_txt = itemView.findViewById(R.id.keterangan_txt);
            jumlah_txt = itemView.findViewById(R.id.jumlah_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }

    }

}
