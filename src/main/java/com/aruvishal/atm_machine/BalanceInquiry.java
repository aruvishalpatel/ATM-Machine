package com.aruvishal.atm_machine;

import java.sql.Connection;

import java.util.List;
import java.util.Map;

import com.aruvishal.atm_machine.util.DBConnection;
import com.aruvishal.atm_machine.util.DBUtil;

public class BalanceInquiry {
	public static String savingBalance(String account_number) throws Exception {
		String query = "select saving_balance from account_number_detail where account_number = " + account_number;
		Connection con = DBConnection.getConnection();
		List<Map<String, Object>> result1 = DBUtil.executeSelectQuery(con, query);
		Map<String, Object> out = result1.get(0);
		String saving = out.get("saving_balance").toString();
		return saving;
	}

}
