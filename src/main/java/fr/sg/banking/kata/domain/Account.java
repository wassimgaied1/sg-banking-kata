package fr.sg.banking.kata.domain;

import fr.sg.banking.kata.enmus.TransactionType;
import fr.sg.banking.kata.utils.DateParser;

import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class Account {

	private double balance=0.00 ;
	private Set<Transaction> transactions = new HashSet<>();
	private DateParser dateParser;


	public Account(DateParser dateParser) {
		this.dateParser = dateParser;
	}



	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Set<Transaction> getTransactions() {
		return transactions;
	}

	public void deposit(double amount, String date) throws ParseException {
		this.addTransaction(TransactionType.DEPOSIT,amount,this.dateParser.getDateFromString(date));
	}

	private void addTransaction( TransactionType transactionType,double amount, Date date) {
		this.balance=transactionType.equals(TransactionType.DEPOSIT)?this.balance+amount:this.balance-amount;
		Transaction transaction = new Transaction(transactionType,amount,date,this.balance);
		this.transactions.add(transaction);
	}

	public void withdraw(double amount, String date) throws ParseException {
		this.addTransaction(TransactionType.WITHDRAWAL,amount,this.dateParser.getDateFromString(date));

	}
}
