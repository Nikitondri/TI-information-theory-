package com.zakharenko.lab04.dal.exception;

public class DalException extends Exception{
    public DalException() {
        super();
    }

    public DalException(String message) {
        super(message);
    }

    public DalException(String message, Throwable cause) {
        super(message, cause);
    }

    public DalException(Throwable cause) {
        super(cause);
    }

    protected DalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
