package com.aruvishal.atm_machine;

import java.sql.Connection;
import java.util.Scanner;

import com.aruvishal.atm_machine.util.DBConnection;
import com.aruvishal.atm_machine.util.DBUtil;

public class OTPVarification {
	private Scanner scanner;
	private boolean tr;

	boolean isValidOTP(String mobile_number) throws Exception {
		scanner = new Scanner(System.in);
		System.out.print("We have sent an OTP to your linked mobile number: " + mobile_number
				+ ". Plese enter the otp for Processing: ");
		boolean valid = true;
		int otpCheak = 0;
		try {
			otpCheak = scanner.nextInt();
		} catch (Exception e) {
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ Invalid entry Sorry!! @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			valid = false;
			tr = false;
		}

		if (valid) {

			String query = "select count(*) as count from otp_varification where mobile_number=" + mobile_number
					+ " and one_time_password=" + otpCheak;
			Connection con = DBConnection.getConnection();
			int count = DBUtil.getresultSetCount(con, query);
			if (count == 0) {
				tr = false;
			} else {
				tr = true;
			}
		}
		return tr;

	}
}