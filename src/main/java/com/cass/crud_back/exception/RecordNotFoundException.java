package com.cass.crud_back.exception;

public class RecordNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public RecordNotFoundException(Long id) {
        super("Record with the id " + id + " not found");
    }
}
