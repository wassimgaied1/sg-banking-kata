package fr.sg.banking.kata.domain;


import fr.sg.banking.kata.enmus.TransactionType;

import java.util.Date;
import java.util.Objects;


public class Transaction {

	private TransactionType transactionType;
	private double amount;
	private Date date;
	private double currentBalance;


	public Transaction(TransactionType transactionType, double amount, Date date, double currentBalance) {
		this.transactionType = transactionType;
		this.amount = amount;
		this.date = date;
		this.currentBalance = currentBalance;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public double getAmount() {
		return amount;
	}

	public Date getDate() {
		return date;
	}

	public double getCurrentBalance() {
		return currentBalance;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Transaction)) return false;
		Transaction that = (Transaction) o;
		return Double.compare(that.getAmount(), getAmount()) == 0 &&
				Double.compare(that.getCurrentBalance(), getCurrentBalance()) == 0 &&
				getTransactionType() == that.getTransactionType() &&
				getDate().equals(that.getDate());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getTransactionType(), getAmount(), getDate(), getCurrentBalance());
	}
}
