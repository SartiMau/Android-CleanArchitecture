package com.globant.equattrocchio.data.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetLatestImagesResponse {
    @SerializedName("images")
    @Expose
    private List<ImageResponse> imageResponses;

    public List<ImageResponse> getImages() {
        return imageResponses;
    }

    public void setImages(List<ImageResponse> imageResponses) {
        this.imageResponses = imageResponses;
    }

}