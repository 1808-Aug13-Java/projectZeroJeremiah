package com.revature.bank.driver;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.revature.bank.account.Account;
import com.revature.bank.controller.Controller;

public class Driver {
	public static Scanner console = new Scanner(System.in);

	public static void main(String[] args) {

		String input = "";
		boolean validInput = false;
		boolean loggedIn = false;
		ArrayList<String> accountCreate = new ArrayList<String>();
		Account currUser = null;

		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("resources\\accountDB"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			String line = null;
			try {
				line = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			while (line != null) {
				String[] accountInfo = line.split(":");
				Controller.addUserNameSet(accountInfo[0]);
				line = br.readLine();
			}
			for (String u : Controller.getUserNameSet()) {
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		while (!validInput) {
			System.out.println("To create a new account type create. Otherwise type log in.");
			input = console.nextLine();
			if (input.equalsIgnoreCase("create")) {
				System.out.println("Enter your prefered user name. The character \":\" is not allowed."
						+ "Your user name will be used for log in.");
				accountCreate.add(console.nextLine());
				System.out.println("Enter your first name.");
				accountCreate.add(console.nextLine());
				System.out.println("Enter your last name.");
				accountCreate.add(console.nextLine());
				System.out.println("Enter your password. The character \":\" is not allowed");
				accountCreate.add(console.nextLine());
				System.out.println("What will be your initial deposit? Enter only digits.");
				accountCreate.add(console.nextLine());
				System.out.println("");

				Account acc = Controller.createAccount(accountCreate.get(0), accountCreate.get(1), accountCreate.get(2),
						accountCreate.get(3), Long.parseLong(accountCreate.get(4)));
				if (acc != null) {
					validInput = true;
					Controller.saveAccount(acc);
				}

			} else if (input.equalsIgnoreCase("log in")) {
				while (!loggedIn) {
					System.out.println("Enter your User name for log in.");
					String enteredU = console.nextLine();
					System.out.println("Enter your password.");
					String enteredP = console.nextLine();
					currUser = Controller.loadAccount(enteredU, enteredP);
					System.out.println("logged in successfully as: " + currUser.toString());
					loggedIn = true;
				}
				validInput = true;
				while (loggedIn) {
					System.out.println(
							"To log out type \"exit\". Type \"withdrawl\" to make a withdrawl, or \"deposit\" to make a deposit.");
					input = console.nextLine();
					if (input.equalsIgnoreCase("exit")) {
						loggedIn = false;
					} else if (input.equalsIgnoreCase("withdrawl")) {
						System.out.println("Enter a numeric value greater than 0 to make withdrawl.");
						input = console.nextLine();
						boolean withdrawl = Controller.withdrawl(Long.parseLong(input), currUser);
						if (withdrawl) {
							System.out.println("Withdrawl successful. New Ballance: " + currUser.getBallance());
							Controller.updateAccount(currUser);
						} else {
							System.out.println("Withdrawl failed. Current Ballance: " + currUser.getBallance()
									+ " amount requested: " + input);
						}
					} else if (input.equalsIgnoreCase("deposit")) {
						System.out.println("Enter a numeric value greater than 0 to make deposit.");
						input = console.nextLine();
						boolean deposit = Controller.deposit(Long.parseLong(input), currUser);
						if (deposit) {
							System.out.println("Deposit successful. New Ballance: " + currUser.getBallance());
							Controller.updateAccount(currUser);
						} else {
							System.out.println("Deposit failed. Current Ballance: " + currUser.getBallance()
									+ " amount requested: " + input);
						}
					} else {
						System.out.println("Invalid request. try again");
					}
				}

				System.out.println("logged out successfully");
			} else {
				System.out.println("Invalid request. try again");
			}
		}

		console.close();
	}

}
