package com.revature.bank.driver;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
		System.out.println("To create a new account type create. Otherwise type new.");
		console = new Scanner(System.in);
		
	}

}
