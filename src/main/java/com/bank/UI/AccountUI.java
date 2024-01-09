package com.bank.UI;

import java.util.Date;
import java.util.List;
import java.util.Scanner;


import com.bank.Entity.Account;
import com.bank.Entity.AccountLog;
import com.bank.Entity.Current;
import com.bank.Entity.PrivilegeType;
import com.bank.Entity.Savings;
import com.bank.Entity.TransferInfo;
import com.bank.Entity.TransferLog;
import com.bank.Entity.TransferMode;
import com.bank.Serivce.AccountManager;
import com.bank.exception.AccountAlreadyClosedException;
import com.bank.exception.AccountNotActiveException;
import com.bank.exception.AccountNotFoundException;
import com.bank.exception.AgeValidityException;
import com.bank.exception.InsufficientFundsException;
import com.bank.exception.InvalidPinNumberException;
import com.bank.exception.RegistrationNullException;
import com.bank.exception.TransferLimitExceededException;

//Boundary Class- This is interacting with the user
public class AccountUI {
	Account account = null;
	AccountManager accountManager = new AccountManager();
	Scanner scanner = new Scanner(System.in);

	public void receiveCustomer() {

		System.out.println("Hello Sir/Mam. How may i help you");
		while (true) {
			System.out.println("1.open a account\n2.deposit\n3.withdraw\n4.Transfer Funds\n5.close account\n6.Exit");
			System.out.println("enter your choice");
			int choice = scanner.nextInt();
			switch (choice) {
			case 1:
				try {
					openAccount();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 2:
				try {
					deposit(account);
				} catch (AccountNotFoundException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 3:
				try {
					withdraw(account);
				} catch (AccountNotFoundException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 4:
				try {
					transferFunds();
				} catch (AccountNotFoundException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 5:
				try {
					closeAccount(account);
				} catch (AccountNotFoundException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 6:
				System.exit(0);
			default:
				System.out.println("enter valid choice");
			}
		}

	}

	public void openAccount() throws Exception {

		System.out.println("Which account do you want to open: 1. Savings - 2. Current");
		System.out.println("enter your choice");
		int choice1 = scanner.nextInt();
		switch (choice1) {
		case 1:
			System.out.println("Please fill the savings form and hand over to us");
			Savings savings = new Savings();
			System.out.println("enter your name:");
			String name = scanner.next();
			scanner.nextLine();
			savings.setName(name);
			System.out.println("enter your gender:");
			String gender = scanner.next();
			savings.setGender(gender);
			System.out.println("enter your phone number:");
			String phoneno = scanner.next();
			savings.setPhoneNumber(phoneno);

			System.out.println("enter your date of birth ");
			System.out.println("enter date in DD format");
			int date = scanner.nextInt();
			System.out.println("enter month in MM format");
			int month = scanner.nextInt();
			System.out.println("enter year in YYYY format");
			int year = scanner.nextInt();
			Date dateofbirth = new Date(year, month, date);
			savings.setDateOfBirth(dateofbirth);

			System.out.println("choose your previlige \n1.Silver\n2.Gold\n3.Platinum");
			int choice2 = scanner.nextInt();
			switch (choice2) {
			case 1:
				savings.setPrivilege(PrivilegeType.silver);
				break;
			case 2:
				savings.setPrivilege(PrivilegeType.gold);
				break;
			case 3:
				savings.setPrivilege(PrivilegeType.premium);
				break;
			default:
				System.out.println("enter valid choice");
			}

			try {

				account = savings;
				boolean isOpened = accountManager.open(account);
				if (isOpened) {
					for (Account account : AccountLog.getAccounts()) {
						System.out.println(account);
					}
					System.out.println("Account got opened");
					System.out.println("account number is " + account.getAccNo());
					System.out.println("set pin number ");
					int pinNumber = scanner.nextInt();
					account.setPinNumber(pinNumber);
					System.out.println("enter the initial balance you want to deposit");
					double amount=scanner.nextDouble();
					account.setBalance(amount);
					System.out.println("account pin set successfully");
					System.out.println("account atm pin is " + account.getPinNumber());
					System.out.println("balance is "+account.getBalance());
				} else {
					System.out.println("something went wrong");
				}
			} catch (AgeValidityException | RegistrationNullException ex) {
				System.out.println(ex.getMessage());
			}

			break;
		case 2:
			System.out.println("Please fill the current form and hand over to us");
			Current current = new Current();
			System.out.println("enter your name");
			String currentName = scanner.next();
			scanner.nextLine();
			current.setName(currentName);
			System.out.println("enter the company name");
			String comName = scanner.next();
			current.setCompanyName(comName);
			System.out.println("enter the registration number");
			String regNo = scanner.next();
			current.setRegistrationNo(regNo);
			System.out.println("enter the websiteurl of the company");
			String url = scanner.next();
			current.setWebsiteURL(url);

			System.out.println("choose your previlige \n1.Silver\n2.Gold\n3.Platinum");
			int Pchoice1 = scanner.nextInt();
			switch (Pchoice1) {
			case 1:
				current.setPrivilege(PrivilegeType.silver);
				break;
			case 2:
				current.setPrivilege(PrivilegeType.gold);
				break;
			case 3:
				current.setPrivilege(PrivilegeType.premium);
				break;
			default:
				System.out.println("enter valid choice");
			}

			try {
				account = current;
				boolean isOpened = accountManager.open(account);
				if (isOpened) {
					for (Account account : AccountLog.getAccounts()) {
						System.out.println(account);
					}
					System.out.println("Account got opened");
					System.out.println("account number is " + account.getAccNo());
					System.out.println("set pin number ");
					int pinNumber = scanner.nextInt();
					account.setPinNumber(pinNumber);
					System.out.println("account pin set successfully");
					System.out.println("enter initial balance you want to deposit");
					double balance=scanner.nextDouble();
					account.setBalance(balance);
					System.out.println("account atm pin is " + account.getPinNumber());
					System.out.println("balance is "+account.getBalance());

				} else {
					System.out.println("something went wrong");
				}
			} catch (RegistrationNullException | AgeValidityException ex) {
				System.out.println(ex.getMessage());
			}
		}
	}
	public void closeAccount(Account account) throws AccountNotFoundException  {
		System.out.println("enter your account number");
		int accountNumber=scanner.nextInt();
		boolean isPresent=false;
		Account account1=null;
		for(Account account2:AccountLog.getAccounts())
		{
			if(accountNumber==account2.getAccNo())
			{
				account1=account2;
				isPresent=true;
			}
		}
		if(!isPresent)
		{
			throw new AccountNotFoundException("Account Not Found ");
		}
		System.out.println("enter your pin number");
		int pinNumber=scanner.nextInt();
		boolean isClosed = false;

		try {
			isClosed = accountManager.close(account1);
			if (isClosed) {
				System.out.println("Account got closed");
			}
		} catch (AccountAlreadyClosedException e) {
			System.out.println(e.getMessage());
		
		}
	}

	public void deposit(Account account) throws AccountNotFoundException {
		System.out.println("enter the account number");
		int accountNo = scanner.nextInt();
		Account account1=null;
		boolean isPresent=false;
		List<Account>accounts=AccountLog.getAccounts();
		for(Account account2:accounts)
		{
			if(account2.getAccNo()==accountNo)
			{
				account1=account2;
				isPresent=true;
			}
		}
		if(!isPresent)
		{
			throw new AccountNotFoundException("Acccount not found");
		}
		System.out.println("enter the amount to be deposited");
		double amount = scanner.nextDouble();

		try {
			boolean isDeposited = accountManager.deposit(account1, amount);
			if (isDeposited=true) {
				System.out.println(amount + " got deposited in acount");
				System.out.println("your current balance is " + account1.getBalance());
			}
		} catch (AccountNotActiveException e) {
			System.out.println(e.getMessage());
		}
		
	}

	public boolean withdraw(Account account) throws AccountNotFoundException {
		System.out.println("enter the account number");
		int accountNo = scanner.nextInt();
		List<Account>accounts=AccountLog.getAccounts();
		Account account1=null;
		boolean isPresent=false;
		for(Account account2:accounts)
		{
			if(account2.getAccNo()==accountNo)
			{
				account1=account2;
				isPresent=true;
			}
		}
		if(!isPresent)
		{
			throw new AccountNotFoundException("Account not found Exception");
		}
		System.out.println("enter the amount to be withdrawn");
		double amount = scanner.nextDouble();
		System.out.println("enter the pin number");
		int pinnumber = scanner.nextInt();
		try {

			boolean isWithdrawn = accountManager.withdraw(account1, amount, pinnumber);
			if (isWithdrawn=true) {
				System.out.println(amount + "got withdrawn in acount");
				System.out.println("your currrent balance is " + account1.getBalance());
				return true;
			}
		} catch (AccountNotActiveException | InvalidPinNumberException | InsufficientFundsException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	public Account checkAccountIsPresent(int accountNo) {
		for (Account account : AccountLog.getAccounts()) {
			if (accountNo == account.getAccNo()) {
				return account;
			}

		}
		return null;
	}

	public void transferFunds() throws AccountNotFoundException {
		TransferInfo transferInfo = new TransferInfo();

		System.out.println("enter from account number");
		int accountNo = scanner.nextInt();
		Account account = checkAccountIsPresent(accountNo);
		if (account != null) {
			transferInfo.setFromAccount(account);
		} else {
			throw new AccountNotFoundException("Account not found");
		}
		System.out.println("enter your pin number");
		int pinNumber = scanner.nextInt();
		transferInfo.setPinNumber(pinNumber);
		System.out.println("enter to account number");
		int accountNo2 = scanner.nextInt();
		Account account2 = checkAccountIsPresent(accountNo2);
		if (account2 != null) {
			transferInfo.setToAccount(account2);
		} else {
			throw new AccountNotFoundException("Account not found");
		}
		System.out.println("enter the amount you want to transfer");
		double transferAmount = scanner.nextDouble();
		transferInfo.setAmountToTransfer(transferAmount);
		System.out.println("select type of transfer Mode");
		System.out.println("1.NEFT\n2.IMPS\n3.RTGS");
		int choice = scanner.nextInt();
		switch (choice) {
		case 1:
			transferInfo.setTransferMode(TransferMode.NEFT);
			break;
		case 2:
			transferInfo.setTransferMode(TransferMode.IMPS);
			break;
		case 3:
			transferInfo.setTransferMode(TransferMode.RTGS);
			break;
		default:
			System.out.println("enter valid choice");
		}

		double previousBalance1=transferInfo.getFromAccount().getBalance();
		double previousBalance2=transferInfo.getToAccount().getBalance();
		boolean isTrue = false;
		try {
			isTrue = accountManager.transferFunds(transferInfo);
		} catch (AccountNotActiveException | InsufficientFundsException | InvalidPinNumberException
				| TransferLimitExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (isTrue) {


			System.out.println("transaction is successful");
			System.out.println("from account balance before transfer "+previousBalance1);
			System.out.println("to account balance before transfer "+previousBalance2);
			System.out.println("from account balance after transfer "+transferInfo.getFromAccount().getBalance());
			System.out.println("to account balance after transfer "+transferInfo.getToAccount().getBalance());
			System.out.println("transfer log list");
			for(TransferInfo transferInfo2:TransferLog.getTransferLog())
			{
				System.out.println(transferInfo2);
			}
		} 
		else {
			System.out.println(isTrue);
		}
	}
}