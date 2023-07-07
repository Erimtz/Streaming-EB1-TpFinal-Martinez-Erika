package com.dh.movie.exception;

public class MovieException extends Exception {

    public MovieException(MessageCode exp){

        super(exp.getMsg());
    }

}
