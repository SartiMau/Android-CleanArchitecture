package com.globant.equattrocchio.domain;

import com.globant.equattrocchio.domain.enities.Image;
import com.globant.equattrocchio.domain.repository.ImagesRepository;

import java.util.List;

import io.realm.RealmChangeListener;

public class LoadImagesUseCase {

    private ImagesRepository imagesRepository;

    public LoadImagesUseCase(ImagesRepository imagesRepository) {
        this.imagesRepository = imagesRepository;
    }

    public List<Image> loadAllImages() {
        return imagesRepository.loadAllImages();
    }

    public void addChangeListener(RealmChangeListener changeListener) {
        imagesRepository.addChangeListener(changeListener);
    }
}
