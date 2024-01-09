package com.bank.Entity;

import java.util.ArrayList;
import java.util.List;

public class AccountLog {
static List<Account>accounts=new ArrayList<Account>();

public static List<Account> getAccounts() {
	return accounts;
}

public static void setAccounts(List<Account> accounts) {
	accounts = accounts;
}
public static void addAccount(Account account)
{
	accounts.add(account);
}
}
