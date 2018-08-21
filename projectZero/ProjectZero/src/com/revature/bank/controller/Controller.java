package com.revature.bank.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
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

	public static Account createAccount(String uName, String fName, String lName, String psWord, long bal) {

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
//		console.close();

		return account;
	}

	public static Account loadAccount(String uName, String psword) {

		Scanner console = new Scanner(System.in);

		BufferedReader br = null;
		boolean password = false;
		boolean userFound = false;
		int userIndex;
		ArrayList<String> accInfoRead = new ArrayList<String>();
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
				for (String s : accountInfo) {
					accInfoRead.add(s);
					System.out.println(" info read: " + s);
				}
				try {
					line = br.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			while (!userFound) {
				if (accInfoRead.contains(uName)) {
					userFound = true;
					userIndex = accInfoRead.indexOf(uName);
					System.out.println("paswrd: " + accInfoRead.get(userIndex + 3));
					while (!password) {
						if (!accInfoRead.get(userIndex + 3).equals(psword)) {
							System.out.println("Invalid password. Please try again. " + psword);
							psword = console.nextLine();
						} else {
							System.out.println("password correct: " + psword);
							password = true;
						}
					}
//					console.close();
					return retrieveAccount(accInfoRead.get(userIndex), accInfoRead.get(userIndex + 1),
							accInfoRead.get(userIndex + 2), accInfoRead.get(userIndex + 3),
							Long.parseLong(accInfoRead.get(userIndex + 4)));

				} else {
					System.out.println("User does not exist");
				}
			}
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

//		User user = new User(uName, fName, lName, psWord);
//		Account account = new Account(user, bal);

//		console.close();

		return null;
	}

	public static Account retrieveAccount(String uName, String fName, String lName, String psWord, long bal) {

		User user = new User(uName, fName, lName, psWord);
		Account account = new Account(user, bal);

		return account;
	}

	public static boolean withdrawl(long withdrawl, Account account) {

		if (withdrawl > account.getBallance()) {
			return false;
		}
		account.setBallance(account.getBallance() - withdrawl);
		System.out.println("new Ballance: " + account.getBallance());
		return true;
	}

	public static boolean deposit(long deposit, Account account) {

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

	public static boolean updateAccount(Account a) {

		BufferedWriter bw = null;
		FileWriter fw = null;
		BufferedReader br = null;
		int c = 0;

		ArrayList<String> accInfoRead = new ArrayList<String>();
		try {
			br = new BufferedReader(new FileReader("resources\\accountDB"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			String line = null;
			try {
				line = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			while (line != null) {

				String[] accountInfo = line.split("\n");
				for (String s : accountInfo) {
					accInfoRead.add(s + "\n");
					System.out.println(" info read: " + s);
					c++;
					if(s.substring(0, a.getUser().getUserName().length()).equals(a.getUser().getUserName())) {
						System.out.println("found user: " + a.getUser().getUserName());
						accInfoRead.remove(c-1);
						accInfoRead.add(c-1, a.toString());;
					}
				}
				try {
					line = br.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			fw = new FileWriter(FILENAME);
			bw = new BufferedWriter(fw);
			for(String s: accInfoRead) {
				bw.write(s);
			}
			System.out.println("account updated");

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

	public static boolean logIn(String userName, String passWord) {
		return false;
	}

}
