package com.java.expenseTrackerApi.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message)
    {
        super(message);
    }

}
