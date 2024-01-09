package com.bank.exception;


//Class written to catch if age is not valid for opening of an account
public class AgeValidityException extends Exception{
String message;
public AgeValidityException(String message){
    super(message);
}

}
