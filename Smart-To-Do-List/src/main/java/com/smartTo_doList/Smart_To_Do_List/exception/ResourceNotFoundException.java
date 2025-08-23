package com.smartTo_doList.Smart_To_Do_List.exception;
/**
 * Custom exception thrown when a requested resource is not found.
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}