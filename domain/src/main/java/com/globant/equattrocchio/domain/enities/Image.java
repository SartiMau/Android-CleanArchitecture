package com.globant.equattrocchio.domain.enities;

public class Image {

    private Integer id;
    private String url;
    private String largeUrl;

    public Image(Integer id, String url) {
        this.id = id;
        this.url = url;
    }

    public Image(Integer id, String url, String largeUrl) {
        this.id = id;
        this.url = url;
        this.largeUrl = largeUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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