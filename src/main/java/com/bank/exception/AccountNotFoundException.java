package com.bank.exception;

public class AccountNotFoundException extends Exception {
String message;

public AccountNotFoundException(String message) {
	super(message);
}

}
