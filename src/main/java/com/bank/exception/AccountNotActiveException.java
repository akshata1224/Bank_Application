package com.bank.exception;

public class AccountNotActiveException extends Exception {
	String message;

	public AccountNotActiveException(String message) {
		super(message);
	}
	

}
