package com.revature.bank.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

import com.revature.bank.account.Account;
import com.revature.bank.user.User;

public class Controller {

	static private HashSet<String> userNameSet = new HashSet<String>();
	static private HashMap<String, String> psWrd = new HashMap<String, String>();
	private static final String FILENAME = "resources\\accountDB";

	public static HashSet<String> getUserNameSet() {
		return userNameSet;
	}

	public static void addUserNameSet(String userName) {
		userNameSet.add(userName);
	}

	public static HashMap<String, String> getPsWrd() {
		return psWrd;
	}

	public static void putPsWrd(String userName, String passWrd) {
		psWrd.put(passWrd, userName);
	}

	public static Account CreateAccount(String uName, String fName, String lName, String psWord, long bal) {

		Scanner console = new Scanner(System.in);
		int setSize = userNameSet.size();
		boolean isAccCreated = false;

		while (!isAccCreated) {
			userNameSet.add(uName);
			if (userNameSet.size() == setSize) {
				System.out.println("That user name has already been taken. Please enter a different user name.");
				console = new Scanner(System.in);
				uName = console.nextLine();
				userNameSet.add(uName);
			}
			if (userNameSet.size() != setSize) {
				isAccCreated = true;
			}
		}

		User user = new User(uName, fName, lName, psWord);
		Account account = new Account(user, bal);

		System.out.println("New account created: " + account.toString());
		console.close();

		return account;
	}

	public static boolean Withdrawl(long withdrawl, Account account) {

		if (withdrawl > account.getBallance()) {
			return false;
		}
		account.setBallance(account.getBallance() - withdrawl);
		System.out.println("new Ballance: " + account.getBallance());
		return true;
	}

	public static boolean Deposit(long deposit, Account account) {

		account.setBallance(account.getBallance() + deposit);
		System.out.println("new Ballance: " + account.getBallance());
		return true;
	}

	public static boolean saveAccount(Account a) {

		BufferedWriter bw = null;
		FileWriter fw = null;

		try {
			fw = new FileWriter(FILENAME, true);
			bw = new BufferedWriter(fw);
			bw.write(a.toString());

			System.out.println("account Saved");

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return false;
	}

}
