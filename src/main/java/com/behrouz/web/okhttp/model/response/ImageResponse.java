package com.behrouz.web.okhttp.model.response;

/**
 * Created By Hapi KZM
 * Package ir.mobintabaran.xima.server.api.customer.response
 * Project server
 * 30 September 2018 13:13
 **/
public class ImageResponse {

    private byte[] image;
    private int id;
    private boolean deleted;

    public ImageResponse() {}
    public byte[] getImage() {
        return image;
    }
    public void setImage( byte[] image ) {
        this.image = image;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
