package com.bank.Entity;

public class AccountNumberGenerator {
private static  int accNo=1001;

public static  int getAccNo() {
	return accNo++;
}
}
