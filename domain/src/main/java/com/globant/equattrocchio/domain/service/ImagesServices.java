package com.globant.equattrocchio.domain.service;

import com.globant.equattrocchio.domain.enities.Image;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.observers.DisposableObserver;

public interface ImagesServices {

    void getLatestImages(Observer<List<Image>> observer);
}
