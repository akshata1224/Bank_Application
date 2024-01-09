package com.bank.Entity;

import java.time.LocalDate;

public abstract class Account {

	//Structure
	private  int accNo;
	private String name;
	private int pinNumber;
	private double balance;
	private PrivilegeType privilege;
	private boolean isActive;
	private LocalDate activatedDate;
	private LocalDate closedDate;
	
	public Account() {
		super();
	}
	public int getAccNo() {
		return accNo;
	}
	public void setAccNo(int accNo) {
		this.accNo=accNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPinNumber() {
		return pinNumber;
	}
	public void setPinNumber(int pinNumber) {
		this.pinNumber = pinNumber;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public PrivilegeType getPrivilege() {
		return privilege;
	}
	public void setPrivilege(PrivilegeType privilege) {
		this.privilege = privilege;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public LocalDate getActivatedDate() {
		return activatedDate;
	}
	public void setActivatedDate(LocalDate local) {
		this.activatedDate = local;
	}
	public LocalDate getClosedDate() {
		return closedDate;
	}
	public void setClosedDate(LocalDate closedDate) {
		this.closedDate = closedDate;
	}
	@Override
	public String toString() {
		return "Account [accno="+accNo+", name=" + name + ", pinNumber=" + pinNumber + ", balance=" + balance + ", privilege="
				+ privilege + ", isActive=" + isActive + ", activatedDate=" + activatedDate + ", closedDate="
				+ closedDate + "]";
	}

}