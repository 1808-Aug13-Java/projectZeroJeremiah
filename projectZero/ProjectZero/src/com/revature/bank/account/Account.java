package com.revature.bank.account;

import com.revature.bank.user.User;

public class Account {

	private User user;
	private long ballance;

	public Account() {
		super();
		user = new User();
		ballance = 0L;
	}

	public Account(User user, long ballance) {
		super();
		this.user = user;
		this.ballance = ballance;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public long getBallance() {
		return ballance;
	}

	public void setBallance(long ballance) {
		this.ballance = ballance;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (ballance ^ (ballance >>> 32));
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Account other = (Account) obj;
		if (ballance != other.ballance)
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Account [user=" + user + ", ballance=" + ballance + "]";
	}

}
