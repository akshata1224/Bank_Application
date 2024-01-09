package com.bank.Serivce;

import com.bank.Entity.Account;
import com.bank.Entity.Current;
import com.bank.Entity.Savings;

public class AccountFactory {
	public AccountImpl open(Account account) throws Exception
	{
		
		if(account instanceof Savings)
		{
			return new SavingsImpl();
		}
		else if (account instanceof Current) {
			return new CurrentImpl();
		}
throw new Exception();
	}
	


}
