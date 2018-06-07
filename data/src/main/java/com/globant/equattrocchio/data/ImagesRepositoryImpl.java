package com.globant.equattrocchio.data;

import com.globant.equattrocchio.data.entities.ImageEntity;
import com.globant.equattrocchio.data.repository.ImagesRepository;
import com.globant.equattrocchio.domain.enities.Image;

import java.util.List;

import io.realm.Realm;


public class ImagesRepositoryImpl implements ImagesRepository {

    private static final String ID = "id";

    @Override
    public ImageEntity getById(int id) {
        Realm realm = Realm.getDefaultInstance();

        return realm.where(ImageEntity.class).equalTo(ID, id).findFirst();
    }

    @Override
    public void insertImages(List<Image> images) {
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();

        for (Image image : images) {
            int id = image.getId();

            ImageEntity iEntity = getById(id);

            if (iEntity == null) {
                iEntity = realm.createObject(ImageEntity.class, id);
            }

            iEntity.setUrl(image.getUrl());
            iEntity.setLargeUrl(image.getLargeUrl());

            realm.insertOrUpdate(iEntity);
        }
        realm.commitTransaction();
    }
}
