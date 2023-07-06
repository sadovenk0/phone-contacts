package com.app.phonecontacts.exception;

public class DuplicateItemsException extends RuntimeException {
    public DuplicateItemsException() {    }

    public DuplicateItemsException(String message) {
        super(message);
    }
}
