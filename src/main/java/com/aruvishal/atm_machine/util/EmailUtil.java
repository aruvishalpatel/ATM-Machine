package com.aruvishal.atm_machine.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

public class EmailUtil {
	public static boolean isSentMail(String e_mail, String subject, String text) throws IOException {
		try 
		{
			//System.out.println("Start Processing .........");
			String message = URLEncoder.encode(text, "UTF-8");
			String sub = URLEncoder.encode(subject, "UTF-8");
			String myUrl = "https://first-class-hydraul.000webhostapp.com/mail.php?mail=" + e_mail + "&subject="+ sub + "&msg=" + message;
			URL url = new URL(myUrl);
			HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", "Mozilla/5.0");
			con.setRequestProperty("cache-control", "no-cache");
			//System.out.println("Wait..............");
			@SuppressWarnings("unused")
			int responseCode = con.getResponseCode();
			// System.out.println("Response Code :" + responseCode);
			StringBuffer response = new StringBuffer();
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));

			while (true) {
				String line = br.readLine();

				if (line == null) {
					break;
				}
				response.append(line);
			}

		} catch (Exception e) {
			System.out.print(e.toString());
		}
		return true;

	}
}