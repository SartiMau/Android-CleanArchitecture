package com.globant.equattrocchio.data.entities;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ImageEntity extends RealmObject {
    @PrimaryKey
    private long id;
    private String url;
    private String largeUrl;

    public ImageEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLargeUrl() {
        return largeUrl;
    }

    public void setLargeUrl(String largeUrl) {
        this.largeUrl = largeUrl;
    }
}
