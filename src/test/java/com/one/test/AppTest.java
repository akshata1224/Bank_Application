package com.one.test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.function.BooleanSupplier;

import org.junit.jupiter.api.Test;

import com.bank.Serivce.AccountManager;
import com.bank.exception.*;
import com.bank.Entity.Account;
import com.bank.Entity.Current;
import com.bank.Entity.PrivilegeType;
import com.bank.Entity.Savings;
import com.bank.Entity.TransferInfo;
import com.bank.Entity.TransferLog;

/**
 * Unit test for simple App.
 */
public class AppTest {

	AccountManager manager = new AccountManager();
	@Test
	void openSavingsAccountTest1() throws Exception {
		Savings account1 = new Savings();
		Date date = new Date(2001, 01, 01);
		account1.setDateOfBirth(date);
		account1.setAccNo(1000);

		TransferLog.storeaccount.add(account1);

		boolean openAccountTestStatus = false;
		openAccountTestStatus = manager.open(account1);

		assertTrue(openAccountTestStatus);
	}

	@Test
	void openSavingsAccountTest2() throws Exception {
		Savings account1 = new Savings();
		Date date = new Date(2018, 01, 01);
		account1.setDateOfBirth(date);
		account1.setAccNo(1000);
//		TransferLog.storeaccount.add(account1);
		assertThrows(AgeValidityException.class, () -> manager.open(account1),"age invalid exception");

	}
	@Test
	void openCurrentAccountTest1() throws Exception {
		Current account1 = new Current();
		account1.setAccNo(1000);
		account1.setRegistrationNo("10011");
//		TransferLog.storeaccount.add(account1);

		boolean openAccountTestStatus = false;
		openAccountTestStatus = manager.open(account1);

		assertTrue(openAccountTestStatus);
	}

	@Test
	void openCurrentAccountTest2() throws Exception {
		Current account1 = new Current();
		account1.setAccNo(1000);
		account1.setRegistrationNo(null);
//		TransferLog.storeaccount.add(account1);
		assertThrows(RegistrationNullException.class, () -> manager.open(account1));

	}

	@Test
	void withdrawMethodTest1()
			throws AccountAlreadyClosedException, InsufficientFundsException, InvalidPinNumberException, AccountNotActiveException, AccountNotFoundException {

		Account account1 = new Savings();
		account1.setPinNumber(1234);
		account1.setBalance(10000);
		account1.setActive(true);

//		TransferLog.storeaccount.add(account1);

		boolean withdrwaMethodTestStatus = false;
		withdrwaMethodTestStatus = manager.withdraw(account1, 1000, 1234);

		assertTrue(withdrwaMethodTestStatus);
	}
	
	@Test
	void withdrawMethodTest2()
			throws AccountAlreadyClosedException, InsufficientFundsException, InvalidPinNumberException, AccountNotActiveException {

		Account account1 = new Savings();
		account1.setPinNumber(1234);
		account1.setBalance(10000);
		account1.setActive(false);

//		TransferLog.storeaccount.add(account1);

		assertThrows(AccountNotActiveException.class, () ->manager.withdraw(account1, 1000, 1234));
	}
	
	@Test
	void withdrawMethodTest3()
			throws AccountAlreadyClosedException, InsufficientFundsException, InvalidPinNumberException, AccountNotActiveException {

		Account account1 = new Savings();
		account1.setPinNumber(1234);
		account1.setBalance(10000);
		account1.setActive(true);

//		TransferLog.storeaccount.add(account1);

		assertThrows(InsufficientFundsException.class, () ->manager.withdraw(account1, 1000000, 1234));
	}
	
	@Test
	void withdrawMethodTest4()
			throws AccountAlreadyClosedException, InsufficientFundsException, InvalidPinNumberException, AccountNotActiveException {

		Account account1 = new Savings();
		account1.setPinNumber(1234);
		account1.setBalance(10000);
		account1.setActive(true);

//		TransferLog.storeaccount.add(account1);

		assertThrows(InvalidPinNumberException.class, () ->manager.withdraw(account1, 1000, 1233));
	}

	@Test
	void withdrawMethodTest5()
			throws AccountAlreadyClosedException, InsufficientFundsException, InvalidPinNumberException, AccountNotActiveException {

		Account account1 = new Savings();
		account1.setPinNumber(1234);
		account1.setBalance(10000);
		account1.setActive(true);

//		TransferLog.storeaccount.add(account1);

		boolean withdrwaMethodTestStatus = false;
		withdrwaMethodTestStatus = manager.withdraw(account1, 1000, 1234);

		assertTrue(withdrwaMethodTestStatus);
	}

	
		@Test
		void depositAccountTest1() throws AccountNotActiveException {
			Account account = new Savings();
			account.setActive(true);
//			TransferLog.storeaccount.add(account);
			boolean depositTestStatus = false;
				depositTestStatus = manager.deposit(account, 1000);
	
			assertTrue(depositTestStatus);
	
		}
		@Test
		void depositAccountTest2() throws AccountNotActiveException {
			Account account = new Savings();
			account.setActive(false);
//			TransferLog.storeaccount.add(account);
				assertThrows(AccountNotActiveException.class, () ->manager.deposit(account, 1000));

	
		}
		
