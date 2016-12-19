package com.sanchev.dbService;

public class DBException extends Exception {
    DBException(Throwable throwable) {
        super(throwable);
    }
}