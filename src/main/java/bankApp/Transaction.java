package bankApp;

import java.io.Serializable;
import java.time.LocalDate;

public class Transaction implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int currentBalance;
    private int amount;
    private LocalDate date;
    private String type;
   
    
    
    Transaction(int currentBalance, int amount, LocalDate date, String type){
        this.setCurrentBalance(currentBalance);
        this.setAmount(amount);
        this.setDate(date);
        this.setType(type);
    }

	public int getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(int currentBalance) {
		this.currentBalance = currentBalance;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}