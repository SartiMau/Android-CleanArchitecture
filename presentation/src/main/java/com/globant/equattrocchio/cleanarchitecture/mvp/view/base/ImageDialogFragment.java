package com.globant.equattrocchio.cleanarchitecture.mvp.view.base;

import android.app.AlertDialog;
import android.app.Dialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.globant.equattrocchio.cleanarchitecture.R;
import com.globant.equattrocchio.domain.enities.Image;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageDialogFragment extends DialogFragment {

    private static final String ID = "ID";
    private static final String URL = "URL";

    private int imageId;
    private String imageUrl;

    @BindView(R.id.cardImage) ImageView cardImage;
    @BindView(R.id.cardImageId) TextView cardImageId;
    @BindView(R.id.cardImageUrl) TextView cardImageUrl;

    public ImageDialogFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imageId = getArguments().getInt(ID);
        imageUrl = getArguments().getString(URL);
    }

    public static ImageDialogFragment newInstance(Image image) {
        ImageDialogFragment imageDialogFragment = new ImageDialogFragment();

        Bundle args = new Bundle();
        args.putInt(ID ,image.getId());
        args.putString(URL, image.getUrl());
        imageDialogFragment.setArguments(args);

        return imageDialogFragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if(getActivity() == null) {
            throw new IllegalStateException();
        }

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_image, null);
        ButterKnife.bind(this, view);

        cardImageId.setText(String.valueOf(imageId));
        cardImageUrl.setText(imageUrl);
        Glide.with(view.getContext())
                .load(imageUrl)
                .into(cardImage);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setPositiveButton(R.string.close_message, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setView(view);

        return builder.create();

    }
}