		@Test
		public void closeAccountTest1() throws AccountAlreadyClosedException  {
			Savings account1 = new Savings();
			account1.setAccNo(1000);
			account1.setActive(true);
			assertTrue(manager.close(account1));
		}
		
		@Test
		public void closeAccountTest2() throws Exception {
			Savings account1 = new Savings();
			account1.setAccNo(1000);
			account1.setActive(false);
			assertThrows(AccountAlreadyClosedException.class, () -> manager.close(account1));
		}
		
	
		@Test
		void TransferFundsAccountTest1() throws AccountNotActiveException, InsufficientFundsException, InvalidPinNumberException, TransferLimitExceededException {
			Account account1 = new Savings();
			account1.setActive(true);
			account1.setPinNumber(1234);
			account1.setBalance(100000);
			account1.setPrivilege(PrivilegeType.gold);
			Account account2 = new Savings();
			account2.setActive(true);
	
//			TransferLog.storeaccount.add(account1);
//			TransferLog.storeaccount.add(account2);
			TransferInfo info = new TransferInfo();
			info.setFromAccount(account1);
			info.setToAccount(account2);
			info.setPinNumber(1234);
			info.setAmountToTransfer(100);
	
			boolean TransferFundsTestStatus = false;
				TransferFundsTestStatus = manager.transferFunds(info);
	
			assertTrue(TransferFundsTestStatus);
	
		}
		
		@Test
		void TransferFundsAccountTest2() {
			Account account1 = new Savings();
			account1.setActive(false);
			account1.setPinNumber(1234);
			account1.setBalance(100000);
			account1.setPrivilege(PrivilegeType.gold);
			Account account2 = new Savings();
			account2.setActive(true);
	
//			TransferLog.storeaccount.add(account1);
//			TransferLog.storeaccount.add(account2);
			TransferInfo info = new TransferInfo();
			info.setFromAccount(account1);
			info.setToAccount(account2);
			info.setPinNumber(1234);
			info.setAmountToTransfer(100);

			assertThrows(AccountNotActiveException.class, () -> manager.transferFunds(info));

	
		}
		
		
		@Test
		void TransferFundsAccountTest3() {
			Account account1 = new Savings();
			account1.setActive(true);
			account1.setPinNumber(1234);
			account1.setBalance(100000);
			account1.setPrivilege(PrivilegeType.gold);
			Account account2 = new Savings();
			account2.setActive(false);
	
//			TransferLog.storeaccount.add(account1);
//			TransferLog.storeaccount.add(account2);
			TransferInfo info = new TransferInfo();
			info.setFromAccount(account1);
			info.setToAccount(account2);
			info.setPinNumber(1234);
			info.setAmountToTransfer(100);
			assertThrows(AccountNotActiveException.class, () -> manager.transferFunds(info));

		}
		
		
		@Test
		void TransferFundsAccountTest4() {
			Account account1 = new Savings();
			account1.setActive(true);
			account1.setPinNumber(1234);
			account1.setBalance(100000);
			account1.setPrivilege(PrivilegeType.gold);
			Account account2 = new Savings();
			account2.setActive(true);
	
//			TransferLog.storeaccount.add(account1);
//			TransferLog.storeaccount.add(account2);
			TransferInfo info = new TransferInfo();
			info.setFromAccount(account1);
			info.setToAccount(account2);
			info.setPinNumber(1233);
			info.setAmountToTransfer(100);
	
			assertThrows(InvalidPinNumberException.class, () -> manager.transferFunds(info));

		}
		
		
		@Test
		void TransferFundsAccountTest5() {
			Account account1 = new Savings();
			account1.setActive(true);
			account1.setPinNumber(1234);
			account1.setBalance(1000);
			account1.setPrivilege(PrivilegeType.gold);
			Account account2 = new Savings();
			account2.setActive(true);
	
//			TransferLog.storeaccount.add(account1);
//			TransferLog.storeaccount.add(account2);
			TransferInfo info = new TransferInfo();
			info.setFromAccount(account1);
			info.setToAccount(account2);
			info.setPinNumber(1234);
			info.setAmountToTransfer(10000);
	
			assertThrows(InsufficientFundsException.class, () -> manager.transferFunds(info));

		}
		
		
		@Test
		void TransferFundsAccountTest6() {
			Account account1 = new Savings();
			account1.setActive(true);
			account1.setPinNumber(1234);
			account1.setBalance(100000);
			account1.setPrivilege(PrivilegeType.gold);
			Account account2 = new Savings();
			account2.setActive(true);
	
//			TransferLog.storeaccount.add(account1);
//			TransferLog.storeaccount.add(account2);
			TransferInfo info = new TransferInfo();
			info.setFromAccount(account1);
			info.setToAccount(account2);
			info.setPinNumber(1234);
			info.setAmountToTransfer(70000);
	
			assertThrows(TransferLimitExceededException.class, () -> manager.transferFunds(info));

		}
		
		

		}
	
		

