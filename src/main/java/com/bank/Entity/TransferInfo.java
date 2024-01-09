package com.bank.Entity;

import java.time.LocalDate;

public class TransferInfo {
	  private Account fromAccount;
	  private Account toAccount;
	  private double amountToTransfer;
	  private int pinNumber;
	  private LocalDate transferDate;
	  private TransferMode transferMode;
	public Account getFromAccount() {
		return fromAccount;
	}
	public void setFromAccount(Account fromAccount) {
		this.fromAccount = fromAccount;
	}
	public double getAmountToTransfer() {
		return amountToTransfer;
	}
	public void setAmountToTransfer(double amountToTransfer) {
		this.amountToTransfer = amountToTransfer;
	}
	public Account getToAccount() {
		return toAccount;
	}
	public void setToAccount(Account toAccount) {
		this.toAccount = toAccount;
	}
	public int getPinNumber() {
		return pinNumber;
	}
	public void setPinNumber(int pinNumber) {
		this.pinNumber = pinNumber;
	}
	public TransferMode getTransferMode() {
		return transferMode;
	}
	public void setTransferMode(TransferMode transferMode) {
		this.transferMode = transferMode;
	}
	
	public LocalDate getTransferDate() {
		return transferDate;
	}
	public void setTransferDate(LocalDate currentDate) {
		this.transferDate = currentDate;
	}
	@Override
	public String toString() {
		return "TransferInfo [fromAccount=" + fromAccount + ", toAccount=" + toAccount + ", amountToTransfer="
				+ amountToTransfer + ", pinNumber=" + pinNumber + ", transferDate=" + transferDate + ", transferMode="
				+ transferMode + "]";
	}
	
	
	  
}
