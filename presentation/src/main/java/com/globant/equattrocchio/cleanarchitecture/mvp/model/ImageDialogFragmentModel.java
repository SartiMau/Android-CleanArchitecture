package com.globant.equattrocchio.cleanarchitecture.mvp.model;

public class ImageDialogFragmentModel {

    private int imageId;
    private String imageUrl;

    public ImageDialogFragmentModel(int imageId, String imageUrl) {
        this.imageId = imageId;
        this.imageUrl = imageUrl;
    }

    public int getImageId() {
        return imageId;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
