package com.globant.equattrocchio.data;

import com.globant.equattrocchio.data.entities.ImageEntity;
import com.globant.equattrocchio.domain.enities.Image;
import com.globant.equattrocchio.domain.repository.ImagesRepository;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;


public class ImagesRepositoryImpl implements ImagesRepository {

    private static final String ID = "id";
    private RealmResults<ImageEntity> images;

    public ImageEntity getById(long id) {
        Realm realm = Realm.getDefaultInstance();

        return realm.where(ImageEntity.class).equalTo(ID, id).findFirst();
    }

    @Override
    public void insertImages(List<Image> images) {
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();

        //delete all data from Realm to test the Listener
        realm.deleteAll();
        //delete all data from Realm to test the Listener

        for (Image image : images) {
            long id = image.getId();

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

    @Override
    public List<Image> loadAllImages() {
        Realm realm = Realm.getDefaultInstance();

        RealmResults<ImageEntity> realmImages = realm.where(ImageEntity.class).findAll();

        return transform(realmImages);
    }

    @Override
    public void addChangeListener(RealmChangeListener realmChangeListener){
        Realm realm = Realm.getDefaultInstance();

        images = realm.where(ImageEntity.class).findAll();

        images.addChangeListener(realmChangeListener);
    }

    @Override
    public void deleteImage(int id) {
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();

        ImageEntity iEntity = getById(id);
        iEntity.deleteFromRealm();

        realm.commitTransaction();
    }

    private List<Image> transform(RealmResults<ImageEntity> realmImages) {
        List<Image> images = new ArrayList<Image>();
        for (ImageEntity image : realmImages) {
            images.add(new Image((int) image.getId(), image.getUrl(), image.getLargeUrl()));
        }
        return images;
    }
}