package com.globant.equattrocchio.cleanarchitecture.mvp.view.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.globant.equattrocchio.cleanarchitecture.R;
import com.globant.equattrocchio.domain.enities.ImageEntity;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewViewHolder> {

    private Context context;
    private List<ImageEntity> images;

    public RecyclerViewAdapter(Context context, List<ImageEntity> images) {
        this.context = context;
        this.images = images;
    }

    @NonNull
    @Override
    public RecyclerViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card,parent,false);
        return new RecyclerViewViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewViewHolder holder, int position) {
        holder.getCardImageId().setText("Image ID: " + images.get(position).getId());
        Glide.with(context)
                .load(images.get(position).getUrl())
                .into(holder.getCardImage());
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public static class RecyclerViewViewHolder extends RecyclerView.ViewHolder{

        private final TextView cardImageId;
        private final ImageView cardImage;

        public RecyclerViewViewHolder(View parent) {
            super(parent);
            cardImageId = itemView.findViewById(R.id.cardImageId);
            cardImage = itemView.findViewById(R.id.cardImage);
        }

        public TextView getCardImageId(){
            return cardImageId;
        }

        public ImageView getCardImage(){
            return cardImage;
        }
    }
}
