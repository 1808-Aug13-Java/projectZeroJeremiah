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

	public static void main(String[] args) {

		String input = "";

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
				System.out.println("line Read: " + line);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(line);

			while (line != null) {
				String[] accountInfo = line.split(":");
				Controller.addUserNameSet(accountInfo[0]);
				line = br.readLine();
			}
			for (String u : Controller.getUserNameSet()) {
				System.out.println(u);
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

//		Account a = Controller.CreateAccount("frodoBags", "Frodo", "Baggins", "samwell42", 1000L);
//		Controller.saveAccount(a);

		Scanner console = new Scanner(System.in);
		boolean validInput = false;
		boolean loggedIn = false;
		ArrayList<String> accountCreate = new ArrayList<String>();
		String[] logIn = new String[2];
		while (!validInput) {
			System.out.println("To create a new account type create. Otherwise type log in.");
			input = console.nextLine();
			if (input.equals("create")) {
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
				validInput = true;
				Controller.saveAccount(acc);

			} else if (input.equals("log in")) {
				while(!loggedIn) {
					int i =0;
					System.out.println("Enter your User name for log in.");
					logIn[i] = console.nextLine();
					System.out.println("Enter your password.");
					logIn[i++] = console.nextLine();
					loggedIn = Controller.logIn(logIn[0], logIn[1]);
					if(loggedIn) {
//						Account currUser = Controller.loadAccount(logIn[0], logIn[1]);
					}
					
				}

			} else {
				System.out.println("To create a new account type create. Otherwise type log in.");
				input = console.nextLine();
			}
		}
		
		console.close();
	}

}
