package com.bank.exception;

public class InvalidPinNumberException extends Exception{
String message;

public InvalidPinNumberException(String message) {
	super(message);
}

}
