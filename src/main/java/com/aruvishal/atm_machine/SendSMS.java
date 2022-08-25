package com.aruvishal.atm_machine;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import javax.net.ssl.HttpsURLConnection;

public class SendSMS {
	public static boolean sendSMS(String mobile, String text) {
		String apiKey = "T1uydBlwb9riQxnkE5mhS8FVzAU6fpLOeqs0g7WvM3RKoDNJZc84zdy0wW9MtETij63GvRbnAPONm1op";
		boolean cheakOffline =false;

		try {
			String sendId = "FSTSMS";
			String language = "english";
			String route = "p";
			String message = text ;
			message = URLEncoder.encode(message, "UTF-8");
			String myUrl = "https://www.fast2sms.com/dev/bulk?authorization=" + apiKey + "&sender_id=" + sendId
					+ "&message=" + message + "&language=" + language + "&route=" + route + "&numbers=" + mobile;
			URL url = new URL(myUrl);
			//System.out.println(myUrl);
			HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", "Mozilla/5.0");
			con.setRequestProperty("cache-control", "no-cache");
			System.out.println("Wait..............");
			@SuppressWarnings("unused")
			int responseCode = con.getResponseCode();
			//System.out.println("Response Code :" + responseCode);
			StringBuffer response = new StringBuffer();
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            
			while (true) {
				String line = br.readLine();

				if (line == null) {
					break;
				}
				response.append(line);
			}
			cheakOffline=true;
			// System.out.println(response);

		} catch (Exception e) {
			//System.out.println("You are in offline mode");
			cheakOffline=false;
			
		}
		return cheakOffline;

	}
}
