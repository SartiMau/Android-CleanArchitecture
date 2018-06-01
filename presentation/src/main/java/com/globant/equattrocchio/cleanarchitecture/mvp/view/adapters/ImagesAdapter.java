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
import com.globant.equattrocchio.domain.enities.Image;

import java.util.List;

import butterknife.BindView;

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ImagesViewHolder> {

    private List<Image> images;

    public ImagesAdapter(Context context, List<Image> images) {
        this.images = images;
    }

    @NonNull
    @Override
    public ImagesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card,parent,false);
        return new ImagesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ImagesViewHolder holder, int position) {
        holder.cardImageId.setText(String.format("{1}{2}", R.string.image_id, images.get(position).getId()));

        Context context = holder.cardImage.getContext();
        if(context != null){
            Glide.with(context)
                    .load(images.get(position).getUrl())
                    .into(holder.cardImage);
        }
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    class ImagesViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.cardImageId) TextView cardImageId;
        @BindView(R.id.cardImage) ImageView cardImage;

        public ImagesViewHolder(View parent) {
            super(parent);
        }
    }
}
