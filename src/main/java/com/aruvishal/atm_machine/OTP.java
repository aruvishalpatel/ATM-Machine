package com.aruvishal.atm_machine;

import java.util.Random;

public class OTP {

	public String generateOTP(String otpLength) {
		int length = Integer.parseInt(otpLength);
		Random random = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			sb.append(random.nextInt(10));
		}
		String otp = sb.toString();
		//System.out.print(otp);
		return otp;
	}
}
