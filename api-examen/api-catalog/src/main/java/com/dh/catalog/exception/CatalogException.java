package com.dh.catalog.exception;

public class CatalogException extends Exception {

    public CatalogException(MessageCode exp) {
        super(exp.getMsg());
    }
}
