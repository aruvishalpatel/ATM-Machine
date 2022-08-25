package com.aruvishal.atm_machine;

import java.util.*;

public class FastCash {

	private Scanner scanner;

	void cashFast(String account_number, String password) throws Exception {
		scanner = new Scanner(System.in);
		System.out.println("\n\n\n\nPress 1 for: 100 Rs                             Press 5 for: 5,000 Rs\n");
		System.out.println("Press 2 for: 200 Rs                             Press 6 for: 10,000 Rs\n");
		System.out.println("Press 3 for: 500 Rs                             Press 7 for: 15,000 Rs\n");
		System.out.println("Press 4 for: 2,000 Rs                           Press 8 for: 20,000 Rs\n");
		System.out.println("++++++++++++++-: Please choose which opration are you going to do :-++++++++++++++");
		boolean isValidKey = true;
		int choice = 0;
		try {
			choice = Integer.parseInt(scanner.nextLine());
		} catch (Exception e) {
			isValidKey = false;
			System.out.println("\n\n\n--------------------------: Invalid key :--------------------------- \n\n");
		}
		if (isValidKey) {
			switch (choice) {
			case 1: {
				TransactionManagement transaction = new TransactionManagement();
				transaction.managementTransaction("100", account_number, password);
				break;
			}
			case 2: {
				TransactionManagement transaction = new TransactionManagement();
				transaction.managementTransaction("200", account_number, password);
				break;
			}
			case 3: {
				TransactionManagement transaction = new TransactionManagement();
				transaction.managementTransaction("500", account_number, password);
				break;
			}
			case 4: {
				TransactionManagement transaction = new TransactionManagement();
				transaction.managementTransaction("2000", account_number, password);
				break;
			}
			case 5: {
				TransactionManagement transaction = new TransactionManagement();
				transaction.managementTransaction("5000", account_number, password);
				break;
			}
			case 6: {
				TransactionManagement transaction = new TransactionManagement();
				transaction.managementTransaction("10000", account_number, password);
				break;
			}
			case 7: {
				TransactionManagement transaction = new TransactionManagement();
				transaction.managementTransaction("15000", account_number, password);
				break;
			}
			case 8: {
				TransactionManagement transaction = new TransactionManagement();
				transaction.managementTransaction("20000", account_number, password);
				break;
			}
			default:
				System.out.print("--------------------------------: Enter Invalid Key :------------------------------");
				break;
			}
		}
	}
}
