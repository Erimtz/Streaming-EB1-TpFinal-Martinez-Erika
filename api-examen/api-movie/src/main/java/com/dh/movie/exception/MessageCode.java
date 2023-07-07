package com.dh.movie.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MessageCode {

    MOVIE_NOT_FOUND("The movie not found"),
    MOVIE_EXISTS("The movie exists by id, name and genre");

    String msg;
}
