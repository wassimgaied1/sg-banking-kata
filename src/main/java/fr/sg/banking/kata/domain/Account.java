package fr.sg.banking.kata.domain;

import fr.sg.banking.kata.enmus.TransactionType;
import fr.sg.banking.kata.utils.DateParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintStream;
import java.text.ParseException;
import java.util.*;


public class Account {

	public static Logger logger = LoggerFactory.getLogger(Account.class);

	private double balance=0.00 ;
	private static final String LINE_FORMAT = "%10s   |%20s|%20s|%20s";
	public static final String HEADER = String.format(LINE_FORMAT, "operation", "date",  "amount", "balance");
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

	public void withdraw(double amount, String date) throws ParseException {
		this.addTransaction(TransactionType.WITHDRAWAL,amount,this.dateParser.getDateFromString(date));

	}

	public void printAllTransaction() {

		SortedSet<Transaction> sortedTransaction = new TreeSet<>(Comparator.comparing(Transaction::getDate));
		sortedTransaction.addAll(this.transactions);
		StringBuilder builder= new StringBuilder();
		builder.append(HEADER);
		for (Transaction transaction : sortedTransaction) {
			builder.append(System.lineSeparator()).append(buildStringFormat(transaction));
		}

		logger.info( builder.toString());

	}

	private void addTransaction( TransactionType transactionType,double amount, Date date) {
		this.balance=transactionType.equals(TransactionType.DEPOSIT)?this.balance+amount:this.balance-amount;
		Transaction transaction = new Transaction(transactionType,amount,date,this.balance);
		this.transactions.add(transaction);
	}

	private String buildStringFormat(Transaction transaction) {
		return String.format(LINE_FORMAT, transaction.getTransactionType().toString(), this.dateParser.getStringFromDate(transaction.getDate()),  transaction.getAmount(), transaction.getCurrentBalance());
	}
}
