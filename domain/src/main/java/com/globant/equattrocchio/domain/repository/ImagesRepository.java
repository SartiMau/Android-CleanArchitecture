package com.globant.equattrocchio.domain.repository;

import com.globant.equattrocchio.domain.enities.Image;

import java.util.List;

import io.realm.RealmChangeListener;

public interface ImagesRepository {

    void insertImages(List<Image> images);

    List<Image> loadAllImages();

    void addChangeListener(RealmChangeListener changeListener);

    void deleteImage(int id);
}
