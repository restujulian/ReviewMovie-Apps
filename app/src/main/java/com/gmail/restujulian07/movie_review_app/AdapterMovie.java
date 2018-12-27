package com.gmail.restujulian07.movie_review_app;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class AdapterMovie extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    private List<DataMovie> data;

    AdapterMovie(Context context, List<DataMovie> data){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.item_movie, parent,false);
        MyHolder holder;
        holder = new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyHolder myHolder= (MyHolder) holder;
        DataMovie current=data.get(position);
        myHolder.textJudul.setText(current.judulMovie);
        myHolder.textTanggal.setText(current.tglPosting);
        myHolder.textDeskripsi.setText(current.deskripsiMovie);

        Glide.with(context)
                .load("https://gbiirenonprojek.000webhostapp.com/wservice/images/" + current.gambarMovie)
                .apply(new RequestOptions().placeholder(R.drawable.movie).error(R.drawable.movie))
                .into(myHolder.ivMovie);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        TextView textJudul;
        ImageView ivMovie;
        TextView textTanggal;
        TextView textDeskripsi;

        MyHolder(View itemView) {
            super(itemView);
            textJudul= itemView.findViewById(R.id.judul);
            ivMovie= itemView.findViewById(R.id.imageView2);
            textTanggal = itemView.findViewById(R.id.tanggal);
            textDeskripsi = itemView.findViewById(R.id.deskripsi);
        }
    }
}