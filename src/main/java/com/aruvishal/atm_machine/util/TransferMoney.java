package com.aruvishal.atm_machine.util;

import java.sql.Connection;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.aruvishal.atm_machine.BalanceInquiry;

public class TransferMoney {
	@SuppressWarnings({ "static-access", "unused" })
	public void transferedMoney(String account_number, String target_account, int money) throws Exception

	{
		System.out.println("Start Processing..................");
		System.out.println("Wait..................");
		BalanceInquiry balanceinquiry = new BalanceInquiry();
		String saving = balanceinquiry.savingBalance(account_number);
		Double saving_balance = Double.parseDouble(saving);

		String target = balanceinquiry.savingBalance(target_account);
		Double target_balance = Double.parseDouble(target);

		String query5 = "select mobile_number,e_mail from account_number_detail where account_number= "
				+ target_account;
		Connection con5 = DBConnection.getConnection();
		List<Map<String, Object>> result1 = DBUtil.executeSelectQuery(con5, query5);
		Map<String, Object> out = result1.get(0);
		String targetNumber = out.get("mobile_number").toString();
		String targetMail = out.get("e_mail").toString();

		String query6 = "select mobile_number,name,e_mail from account_number_detail where account_number= "
				+ account_number;
		Connection con6 = DBConnection.getConnection();
		List<Map<String, Object>> result3 = DBUtil.executeSelectQuery(con6, query6);
		Map<String, Object> out1 = result3.get(0);
		String sourceNumber = out1.get("mobile_number").toString();
		String sourceMail = out1.get("e_mail").toString();
		String name = out1.get("name").toString();

		String uuid = UUID.randomUUID().toString();
		String query21 = "insert into date_time(id) value('" + uuid + "')";
		Connection con21 = DBConnection.getConnection();
		boolean result21 = DBUtil.executInsertUpdateDelete(con21, query21);
		String query22 = "select time from date_time where id='" + uuid + "'";
		List<Map<String, Object>> result2 = DBUtil.executeSelectQuery(con21, query22);
		Map<String, Object> out32 = result2.get(0);
		String date_time = out32.get("time").toString();

		String query = "update account_number_detail set saving_balance=" + (saving_balance - money)
				+ " where account_number=" + account_number;
		String query1 = "update account_number_detail set saving_balance=" + (target_balance + money)
				+ " where account_number=" + target_account;
		String query2 = "insert into transaction_histry(target_account,source_account,transactional_money) value('"
				+ target_account + "','"+account_number+"','" + money + "')";
		Connection con = DBConnection.getConnection();
		boolean sav = DBUtil.executInsertUpdateDelete(con, query);

		Connection con1 = DBConnection.getConnection();
		boolean tar = DBUtil.executInsertUpdateDelete(con1, query1);

		Connection con2 = DBConnection.getConnection();
		boolean his = DBUtil.executInsertUpdateDelete(con2, query2);

		if (sav && tar && his) {

			System.out.println("Sending money = " + money + " from " + account_number + " to " + target_account);
			System.out.println("\nNow you have remaining amount in your account = Rs." + (saving_balance - money));
			String text1 = "Dear User, ur A/c: " + account_number + "-debited by Rs" + money + ".00 on " + date_time
					+ " .Go for more details www.sbi.com";
			String text2 = "Dear User, ur A/c:" + target_account + " credited by Rs" + money + " .00 on " + date_time
					+ " by " + name + "(A/c:" + account_number + ") .Go for more details www.sbi.com";
			String balance = Integer.toString(money);
			EmailUtil emailUtil = new EmailUtil();
			String subject = "IIITV ATM Machine Project Testing";
			boolean first = emailUtil.isSentMail(sourceMail, subject, text1);
			boolean second = emailUtil.isSentMail(targetMail, subject, text2);
			System.out.println(
					"\n\n\n#####################################: Thank you for using this ATM Machine :##################################### ");

		} else {
			System.out.println("++++++++++++ Sorry ATM Machine have some problems +++++++++++++++++++++");
			System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% Your transaction cancelled now %%%%%%%%%%%%%%%%%%%%%%%");
			System.out.println(
					"\n\n\n#####################################: Thank you for using this ATM Machine :##################################### ");

		}

	}

}
