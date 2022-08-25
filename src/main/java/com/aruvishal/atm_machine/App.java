package com.aruvishal.atm_machine;

import java.sql.Connection;

import java.util.*;

import com.aruvishal.atm_machine.util.DBConnection;
import com.aruvishal.atm_machine.util.DBUtil;
import com.aruvishal.atm_machine.util.EmailUtil;
import com.aruvishal.atm_machine.util.TransferMoney;
import com.aruvishal.atm_machine.util.Withdraw;

public class App {

	private static boolean outcome;
	private static int pas;

	@SuppressWarnings({ "static-access", "unused" })
	public static void main(String[] args) throws Exception {

		Scanner scanner = new Scanner(System.in);
		System.out.println("Press 1 for Login Account: ");
		System.out.println("Press 2 for Create New Account: ");
		System.out.println("Press 3 for ATM Admin: ");
		int optionChoice = 0;
		boolean isValidVariable = true;
		try {
			optionChoice = Integer.parseInt(scanner.nextLine());
		} catch (Exception e) {
			System.out.println("\n\n\n You press a invalid key: \n\n\n");
			isValidVariable = false;
		}
		if (isValidVariable) {
			switch (optionChoice) {
			case 1: {
				System.out.print("Enter the Account Number: ");
				String account_number = scanner.nextLine();
				System.out.print("Enter the Password(PIN): ");
				String password = scanner.nextLine();

				LoginManagement login = new LoginManagement();
				boolean output = login.isValidUser(account_number, password);
				if (output) {
					System.out.println("\n \n Wellcome to ATM Machine : \n \n \n");
					System.out.print("Press 1 for: FAST CASH");
					System.out.print("                       Press 2 for: CASH WITHDRAWAL\n\n");
					System.out.print("Press 3 for: TRANSFER");
					System.out.print("                        Press 4 for: PIN CHANGE\n\n");
					System.out.print("Press 5 for: BALANCE ENQUIRY");
					System.out.print("                Press 6 for: MINI STATEMENT\n\n");
					System.out.print(
							"++++++++++++++-: Please choose which opration are you going to do :-++++++++++++++ ");
					int choice = 0;
					boolean isValidChoice = true;
					try {
						choice = Integer.parseInt(scanner.nextLine());
					} catch (Exception e) {
						System.out.println(
								"\n\n\n -------------------:You press a invalid key:-------------------- \n\n\n");
						isValidChoice = false;
					}
					if (isValidChoice) {
						switch (choice) {
						case 1: {
							FastCash fastcash = new FastCash();
							fastcash.cashFast(account_number, password);
							break;
						}
						case 2: {
							System.out.println(
									"\n\n\n$$$$$$$$$$$$$$$$$$$$$$$$: Enter the ammount in multiple of 100 :$$$$$$$$$$$$$$$$$$$$$$$$$ ");
							int money = 0;
							boolean moneyOpration = true;

							try {
								money = Integer.parseInt(scanner.nextLine());
							} catch (Exception e) {
								System.out.println(
										"\n\n\n -------------------: You Enter a invalid money :-------------------- \n\n\n");
								moneyOpration = false;
							}
							if (moneyOpration) {
								if (money % 100 == 0 && money > 0) {
									if (money <= 20000) {
										System.out.println("\n\n\nPress 1:  costomize :");
										System.out.println("Press 2: Noncostomize :");
										System.out.print(
												"\n\n                             ++++++++++++++-: Please choose which opration are you going to do :-++++++++++++++ ");
										boolean cheakDenomination = true;
										int opration = 0;
										try {
											opration = Integer.parseInt(scanner.nextLine());
										} catch (Exception e) {
											System.out.println(
													"\n\n\n -------------------:You press a invalid key:-------------------- \n\n\n");
											cheakDenomination = false;
										}
										if (cheakDenomination) {
											switch (opration) {

											case 1: {
												String Required = Integer.toString(money);

												System.out.println("Press 1 for denomination of 100 :");
												System.out.println("Press 2 for denomination of 200 :");
												System.out.println("Press 3 for denomination of 500 :");
												System.out.println("Press 4 for denomination of 2000 :");
												System.out.print(
														"++++++++++++++-: Please choose which opration are you going to do :-++++++++++++++ ");
												boolean allDenominationChoice = true;
												int opration1 = 0;
												try {
													opration1 = Integer.parseInt(scanner.nextLine());
												} catch (Exception e) {
													System.out.println(
															"\n\n\n -------------------:You press a invalid key:-------------------- \n\n\n");
													System.out.println(
															"\n\n\n#####################################: Thank you for using this ATM Machine :##################################### ");

													cheakDenomination = false;
												}
												if (allDenominationChoice) {
													switch (opration1) {
													case 1: {
														Withdraw vithdrow = new Withdraw();
														vithdrow.cashManagement(Required, account_number, password,
																"100");

														break;
													}
													case 2: {
														Withdraw vithdrow = new Withdraw();
														vithdrow.cashManagement(Required, account_number, password,
																"200");
														break;

													}
													case 3: {
														Withdraw vithdrow = new Withdraw();
														vithdrow.cashManagement(Required, account_number, password,
																"500");
														break;
													}
													case 4: {
														Withdraw vithdrow = new Withdraw();
														vithdrow.cashManagement(Required, account_number, password,
																"2000");
														break;
													}
													default: {
														System.out.println(
																"\n\n\n -------------------:You press a invalid key:-------------------- \n\n\n");
														System.out.println(
																"\n\n\n#####################################: Thank you for using this ATM Machine :##################################### ");

														break;
													}
													}
												}
												break;
											}
											case 2: {
												String Required = Integer.toString(money);

												TransactionManagement management = new TransactionManagement();
												management.managementTransaction(Required, account_number, password);
												break;
											}

											default: {
												System.out.println(
														"\n\n\n -------------------:You press a invalid key:-------------------- \n\n\n");
												System.out.println(
														"\n\n\n#####################################: Thank you for using this ATM Machine :##################################### ");

												break;
											}
											}
										}

									} else {
										System.out.println(
												"\n\n\n+++++++++++++++++++++++++++++++++: Exceding the Limit of Denomination :+++++++++++++++++++++++++++++++");
										System.out.println(
												"\n\n\n#####################################: Thank you for using this ATM Machine :##################################### ");

									}
								} else if (money <= 0) {
									System.out.println(
											"\n\n\n++++++++++++++++++++++: Please Entering a valid amount :+++++++++++++++++++++++++++");
									System.out.println(
											"\n\n\n#####################################: Thank you for using this ATM Machine :##################################### ");

								} else {
									System.out.println(
											"\n\n\n++++++++++++++++++++++: Please Entering money Only multiple of 100 :+++++++++++++++++++++++++++");
									System.out.println(
											"\n\n\n#####################################: Thank you for using this ATM Machine :##################################### ");

								}
							}

							break;
						}

						case 3: {

							System.out.print(
									"\n\n\n@@@@@@@@@@@@@@@@@@@@@@@@@@: Enter the target account number :@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ ");
							String target_account = scanner.nextLine();

							String query12 = "select count(*) as count from account_number_detail where account_number="
									+ target_account;
							Connection con12 = DBConnection.getConnection();
							int count = DBUtil.getresultSetCount(con12, query12);
							if (count == 1) {

								BalanceInquiry balanceinqyuiry = new BalanceInquiry();
								String saving_balance = balanceinqyuiry.savingBalance(account_number);
								System.out.print(
										"\n\n\n@@@@@@@@@@@@@@@@@@@@@@@@@@Enter the money :@@@@@@@@@@@@@@@@@@@@@@@@@@ ");
								int money = 0;

								boolean isValidAmount = true;
								try {
									money = scanner.nextInt();
								} catch (Exception e) {
									System.out.println(
											"%%%%%%%%%%%%%%%%%%%%%% Sorry please enter a valid amount %%%%%%%%%%%%%%%%%%%%%%%%");
									System.out.println(
											"\n\n\n#####################################: Thank you for using this ATM Machine :##################################### ");

									isValidAmount = false;
								}
								if (isValidAmount) {
									if (money < 50000 && money > 0 && money < Double.parseDouble(saving_balance)) {

										TransferMoney transfe = new TransferMoney();
										transfe.transferedMoney(account_number, target_account, money);

									} else if (money < 50000 && money > 0) {
										System.out.println(
												"################## Sorry your account have not sufficiant balance ###################");
										System.out.println(
												"\n\n\n#####################################: Thank you for using this ATM Machine :##################################### ");

									} else {
										System.out.println(
												"############################## Exceed the limit #################################");
										System.out.println(
												"\n\n\n#####################################: Thank you for using this ATM Machine :##################################### ");

									}

								}
							} else {
								System.out.println(
										"------------------------------: Target accuont is not found :-------------------------");
								System.out.println(
										"\n\n\n#####################################: Thank you for using this ATM Machine :##################################### ");

							}

							break;
						}
						case 4: {
							OTP op = new OTP();
							PasswordManagement pass = new PasswordManagement();
							System.out.println("Program Started...................");
							String otpmassage = op.generateOTP("5");
							// System.out.println("Generate OTP :" +
							// otpmassage);
							String query = "select mobile_number,e_mail from account_number_detail where account_number= "
									+ account_number;

							Connection con1 = DBConnection.getConnection();
							List<Map<String, Object>> result1 = DBUtil.executeSelectQuery(con1, query);
							Map<String, Object> out = result1.get(0);
							String number = out.get("mobile_number").toString();
							String mail = out.get("e_mail").toString();
							String mobiletext = "Your PIN change OTP is " + otpmassage;

							outcome = pass.sendOTP(otpmassage, number, mobiletext);
							if (outcome) {

								System.out.println(
										"\n\n######################:- Enter new PIN with 6 digit: -:##########################");
								boolean validPin = true;
								pas = 0;
								try {
									pas = scanner.nextInt();

								} catch (Exception e) {
									System.out.println(
											"########################## Invalid Entry #############################");
									System.out.println(
											"\n\n\n#####################################: Thank you for using this ATM Machine :##################################### ");

									validPin = false;
								}
								if (validPin) {
									if (pas < 1000000 && pas > 99999) {
										String passwor = Integer.toString(pas);
										String query0 = "update account_number_detail set password =" + passwor
												+ " where account_number =  " + account_number;
										Connection con3 = DBConnection.getConnection();
										boolean result = DBUtil.executInsertUpdateDelete(con3, query0);
										if (result) {
											String text1 = "Do not sare this message: dear Users now your ATM Machine updated pin is:- "
													+ pas;
											EmailUtil emailUtil = new EmailUtil();
											String subject = "IIITV ATM Machine Project Testing";
											boolean first = emailUtil.isSentMail(mail, subject, text1);
											System.out.println(
													"@@@@@@@@@@@@@@@@@@@ Your ATM Pin is Updated Succesfuly @@@@@@@@@@@@@@@@@@@@@");
											System.out.println(
													"\n\n\n#####################################: Thank you for using this ATM Machine :##################################### ");

										} else {
											System.out.println(
													"########################## Some error in ATM Machine #############################");
											System.out.println(
													"########################## Your password is not updated #############################");
											System.out.println(
													"\n\n\n#####################################: Thank you for using this ATM Machine :##################################### ");

										}
									} else {
										System.out.println(
												"########################## Invalid Entry #############################");
										System.out.println(
												"########################## Your password is not updated #############################");
										System.out.println(
												"\n\n\n#####################################: Thank you for using this ATM Machine :##################################### ");

									}
								}
							} else {
								System.out.println(
										"%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% you are offline mode %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
								System.out.println(
										"\n\n\n#####################################: Thank you for using this ATM Machine :##################################### ");

							}
							break;
						}
						case 5: {

							BalanceInquiry balance = new BalanceInquiry();

							String savingBalance = balance.savingBalance(account_number);
							String saving_balance = savingBalance;

							// double current_balance =
							// Double.parseDouble(current_balance);

							System.out.println("\n \nTotal Saving Balance : " + saving_balance + " Rs.");

							System.out.println(
									"\n\n\n ------------------: Thank you for using this ATM Machine :----------------------");
							break;
						}
						case 6: {
							LastTransaction lasttransaction = new LastTransaction();
							lasttransaction.lastTransaction(account_number);
							break;
						}
						default:
							System.out.println("\n \n Wellcome to ATM Machine : \n \n \n");
							break;
						}

					}
				} else {
					System.out.println("\n \n ------------: Invalid Account Number or Password :------------ \n \n \n");
				}
				break;
			}
			case 2: {
				System.out.println("\n \n \n -:Wellcome for New Registration:-\n \n");
				System.out.print("Enter the Name: ");
				String name = scanner.nextLine();
				System.out.print("Enter the Mobile Number: ");
				String mobile_number = scanner.nextLine();
				System.out.print("Enter the E-mail account: ");
				String e_mail = scanner.nextLine();
				System.out.print("Enter the Date_Of_Birth in form of yyyy-mm-dd: ");
				String date_of_birth = scanner.nextLine();
				System.out.print("Enter the address: ");
				String address = scanner.nextLine();
				System.out.print("Now Enter the password(PIN) and you remember it: ");
				String password = scanner.nextLine();
				CreatingManagement creating = new CreatingManagement();
				String account_number = creating.CreateAccount(name, mobile_number, e_mail, date_of_birth, address,
						password);

				System.out.println("\n\n################:- " + account_number + " -:################\n\n\n");
				break;
			}
			case 3: {

				Admin admin = new Admin();
				System.out.print("Please Enter the Admin Password: ");

				String passwor = scanner.nextLine();
				System.out.print("\n\n\n");
				String query1 = "select atm_password from admin where mobile_number='+919988776655'";
				Connection con1 = DBConnection.getConnection();
				List<Map<String, Object>> result3 = DBUtil.executeSelectQuery(con1, query1);
				Map<String, Object> out1 = result3.get(0);
				String atm_password = out1.get("atm_password").toString();
				if (passwor.equals(atm_password) == true) {
					Admin.updateATMBalance();
				} else {
					System.out.println(
							"\n\n\n-----------------: Please Enter a Correct Password :-----------------------");
				}

				break;
			}
			default: {
				System.out.println(
						"\n\n\n ------------------------------: INVALID KEY :-------------------------------- \n\n\n");
			}
			}
		}
		scanner.close();
	}
}
