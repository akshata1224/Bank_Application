package com.bank.exception;

public class AccountAlreadyClosedException extends Exception{
String messege;

public AccountAlreadyClosedException(String messege) {
	super(messege);
}

}
