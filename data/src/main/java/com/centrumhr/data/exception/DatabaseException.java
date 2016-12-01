package com.centrumhr.data.exception;

/**
 * Created by Seweryn on 02.10.2016.
 */
public class DatabaseException extends RuntimeException {
    public DatabaseException() {
    }

    public DatabaseException(Throwable cause) {
        super(cause);
    }

    public DatabaseException(String message) {
        super(message);
    }
}
