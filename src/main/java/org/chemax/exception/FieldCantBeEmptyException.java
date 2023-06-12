package org.chemax.exception;

public class FieldCantBeEmptyException extends RuntimeException {

    public FieldCantBeEmptyException() {
        super("Required fields can't be null");
    }
}
