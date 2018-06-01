package com.globant.equattrocchio.domain.service;

import com.globant.equattrocchio.domain.enities.ImageEntity;

import java.util.List;

import io.reactivex.Observer;

public interface ImagesServices {

    void getLatestImages(Observer<List<ImageEntity>> observer);
}
