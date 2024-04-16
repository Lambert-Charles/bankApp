package bankApp;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Customer implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String userID;
    private String password;
    private int balance;
    private String name;
    private String surname;
    private List<Transaction> transactionsList;
   
    String getUserID() {
		return userID;
	}
    
	void setUserID(String userID) {
		this.userID = userID;
	}
	
	String getPassword() {
		return password;
	}
	
	void setPassword(String password) {
		this.password = password;
	}
	
	int getBalance() {
		return balance;
	}
	
	void setBalance(int balance) {
		this.balance = balance;
	}
	
	public String getName() {
		return name;
	}
	
	void setName(String name) {
		this.name = name;
	}
	
	String getSurname() {
		return surname;
	}
	
	void setSurname(String surname) {
		this.surname = surname;
	}
	
	List<Transaction> getTransactionsList() {
		return transactionsList;
	}
	
	void setTransactionsList(List<Transaction> list) {
		this.transactionsList = list;
	}

	
	
	void addTransaction(Transaction transaction) {
		this.transactionsList.add(transaction);
	}
	
	
	
	public Customer(String userID, String password, String name, String surname) {
		this.userID = userID;
		this.password = password;
		this.name = name;
		this.surname = surname;
		
		transactionsList = new ArrayList<Transaction>();
	}
	
	public Customer() {
		
	}
	
}