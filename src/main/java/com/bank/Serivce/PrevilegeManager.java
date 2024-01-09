package com.bank.Serivce;

import com.bank.Entity.PrivilegeType;

public class PrevilegeManager {
	public static double getPrivilegeLimitForTransfer(PrivilegeType privilegeType){

		if(privilegeType==PrivilegeType.silver)
		{
			return 25000;
		}
		else if(privilegeType==PrivilegeType.gold)
		{
			return 50000;
		}
		else if(privilegeType==PrivilegeType.premium)
		{
			return 100000;
		}
		return 0;
	} 
}
