package com.java.expenseTrackerApi.exceptions;

public class ItemAlreadyExistsException extends RuntimeException{

    public ItemAlreadyExistsException(String message)
    {
        super(message);
    }
}
