package com.behrouz.web.okhttp.model.request;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created By Hapi KZM
 * Package ir.mobintabaran.xima.server.rest.request
 * Project Koala Server
 * 10 September 2018 12:32
 **/
public class ProductImageRequest {

    private int id; // age mikhad Edit kone, sikh nazan

    private int imageOrder;

    private byte[] image;

    private MultipartFile imageFile;

    public int getId() {
        return id;
    }
    public void setId( int id ) {
        this.id = id;
    }



    public int getImageOrder() {
        return imageOrder;
    }
    public void setImageOrder( int imageOrder ) {
        this.imageOrder = imageOrder;
    }



    public byte[] getImage() {
        return image;
    }
    public void setImage( byte[] image ) {
        this.image = image;
    }



    public MultipartFile getImageFile() {
        return imageFile;
    }
    public void setImageFile(MultipartFile imageFile) {
        this.imageFile = imageFile;
    }


}
