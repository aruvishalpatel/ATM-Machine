package com.aruvishal.atm_machine;

import java.sql.Connection;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.aruvishal.atm_machine.util.DBConnection;
import com.aruvishal.atm_machine.util.DBUtil;
import com.aruvishal.atm_machine.util.EmailUtil;

public class CreatingManagement {

	@SuppressWarnings({ "unused", "static-access" })
	public String CreateAccount(String name, String mobile_number, String e_mail, String date_of_birth, String address,
			String password) throws Exception {
		int psswrd = Integer.parseInt(password);
		long number = Long.parseLong(mobile_number);
		String response = "";

		if (psswrd < 1000000 && psswrd > 99999 && number <= 10000000000L && number > 999999999L) {
			String query19 = "insert into date_of_birth_management value('" + date_of_birth + "')";
			Connection con19 = DBConnection.getConnection();
			boolean isVlideDateOfBirth = DBUtil.executInsertUpdateDelete(con19, query19);
			if (isVlideDateOfBirth) {
				PasswordManagement pass = new PasswordManagement();
				OTP op = new OTP();
				System.out.println("Program Started...................");
				String otp = op.generateOTP("5");
				// System.out.println("Generate OTP :" + otpmassage);
				String mobileText = "your new account varification OTP:" + otp;

				boolean outcome = true;
				if (outcome)

				{

					String uuid = UUID.randomUUID().toString();
					String query = "insert into account_number_detail(id,name, mobile_number, e_mail,date_of_birth,address, password,saving_balance) value('"
							+ uuid + "','" + name + "', '" + mobile_number + "','" + e_mail + "','" + date_of_birth
							+ "','" + address + "','" + password + "' ,0)";
					Connection con = DBConnection.getConnection();
					boolean result = DBUtil.executInsertUpdateDelete(con, query);

					if (result) {
						Connection con2 = DBConnection.getConnection();
						String query2 = "select account_number , regisration_date_time from account_number_detail where id='"
								+ uuid + "'";
						List<Map<String, Object>> result2 = DBUtil.executeSelectQuery(con2, query2);

						Map<String, Object> out = result2.get(0);
						String res = out.get("account_number").toString();
						String date_time = out.get("regisration_date_time").toString();
						System.out.println("Program started ..........");
						System.out.println("Wait.......");
						

						String text = "I AM A STUDENT OF IIIT VADODARA GUJRAT : Dear user your A/c:" + res
								+ " is registerd succesfully on " + date_time;
						String subject = "Bank Account";
						EmailUtil mail = new EmailUtil();
						boolean isOfline = true;

						response = "Your Account number -: " + res + " :-  is created Successfully at " + date_time;

					} else {
						response = "Error in user details";
					}

				} else {

					response = "Your OTP is not match ";
				}
			} else {
				System.out.println(
						"\n\n\n#########################  Error in Date Of Birth detail ##############################");
			}
		} else {
			response = "Error in password or mobile number ";

		}
		return response;
	}

}
