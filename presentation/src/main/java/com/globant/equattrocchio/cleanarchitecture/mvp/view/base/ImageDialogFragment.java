package com.globant.equattrocchio.cleanarchitecture.mvp.view.base;

import android.app.AlertDialog;
import android.app.Dialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.app.DialogFragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.globant.equattrocchio.cleanarchitecture.R;
import com.globant.equattrocchio.cleanarchitecture.mvp.model.ImageDialogFragmentModel;
import com.globant.equattrocchio.cleanarchitecture.mvp.presenter.ImageDialogFragmentPresenter;
import com.globant.equattrocchio.cleanarchitecture.mvp.view.ImageDialogFragmentView;
import com.globant.equattrocchio.domain.enities.Image;

public class ImageDialogFragment extends DialogFragment {

    private static final String ID = "ID";
    private static final String URL = "URL";

    private int imageId;
    private String imageUrl;

    private ImageDialogFragmentModel model;
    private ImageDialogFragmentPresenter presenter;

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return LayoutInflater.from(getActivity()).inflate(R.layout.dialog_image, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        presenter = new ImageDialogFragmentPresenter(new ImageDialogFragmentView(this), new ImageDialogFragmentModel(imageId, imageUrl));

        presenter.loadData();
    }
}