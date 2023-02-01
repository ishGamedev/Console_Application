package SerializableObjects;

import java.io.Serializable;

public class Wallet implements Serializable{
	private static final long serialVersionUID = 1L;
	private int balance;
	
	public Wallet() {
		balance = 100;
	}
	
	public void setBalance(int addBalance) {
		balance += addBalance;
	}
	
	public int getBalance() {
		return balance;
	}
}
