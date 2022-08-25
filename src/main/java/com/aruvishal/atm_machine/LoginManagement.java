package com.aruvishal.atm_machine;

import java.sql.Connection;
import com.aruvishal.atm_machine.util.DBConnection;
import com.aruvishal.atm_machine.util.DBUtil;

public class LoginManagement {

	boolean isValidUser(String account_number, String password) throws Exception {

		String query = "select count(*) as count from account_number_detail where account_number=" + account_number
				+ " and password=" + password;
		Connection con = DBConnection.getConnection();
		int count = DBUtil.getresultSetCount(con, query);
		if (count == 0) {
			return false;
		} else {
			return true;
		}

	}
}
