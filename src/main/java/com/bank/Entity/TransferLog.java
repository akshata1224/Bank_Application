package com.bank.Entity;

import java.util.ArrayList;
import java.util.List;


public class TransferLog {
	  private static List<TransferInfo> transferLog = new ArrayList<TransferInfo>();
		public static List<Account> storeaccount = new ArrayList<>();

	public static List<TransferInfo> getTransferLog() {
		return transferLog;
	}

	public static void setTransferLog(List<TransferInfo> transferLog) {
		TransferLog.transferLog = transferLog;
	}

}
