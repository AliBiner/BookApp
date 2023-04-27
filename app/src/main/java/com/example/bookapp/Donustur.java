package com.example.bookapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class Donustur extends RecyclerView.Adapter<Donustur.ViewHolder> {
    ArrayList kitapId, kitapAd, yazar, sayfa;
    Context context;
    Activity activity;

    Donustur(Activity activity, ArrayList kitapId, ArrayList kitapAd, ArrayList yazar, ArrayList sayfa, Context context) {
        this.activity=activity;
        this.kitapId = kitapId;
        this.kitapAd = kitapAd;
        this.yazar = yazar;
        this.sayfa = sayfa;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.list,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

            holder.tvKitapId.setText(String.valueOf(kitapId.get(position)));
            holder.tvKitapAd.setText(String.valueOf(kitapAd.get(position)));
            holder.tvKitapYazar.setText(String.valueOf(yazar.get(position)));
            holder.tvSayfa.setText(String.valueOf(sayfa.get(position)));
            holder.listLayout.setTag(holder);
            holder.listLayout.setOnClickListener(v -> {
               Intent intent = new Intent(context,GuncellemeActivity.class);
               intent.putExtra("id",String.valueOf(kitapId.get(position)));
               intent.putExtra("kitapAd",String.valueOf(kitapAd.get(position)));
                intent.putExtra("yazar",String.valueOf(yazar.get(position)));
               intent.putExtra("sayfa",String.valueOf(sayfa.get(position)));
              activity.startActivityForResult(intent,1);
            });
    }

    @Override
    public int getItemCount() {
        return kitapId.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvKitapId, tvKitapAd, tvKitapYazar,tvSayfa;
        LinearLayout listLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvKitapId = itemView.findViewById(R.id.tvKitapId);
            tvKitapAd = itemView.findViewById(R.id.tvKitapAd);
            tvKitapYazar = itemView.findViewById(R.id.tvKitapYazar);
            tvSayfa = itemView.findViewById(R.id.tvSayfa);
            listLayout = itemView.findViewById(R.id.listLayout);
        }
    }
}
