package fr.sg.banking.kata.domain;

import java.util.HashSet;
import java.util.Set;


public class Account {

	private double balance ;
	private Set<Transaction> transactions = new HashSet<>();

	public double getBalance() {
		return balance;
	}

	public Set<Transaction> getTransactions() {
		return transactions;
	}
}
