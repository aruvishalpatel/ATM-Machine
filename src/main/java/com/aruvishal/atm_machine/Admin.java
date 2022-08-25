package com.aruvishal.atm_machine;

import java.sql.Connection;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.aruvishal.atm_machine.util.DBConnection;
import com.aruvishal.atm_machine.util.DBUtil;

public class Admin {
	private static Scanner scanner;
	@SuppressWarnings("unused")
	private static int tatalAmount;

	public static void updateATMBalance() throws Exception {
		scanner = new Scanner(System.in);
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
		for (int i = 0; i < 4; i++) {
			System.out.print("Please Enter the note of " + typeNote[i] + " : ");
			String note = scanner.nextLine();
			System.out.print("\n");
			noteCollection[i] = noteCollection[i] + (Integer.parseInt(note));
			// System.out.println(noteCollection[i]);
		}

		String query3 = "update atm_machine_balance set note_of_100=" + noteCollection[0] + ",note_of_200="
				+ noteCollection[1] + ",note_of_500=" + noteCollection[2] + ",note_of_2000=" + noteCollection[3]
				+ " where atm_code ='SBIN39872BHFDTDSE'";

		Connection con3 = DBConnection.getConnection();
		boolean output = DBUtil.executInsertUpdateDelete(con3, query3);
		if (output) {

			int Note[] = { 100, 200, 500, 2000 };
			int Collection[] = { 0, 0, 0, 0 };
			for (int i = 0; i < 4; i++) {
				String query7 = "select note_of_" + Note[i] + " from atm_machine_balance";
				Connection con7 = DBConnection.getConnection();
				List<Map<String, Object>> result7 = DBUtil.executeSelectQuery(con7, query7);
				Map<String, Object> out7 = result7.get(0);
				String saving1 = out7.get("note_of_" + Note[i]).toString();
				int allNote = Integer.parseInt(saving1);
				Collection[i] = allNote;
				// System.out.println(noteCollection[i]);

			}

			System.out.println(
					"\n\n++++++++++++++++++++++++++: Your ATM Money is Updated Now :++++++++++++++++++++++++++++++++");

			System.out.println("Total Note's of 2,000 = " + Collection[3]);
			System.out.println("Total Note's of 500   = " + Collection[2]);
			System.out.println("Total Note's of 200   = " + Collection[1]);
			System.out.println("Total Note's of 100   = " + Collection[0]);
			tatalAmount = ((2000 * Collection[0]) + (500 * Collection[1]) + (200 * Collection[2])
					+ (100 * Collection[3]));

		}
	}
}
