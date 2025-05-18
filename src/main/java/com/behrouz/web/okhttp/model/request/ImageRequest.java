package com.behrouz.web.okhttp.model.request;


/**
 * Created By Hapi KZM
 * Company: reza
 * Package: ir.mobintabaran.xima.server.api.customer.request
 * Project Name: xima-server
 * 15 June 2020
 **/
public class ImageRequest {

    private int id;

    private byte[] image;

    private boolean deleted;

    public ImageRequest(byte[] byteImage) {
        this.image = byteImage;
    }

    public ImageRequest(int id) {
        this.id = id;
    }

    public ImageRequest(int id, boolean deleted) {
        this.id = id;
        this.deleted = deleted;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public ImageRequest(int id, byte[] image) {
        this.id = id;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }


    public ImageRequest() {
    }


    @Override
    public String toString() {
        return "ImageRequest{" +
                "id=" + id +
                ", image=" + (image == null ? "Null" : "Filled") +
                ", deleted=" + deleted +
                '}';
    }
}