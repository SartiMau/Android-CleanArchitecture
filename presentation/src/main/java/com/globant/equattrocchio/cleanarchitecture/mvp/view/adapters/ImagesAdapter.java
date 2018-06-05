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
import com.globant.equattrocchio.cleanarchitecture.util.bus.RxBus;
import com.globant.equattrocchio.cleanarchitecture.util.bus.observers.CallServiceCardObserver;
import com.globant.equattrocchio.domain.enities.Image;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ImagesViewHolder> {

    private List<Image> images;

    public ImagesAdapter(List<Image> images) {
        this.images = images;
    }

    @NonNull
    @Override
    public ImagesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();

        if(context != null){
            View itemView = LayoutInflater.from(context).inflate(R.layout.card,parent,false);
            return new ImagesViewHolder(itemView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ImagesViewHolder holder, int position) {
        holder.cardImageId.setText(String.format("%s %d", holder.itemView.getContext().getResources().getString(R.string.image_id), images.get(position).getId()));

        holder.imageId = images.get(position).getId();

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

    static class ImagesViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.cardImageId) TextView cardImageId;
        @BindView(R.id.cardImage) ImageView cardImage;
        private int imageId;

        public ImagesViewHolder(View parent) {
            super(parent);
            ButterKnife.bind(this, parent);
        }

        @OnClick(R.id.cardImage)
        public void callServiceCardPressed(ImageView cardImage) {
            RxBus.post(new CallServiceCardObserver.CallServiceCardPressed(imageId));
        }
    }
}
