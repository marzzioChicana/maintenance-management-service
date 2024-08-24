package com.marzz.maintenance_management_service.exception;

public class ValidationException extends RuntimeException{
    public ValidationException() {}

    public ValidationException(String message) {super(message);}
}
