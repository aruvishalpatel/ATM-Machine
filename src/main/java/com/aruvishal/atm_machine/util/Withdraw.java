package com.aruvishal.atm_machine.util;

import java.sql.Connection;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

public class Withdraw {
	@SuppressWarnings("unused")
	private int updateATMBalance;
	@SuppressWarnings("unused")
	private boolean output;
	private Scanner scanner;

	@SuppressWarnings({ "static-access", "unused" })
	public void cashManagement(String Required, String account_number, String password, String denomination)
			throws Exception {

		scanner = new Scanner(System.in);
		int moneyRequired = Integer.parseInt(Required);
		int denomi = Integer.parseInt(denomination);
		int checkMoney = moneyRequired;
		String massageMoney = Required;
		// System.out.println(checkMoney);
		String query = "select saving_balance , mobile_number,e_mail from account_number_detail where account_number = "
				+ account_number;
		Connection con = DBConnection.getConnection();
		List<Map<String, Object>> result1 = DBUtil.executeSelectQuery(con, query);
		Map<String, Object> out = result1.get(0);
		String saving = out.get("saving_balance").toString();
		String number = out.get("mobile_number").toString();
		String mail = out.get("e_mail").toString();
		// System.out.println(mail);

		double saving_balance = Double.parseDouble(saving);

		// System.out.println(saving_balance);

		int typeNote[] = { 100, 200, 500, 2000 };
		int noteCollection[] = { 0, 0, 0, 0 };
		for (int i = 0; i < 4; i++) {
			String query1 = "select note_of_" + typeNote[i] + " from atm_machine_balance";
			Connection con1 = DBConnection.getConnection();
			List<Map<String, Object>> result3 = DBUtil.executeSelectQuery(con1, query1);
			Map<String, Object> out1 = result3.get(0);
			String saving1 = out1.get("note_of_" + typeNote[i]).toString();
			int allNote = Integer.parseInt(saving1);
			noteCollection[i] = allNote;
			// System.out.println(noteCollection[i]);

		}

		int noteCount[] = { 0, 0, 0, 0 };
		int atmMoney = ((100 * noteCollection[0]) + (200 * noteCollection[1]) + (500 * noteCollection[2])
				+ (2000 * noteCollection[3]));

		if (moneyRequired <= atmMoney && moneyRequired <= saving_balance) {
			{
				if (denomi == 2000) {
					if (noteCollection[3] != 0) {
						for (int i = 1; i <= noteCollection[3]; i++) {
							if (moneyRequired < 2000) {
								break;
							} else {

								moneyRequired = moneyRequired - 2000;
								noteCount[0] = noteCount[0] + 1;
							}
						}
					}
					if (noteCollection[2] != 0) {
						for (int i = 1; i <= noteCollection[2]; i++) {
							if (moneyRequired < 500) {
								break;
							} else {
								moneyRequired = moneyRequired - 500;
								noteCount[1] = noteCount[1] + 1;
							}
						}
					}
					if (noteCollection[1] != 0) {
						for (int i = 1; i <= noteCollection[1]; i++) {
							if (moneyRequired < 200) {
								break;
							} else {
								moneyRequired = moneyRequired - 200;
								noteCount[2] = noteCount[2] + 1;
							}
						}
					}
					if (noteCollection[0] != 0) {

						for (int i = 1; i <= noteCollection[0]; i++) {
							if (moneyRequired == 0) {
								break;
							}
							moneyRequired = moneyRequired - 100;
							noteCount[3] = noteCount[3] + 1;
						}

					}
				} else if (denomi == 500) {
					if (noteCollection[2] != 0) {
						for (int i = 1; i <= noteCollection[2]; i++) {
							if (moneyRequired < 500) {
								break;
							} else {
								moneyRequired = moneyRequired - 500;
								noteCount[1] = noteCount[1] + 1;
							}
						}
					}
					if (noteCollection[1] != 0) {
						for (int i = 1; i <= noteCollection[1]; i++) {
							if (moneyRequired < 200) {
								break;
							} else {
								moneyRequired = moneyRequired - 200;
								noteCount[2] = noteCount[2] + 1;
							}
						}
					}
					if (noteCollection[0] != 0) {

						for (int i = 1; i <= noteCollection[0]; i++) {
							if (moneyRequired == 0) {
								break;
							}
							moneyRequired = moneyRequired - 100;
							noteCount[3] = noteCount[3] + 1;
						}

					}
					if (noteCollection[3] != 0) {
						for (int i = 1; i <= noteCollection[3]; i++) {
							if (moneyRequired < 2000) {
								break;
							} else {

								moneyRequired = moneyRequired - 2000;
								noteCount[0] = noteCount[0] + 1;
							}
						}
					}
				} else if (denomi == 200) {
					if (noteCollection[1] != 0) {
						for (int i = 1; i <= noteCollection[1]; i++) {
							if (moneyRequired < 200) {
								break;
							} else {
								moneyRequired = moneyRequired - 200;
								noteCount[2] = noteCount[2] + 1;
							}
						}
					}
					if (noteCollection[0] != 0) {

						for (int i = 1; i <= noteCollection[0]; i++) {
							if (moneyRequired == 0) {
								break;
							}
							moneyRequired = moneyRequired - 100;
							noteCount[3] = noteCount[3] + 1;
						}

					}

					if (noteCollection[2] != 0) {
						for (int i = 1; i <= noteCollection[2]; i++) {
							if (moneyRequired < 500) {
								break;
							} else {
								moneyRequired = moneyRequired - 500;
								noteCount[1] = noteCount[1] + 1;
							}
						}
					}
					if (noteCollection[3] != 0) {
						for (int i = 1; i <= noteCollection[3]; i++) {
							if (moneyRequired < 2000) {
								break;
							} else {

								moneyRequired = moneyRequired - 2000;
								noteCount[0] = noteCount[0] + 1;
							}
						}
					}
				} else if (denomi == 100) {
					if (noteCollection[0] != 0) {

						for (int i = 1; i <= noteCollection[0]; i++) {
							if (moneyRequired == 0) {
								break;
							}
							moneyRequired = moneyRequired - 100;
							noteCount[3] = noteCount[3] + 1;
						}

					}
					if (noteCollection[1] != 0) {
						for (int i = 1; i <= noteCollection[1]; i++) {
							if (moneyRequired < 200) {
								break;
							} else {
								moneyRequired = moneyRequired - 200;
								noteCount[2] = noteCount[2] + 1;
							}
						}
					}
					if (noteCollection[2] != 0) {
						for (int i = 1; i <= noteCollection[2]; i++) {
							if (moneyRequired < 500) {
								break;
							} else {
								moneyRequired = moneyRequired - 500;
								noteCount[1] = noteCount[1] + 1;
							}
						}
					}
					if (noteCollection[3] != 0) {
						for (int i = 1; i <= noteCollection[3]; i++) {
							if (moneyRequired < 2000) {
								break;
							} else {

								moneyRequired = moneyRequired - 2000;
								noteCount[0] = noteCount[0] + 1;
							}
						}
					}
				} else {
					System.out.println("+++++++++++++: Some error in machine :++++++++++++");
				}
			}
		}
		if (moneyRequired != 0) {
			// System.out.println(checkMoney);

			if (checkMoney > saving_balance && checkMoney < atmMoney) {
				System.out.println(
						"\n\n+++++++++++++++++++++: Sorry U have not sufficient balance :++++++++++++++++++++++++++ \n\n\n");
				System.out.println(" Press 1 for check: Account Balance :");
				System.out.println(" Press 2 for exit :");
				System.out
						.println("++++++++++++++-: Please choose which opration are you going to do :-++++++++++++++ ");
				boolean isValidCondition = true;
				int choice1 = 0;
				try {
					choice1 = Integer.parseInt(scanner.nextLine());
				} catch (Exception e) {
					System.out
							.println("\n\n\n--------------------------: Invalid key :--------------------------- \n\n");
					System.out.println(
							"\n\n\n###############################: Thank you for using this ATM Machine :############################### ");

					isValidCondition = false;
				}
				if (isValidCondition) {
					switch (choice1) {
					case 1: {
						System.out.println("Total Available balance in your Account is = " + saving_balance + " Rs.");
						System.out.println(
								"\n\n\n###############################: Thank you for using this ATM Machine :############################### ");

						break;
					}
					case 2: {
						System.out.println(
								"\n\n\n###############################: Thank you for using this ATM Machine :############################### ");
						break;
					}
					default: {
						System.out.println(
								"\n\n\n###############################: Thank you for using this ATM Machine :############################### ");
						break;
					}
					}
				}
			} else if (checkMoney > atmMoney && checkMoney < saving_balance) {
				System.out.println(
						"\n\n\n-------------------: ATM have not sufficient balance Sorry!! :------------------- \n\n\n");
				System.out.println(" Press 1 for check: ATM Balence :");
				System.out.println(" Press 2 for exit :");
				System.out
						.println("++++++++++++++-: Please choose which opration are you going to do :-++++++++++++++ ");
				boolean isValid = true;
				int choice = 0;
				try {
					choice = Integer.parseInt(scanner.nextLine());
				} catch (Exception e) {
					System.out
							.println("\n\n\n--------------------------: Invalid key :--------------------------- \n\n");
					System.out.println(
							"\n\n\n###############################: Thank you for using this ATM Machine :############################### ");

					isValid = false;
				}
				if (isValid) {
					switch (choice) {
					case 1: {
						System.out.println("            Note of 100 is = " + noteCollection[0]);
						System.out.println("            Note of 200 is = " + noteCollection[1]);
						System.out.println("            Note of 500 is = " + noteCollection[2]);
						System.out.println("            Note of 2,000 is = " + noteCollection[3]);
						System.out.println("Total Available balance in ATM is = " + atmMoney + " Rs.");
						System.out.println(
								"\n\n\n###############################: Thank you for using this ATM Machine :############################### ");

						break;
					}
					case 2: {
						System.out.println(
								"\n\n\n###############################: Thank you for using this ATM Machine :############################### ");
						break;
					}
					default: {
						System.out.println(
								"\n\n\n###############################: Thank you for using this ATM Machine :############################### ");
						break;
					}
					}
				}
			} else {
				System.out.println(
						"\n\n\n+++++++++++++++++++++++: Sorry ATM Machine is not eligible for your Opration :+++++++++++++++++++++\n\n\n");
				System.out.println("$$$$$$$$$$$$$$$$$$$: ATM DETAILS :$$$$$$$$$$$$$$$$$$$$$$$$ \n");
				System.out.println("            Note of 100 is = " + noteCollection[0]);
				System.out.println("            Note of 200 is = " + noteCollection[1]);
				System.out.println("            Note of 500 is = " + noteCollection[2]);
				System.out.println("            Note of 2,000 is = " + noteCollection[3]);
				System.out.println("Total Available balance in ATM is = " + atmMoney + " Rs.");
				System.out.println("\n\n\n$$$$$$$$$$$$$$$$$$: ACCOUNT DETAILS :$$$$$$$$$$$$$$$$$$$$$$$$\n");
				System.out.println("Total Available balance in your account is = " + saving_balance + " Rs.");
				System.out.println(
						"\n\n\n###############################: Thank you for using this ATM Machine :############################### ");

			}
		} else {

			int tatalAmount = ((2000 * noteCount[0]) + (500 * noteCount[1]) + (200 * noteCount[2])
					+ (100 * noteCount[3]));

			double updateBalance = (saving_balance) - (checkMoney);
			String query3 = "update account_number_detail set saving_balance =" + updateBalance
					+ "where account_number =  " + account_number;
			Connection con3 = DBConnection.getConnection();
			boolean result = DBUtil.executInsertUpdateDelete(con3, query3);
			String uuid = UUID.randomUUID().toString();
			String query21 = "insert into date_time(id) value('" + uuid + "')";
			Connection con21 = DBConnection.getConnection();
			boolean result21 = DBUtil.executInsertUpdateDelete(con21, query21);
			String query2 = "select time from date_time where id='" + uuid + "'";
			List<Map<String, Object>> result2 = DBUtil.executeSelectQuery(con21, query2);

			Map<String, Object> out32 = result2.get(0);
			String date_time = out32.get("time").toString();
			String text1 = "Dear User, ur A/c: " + account_number + "-debited by Rs" + massageMoney + " on " + date_time
					+ " .Go for more details www.sbi.com";

			String amount = Integer.toString(tatalAmount);
			EmailUtil emailUtil = new EmailUtil();
			String subject = "IIITV ATM Machine Project Testing";
			boolean first = emailUtil.isSentMail(mail, subject, text1);
			updateATMBalance = (atmMoney) - (checkMoney);

			String query4 = "update atm_machine_balance set note_of_100 =" + (noteCollection[0] - noteCount[3])
					+ " , note_of_200=" + (noteCollection[1] - noteCount[2]) + " , note_of_500="
					+ (noteCollection[2] - noteCount[1]) + " , note_of_2000=" + (noteCollection[3] - noteCount[0])
					+ " where atm_code ='SBIN39872BHFDTDSE'";
			Connection con4 = DBConnection.getConnection();
			output = DBUtil.executInsertUpdateDelete(con4, query4);
			if (result) {
				System.out.println(
						"\n\n\n----------------------: Plese collect your amount :--------------------------\n\n\n ");
				if(noteCount[0]!=0)
				{
				System.out.println("Total Note's of 2,000 = " + noteCount[0]);
				}
				if(noteCount[1]!=0)
				{
				System.out.println("Total Note's of 500   = " + noteCount[1]);
				}
				if(noteCount[2]!=0)
				{
				System.out.println("Total Note's of 200   = " + noteCount[2]);
				}
				if(noteCount[3]!=0)
				{
				System.out.println("Total Note's of 100   = " + noteCount[3]);
				}

				System.out.println("Total Amount is = " + tatalAmount + " Rs.");
				System.out.println("Remaining Amount in your Account is : " + updateBalance + " Rs.");
				System.out.println(
						"\n\n\n###############################: Thank you for using this ATM Machine :############################### ");

			}

		}

	}
}
