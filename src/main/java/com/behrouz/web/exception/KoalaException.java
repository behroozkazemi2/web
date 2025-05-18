package com.behrouz.web.exception;

/**
 * Created By Hapi KZM
 * Package ir.mobintabaran.xima.server.exception
 * Project Koala Server
 * 09 September 2018 16:49
 **/
public class KoalaException extends Throwable {


    private final String description;


    public KoalaException ( String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static void make(String message) throws KoalaException {
        throw new KoalaException( message );
    }


}