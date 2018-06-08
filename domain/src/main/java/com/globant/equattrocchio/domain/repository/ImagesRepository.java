package com.globant.equattrocchio.domain.repository;

import com.globant.equattrocchio.domain.enities.Image;

import java.util.List;

public interface ImagesRepository {

    void insertImages(List<Image> images);

    List<Image> loadAllImages();

    Object getAllImageEntities();

    void deleteImage(int id);
}
