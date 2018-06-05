package com.globant.equattrocchio.cleanarchitecture.mvp.presenter;

import com.globant.equattrocchio.cleanarchitecture.mvp.model.ImageDialogFragmentModel;
import com.globant.equattrocchio.cleanarchitecture.mvp.view.ImageDialogFragmentView;

public class ImageDialogFragmentPresenter {

    private ImageDialogFragmentView view;
    private ImageDialogFragmentModel model;

    public ImageDialogFragmentPresenter(ImageDialogFragmentView view, ImageDialogFragmentModel model) {
        this.view = view;
        this.model = model;
    }

    public void loadData() {
        this.view.setCardImageIdText(String.valueOf(model.getImageId()));
        this.view.setCardImageUrlText(model.getImageUrl());
        this.view.loadImage(model.getImageUrl());
    }
}
