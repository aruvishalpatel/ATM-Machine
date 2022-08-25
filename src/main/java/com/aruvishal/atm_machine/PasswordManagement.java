package com.aruvishal.atm_machine;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;





	import java.sql.Connection;
	import com.aruvishal.atm_machine.util.DBConnection;
	import com.aruvishal.atm_machine.util.DBUtil;

	public class PasswordManagement {

		private boolean retrn;

		boolean sendOTP(String otp, String mobile_number,  String message) throws Exception {
			String apiKey = "t6UceqYMd10HAyJu9szIoN3DkPvG25hrZC4iKEWV8lbFmajgpfUDXNnLbAguR39VqzhZfFBcM4TGaIyk";


			try {
				String sendId = "FSTSMS";
				String language = "english";
				String route = "p";
			
				message = URLEncoder.encode(message, "UTF-8");
				
				String myUrl = "https://www.fast2sms.com/dev/bulk?authorization=" + apiKey + "&sender_id=" + sendId
						+ "&message=" + message + "&language=" + language + "&route=" + route + "&numbers=" + mobile_number;
				URL url = new URL(myUrl);
				//System.out.println(myUrl);
				HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
				con.setRequestMethod("GET");
				con.setRequestProperty("User-Agent", "Mozilla/5.0");
				con.setRequestProperty("cache-control", "no-cache");
				System.out.println("Wait..............");
				//System.out.println(otp);
				@SuppressWarnings("unused")
				int responseCode = con.getResponseCode();
				//System.out.println("Response Code :" + responseCode);
				StringBuffer response = new StringBuffer();
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));

				retrn = false;
				while (true) {
					String line = br.readLine();

					if (line == null) {
						break;
					}
					response.append(line);
				}

				// System.out.println(response);
				String query8 = "select count(*) as count from otp_varification where mobile_number=" + mobile_number;
				Connection con8 = DBConnection.getConnection();
				int count = DBUtil.getresultSetCount(con8, query8);
				if (count == 1) {
					String query2 = "update otp_varification set one_time_password=" + otp + " where mobile_number= "
							+ mobile_number;
					Connection con3 = DBConnection.getConnection();
					boolean output = DBUtil.executInsertUpdateDelete(con3, query2);
					if (output) {
						OTPVarification varification = new OTPVarification();
						boolean cheaking = varification.isValidOTP(mobile_number);
						if (cheaking) {
							retrn = true;

						} else {
							retrn = false;
						}
					} else {
						System.out.println(
								"%%%%%%%%%%%%%%%%%%%%%%%%%%%%%: Sorry!!! :%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
					}

				}
 else 
				 {
					String query2 = "insert into otp_varification value( " + mobile_number + "," + otp + ")";
					Connection con3 = DBConnection.getConnection();
					boolean output = DBUtil.executInsertUpdateDelete(con3, query2);
					if (output) {
						OTPVarification varification = new OTPVarification();
						boolean cheaking = varification.isValidOTP(mobile_number);
						if (cheaking) {
							retrn = true;

						} else {
							retrn = false;
						}
					} else {
						System.out.println(
								"%%%%%%%%%%%%%%%%%%%%%%%%%%%%%: Sorry!!! :%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
					}

				}
				
				
				//
				
			} catch (Exception e) {
				retrn=false;
			}
			return retrn;
		}

	}