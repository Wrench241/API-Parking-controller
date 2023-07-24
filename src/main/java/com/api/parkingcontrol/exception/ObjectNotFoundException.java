package com.api.parkingcontrol.exception;

public class ObjectNotFoundException extends RuntimeException{
    public ObjectNotFoundException (String msg){
        super(msg);
    }
}
