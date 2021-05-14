package com.quircode.testcarso.View;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.quircode.testcarso.Model.Pelis;
import com.quircode.testcarso.Net.ApiNetClient;
import com.quircode.testcarso.R;
import com.quircode.testcarso.insiderActivity;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class recyclerPelisAdapter extends RecyclerView.Adapter<recyclerPelisAdapter.MyViewHolder> implements View.OnClickListener {

    private List<Pelis> pelisList;
    private Context context;
    View.OnClickListener listener;

    public recyclerPelisAdapter(List<Pelis> pelisList, Context context) {
        this.pelisList = pelisList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pelis_item,parent,false);
        view.setOnClickListener(this);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerPelisAdapter.MyViewHolder holder, int position) {
        holder.pelisTItleItem.setText(pelisList.get(position).getTitle());
        Glide.with(context).load(ApiNetClient.URL_IMAGE+pelisList.get(position).getPosterPath()).into(holder.pelisImageItem);

    }

    @Override
    public int getItemCount() {
        return pelisList.size();
    }

    @Override
    public void onClick(View v) {
        if (listener != null){
            listener.onClick(v);
        }
    }
    public void setOnClickListener (View.OnClickListener listener){
        this.listener = listener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView pelisImageItem;
        TextView pelisTItleItem;

        public MyViewHolder(@NonNull  View itemView) {
            super(itemView);

            pelisImageItem = (ImageView)itemView.findViewById(R.id.pelisImageItem);
            pelisTItleItem = (TextView) itemView.findViewById(R.id.pelisTItleItem);

        }
    }
}
