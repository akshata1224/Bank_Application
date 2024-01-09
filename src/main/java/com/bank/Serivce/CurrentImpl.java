package com.bank.Serivce;

import java.time.LocalDate;
import java.util.Scanner;

import com.bank.Entity.Account;
import com.bank.Entity.AccountNumberGenerator;
import com.bank.Entity.Current;
import com.bank.Entity.TransferInfo;
import com.bank.exception.AccountAlreadyClosedException;
import com.bank.exception.AccountNotActiveException;
import com.bank.exception.InsufficientFundsException;
import com.bank.exception.InvalidPinNumberException;
import com.bank.exception.RegistrationNullException;
import com.bank.exception.TransferLimitExceededException;

public class CurrentImpl implements AccountImpl{
	@Override
	public boolean open(Account account) throws RegistrationNullException {
		Current current=(Current)account;
		boolean isOpened = false;
		if(current.getRegistrationNo()!=null)
		{
			current.setActive(true);

			int accno= AccountNumberGenerator.getAccNo();
			current.setAccNo(accno);
			LocalDate local=LocalDate.now();
			current.setActivatedDate(local);
			isOpened=true;
		}
		else
		{
			throw new RegistrationNullException("Registration number is null");
		}
		return isOpened;
	}

}
