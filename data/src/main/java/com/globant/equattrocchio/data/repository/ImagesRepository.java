package com.globant.equattrocchio.data.repository;

import com.globant.equattrocchio.data.entities.ImageEntity;
import com.globant.equattrocchio.domain.enities.Image;

import java.util.List;

public interface ImagesRepository {

    ImageEntity getById(int id);

    void insertImages(List<Image> images);
}
