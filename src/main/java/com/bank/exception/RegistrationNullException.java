package com.bank.exception;

public class RegistrationNullException extends Exception{
String message;

public RegistrationNullException(String message) {
	super(message);
}

}
