package org.example.exceptions;

public class UserAlreadyPresentException extends Throwable{

    public UserAlreadyPresentException(String message){

        super(message);

    }
}
