package com.dh.apiserie.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MessageCode {
    SERIE_NOT_FOUND("The serie not found"),
    SERIE_EXISTS("The serie exists by id, name and genre");

    String msg;
}
