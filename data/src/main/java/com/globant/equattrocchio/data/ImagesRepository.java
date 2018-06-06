package com.globant.equattrocchio.data;

import com.globant.equattrocchio.data.entities.ImageEntity;
import com.globant.equattrocchio.domain.enities.Image;

import java.util.List;

import io.realm.Realm;


public class ImagesRepository {

    private static final String ID = "id";

    public static void insertImages(List<Image> images) {
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();

        for (Image image : images) {
            int id = image.getId();

            ImageEntity iEntity = realm.where(ImageEntity.class).equalTo(ID, id).findFirst();

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
