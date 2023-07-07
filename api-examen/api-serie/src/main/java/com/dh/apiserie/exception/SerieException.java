package com.dh.apiserie.exception;

public class SerieException extends Exception {
    public SerieException(MessageCode exp){
        super(exp.getMsg());
    }
}
