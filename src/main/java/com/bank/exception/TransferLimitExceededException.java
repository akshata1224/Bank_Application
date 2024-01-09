package com.bank.exception;

public class TransferLimitExceededException extends Exception {
String message;

public TransferLimitExceededException(String message) {
	super(message);
}


}
