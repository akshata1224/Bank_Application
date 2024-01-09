package com.bank.Serivce;

import com.bank.Entity.Account;
import com.bank.exception.AgeValidityException;
import com.bank.exception.RegistrationNullException;

public interface AccountImpl {
	boolean open(Account account) throws AgeValidityException, RegistrationNullException;
}
