package com.bank.Serivce;


import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.bank.Entity.Account;
import com.bank.Entity.AccountLog;
import com.bank.Entity.TransferInfo;
import com.bank.Entity.TransferLog;
import com.bank.exception.*;

public class AccountManager{
	AccountFactory accountFactory=new AccountFactory();
	public boolean open(Account account) throws Exception {
		AccountImpl accountImpl = accountFactory.open(account);
		boolean isOpened = accountImpl.open(account);

		if(isOpened) {
			AccountLog.getAccounts().add(account);
		}
		return isOpened;
	}

	public boolean close(Account account) throws AccountAlreadyClosedException   
	{
boolean isClosed=false;		
				
					{
						if(!account.isActive()){
							throw new AccountAlreadyClosedException("Account already closed.........");
						}
						else{
							account.setActive(false);
							LocalDate currentDate = LocalDate.now(); 
							account.setClosedDate(currentDate);
							isClosed=true;
						}
					}
					return isClosed;
				}

	public boolean deposit(Account account, double amountToDeposit) throws AccountNotActiveException 
	{
		

				if(!account.isActive())
				{
					throw new AccountNotActiveException("Account not active please active the account to deposit the amount");
				}
				else
				{
					double amount=account.getBalance()+amountToDeposit;
					account.setBalance(amount);
					return true;
				}
			

		
	
		

	}
	public boolean withdraw(Account account, double amountToWithdraw, int pinNumber) throws AccountNotActiveException, InvalidPinNumberException, InsufficientFundsException {

		//Logic here---
		//Check if the account is active - throw AccountNotActiveException
		
				if(account.isActive())
				{
					if(pinNumber==account.getPinNumber())
					{
						if(amountToWithdraw<=account.getBalance())
						{
							double balance=account.getBalance();
							balance-=amountToWithdraw;
							account.setBalance(balance);
							return true;
						}
						else
						{
							throw new InsufficientFundsException("Insufficient balance");
						}
					}
					else
					{
						throw new InvalidPinNumberException("invalid pin please check Once again");
					}
				}
				
				else
				{
					throw new AccountNotActiveException("Account is not active please active the account to deposit the amount");
				}

			

	}

	public boolean transferFunds(TransferInfo transferInfo) throws AccountNotActiveException, InsufficientFundsException, InvalidPinNumberException, TransferLimitExceededException{
		boolean isTransferred = false;
			if(checkIfAccountIsActive(transferInfo.getFromAccount())){
				if(checkIfAccountIsActive(transferInfo.getToAccount())){
					if(checkIfFundsAreAvailable(transferInfo.getFromAccount(), transferInfo.getAmountToTransfer())){
						if(checkIfPinNumberIsValid(transferInfo.getFromAccount(), transferInfo.getPinNumber())){
							if(checkTransferLimitForTheDay(transferInfo.getFromAccount(), transferInfo.getAmountToTransfer())){
								LocalDate currentDate=LocalDate.now();
								transferInfo.setTransferDate(currentDate);
								transferFundsToAccount(transferInfo);
								isTransferred=true;
							}
						}
					}
				}
			}
		return isTransferred;
	}

	private boolean transferFundsToAccount(TransferInfo transferInfo){
		transferInfo.getFromAccount().setBalance(transferInfo.getFromAccount().getBalance()-transferInfo.getAmountToTransfer()) ;
		transferInfo.getToAccount().setBalance(transferInfo.getToAccount().getBalance()+transferInfo.getAmountToTransfer()) ;
		TransferLog.getTransferLog().add(transferInfo);
		
		return true ;

	}

	private boolean checkTransferLimitForTheDay(Account account, double amountToTransfer) throws TransferLimitExceededException{
		double amountAlreadyTransferred = 0;
		for(TransferInfo transferInfo : TransferLog.getTransferLog()){
			LocalDate currentDate=LocalDate.now();
			if(transferInfo.getFromAccount().getAccNo() == account.getAccNo()&&currentDate.equals(transferInfo.getTransferDate())){
				amountAlreadyTransferred+= transferInfo.getAmountToTransfer();
			}
		}
		double transferLimit = PrevilegeManager.getPrivilegeLimitForTransfer(account.getPrivilege());
		if((amountAlreadyTransferred+amountToTransfer) <= transferLimit){
			return true;
		}
		else
			throw new TransferLimitExceededException("transfer limit for the day is exceeded");
	}

	private boolean checkIfPinNumberIsValid(Account account, int pinNumber) throws InvalidPinNumberException{

		if(account.getPinNumber()==pinNumber)
		{
			return true;
		}
		else
		{
			throw new InvalidPinNumberException("Invalid pin number");
		}
	}

	private boolean checkIfAccountIsActive(Account account) throws AccountNotActiveException{

		if(!account.isActive())
			throw new  AccountNotActiveException("Account not activated");

		return true;
	}

	private boolean checkIfFundsAreAvailable(Account account, double amountToTransfer) throws InsufficientFundsException{

		if(account.getBalance()<amountToTransfer){
			throw new InsufficientFundsException("Insufficient balance");
		}

		return true;
	}
}





