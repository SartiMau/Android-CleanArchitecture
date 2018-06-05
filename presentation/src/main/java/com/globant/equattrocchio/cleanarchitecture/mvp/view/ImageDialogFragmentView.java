package com.globant.equattrocchio.cleanarchitecture.mvp.view;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.globant.equattrocchio.cleanarchitecture.R;
import com.globant.equattrocchio.cleanarchitecture.mvp.view.base.ImageDialogFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageDialogFragmentView extends FragmentView{

    @BindView(R.id.cardImage) ImageView cardImage;
    @BindView(R.id.cardImageId) TextView cardImageId;
    @BindView(R.id.cardImageUrl) TextView cardImageUrl;

    public ImageDialogFragmentView(ImageDialogFragment fragment) {
        super(fragment);
        ButterKnife.bind(this, fragment.getView());
    }

    public void setCardImageIdText(String imageId){
        cardImageId.setText(imageId);
    }

    public void setCardImageUrlText(String imageUrl){
        cardImageUrl.setText(imageUrl);
    }

    public void loadImage(String imageUrl){
        Glide.with(getContext())
                .load(imageUrl)
                .into(cardImage);
    }



}
