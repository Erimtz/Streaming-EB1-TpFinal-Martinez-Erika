package com.dh.catalog.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MessageCode {

    CATALOG_NOT_FOUND("The catalog not found, circuit breaker is active"),
    CATALOG_NOT_ACTIVE("The catalog not active"),
    CATALOG_MOVIE_NOT_FOUND("The movie not found"),
    CATALOG_SERIE_NOT_FOUND("The serie not found"),
    MOVIE_NOT_LIMIT("Limit exceeded"), CATALOG_NOT_TOTAL_AMOUNT_EQUALS("Verify details amounts");

    String msg;

}
