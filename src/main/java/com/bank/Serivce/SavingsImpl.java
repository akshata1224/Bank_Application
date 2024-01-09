package com.bank.Serivce;

import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

import com.bank.Entity.Account;
import com.bank.Entity.AccountNumberGenerator;
import com.bank.Entity.Savings;
import com.bank.Entity.TransferInfo;
import com.bank.exception.AccountAlreadyClosedException;
import com.bank.exception.AccountNotActiveException;
import com.bank.exception.AgeValidityException;
import com.bank.exception.InsufficientFundsException;
import com.bank.exception.InvalidPinNumberException;
import com.bank.exception.TransferLimitExceededException;

public class SavingsImpl implements AccountImpl {

	@Override
	public boolean open(Account account) throws AgeValidityException {
		Savings savings=(Savings)account;

		int year=savings.getDateOfBirth().getYear();
		int month=savings.getDateOfBirth().getMonth();
		int date=savings.getDateOfBirth().getDate();

		System.out.println();
		LocalDate birthDate=LocalDate.of(year, month, date);
		LocalDate currentDate=LocalDate.now();

		int age= Period.between(birthDate, currentDate).getYears();

		boolean isOpened;
		if(age>=18){
			savings.setActive(true);
			isOpened = true;
			int accno= AccountNumberGenerator.getAccNo();
			savings.setAccNo(accno);


			LocalDate local=LocalDate.now();
			savings.setActivatedDate(local);
		}
		else
		{
			throw new AgeValidityException("Age less than 18 years");
		}

		return isOpened;
	}




}
