package com.globant.equattrocchio.domain.service;

import com.globant.equattrocchio.domain.enities.ImageEntity;

import java.util.List;

import io.reactivex.Observer;

public interface ImagesServices {

//    This is from the first Module's item
    void getJSON(Observer<String> observer);

    void getLatestImages(Observer<List<ImageEntity>> observer);
}
