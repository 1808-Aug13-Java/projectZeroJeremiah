package com.revature.bank.user;

public class User {

	private String userName;
	private String fName;
	private String lName;
	private String psWord;

	public User() {
		super();
		userName = "default";
		fName = "default";
		lName = "default";
		psWord = "default";
	}

	public User(String userName, String fName, String lName, String psWord) {
		super();
		this.userName = userName;
		this.fName = fName;
		this.lName = lName;
		this.psWord = psWord;
	}

	@Override
	public String toString() {
		return userName + ":" + fName + ":" + lName + ":" + psWord;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fName == null) ? 0 : fName.hashCode());
		result = prime * result + ((lName == null) ? 0 : lName.hashCode());
		result = prime * result + ((psWord == null) ? 0 : psWord.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (fName == null) {
			if (other.fName != null)
				return false;
		} else if (!fName.equals(other.fName))
			return false;
		if (lName == null) {
			if (other.lName != null)
				return false;
		} else if (!lName.equals(other.lName))
			return false;
		if (psWord == null) {
			if (other.psWord != null)
				return false;
		} else if (!psWord.equals(other.psWord))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

}
