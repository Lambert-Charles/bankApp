package bankApp;

import javafx.geometry.Pos;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

class PreviousTransactionsPane {

	private BankApp bankApp;
	
	private PaneName activePane = PaneName.PREVIOUS_TRANSACTIONS;
	
	private Text dateText, typeText, amountText, balanceText;
	
	private GridPane gridPane;
	
	
	
	public PreviousTransactionsPane(BankApp bankApp) {
		this.bankApp = bankApp;
	}
	
	
	
    void activate(int firstTransactionNumber){
    	
    	bankApp.mainPane.setLayoutY(100);
    	
    	bankApp.updateActivePaneName(activePane);
    	
    	if(noTransactionsToShow()) {
    		
    		displayNoTransactionsMessage();
    		
    	} else {
    		
    		showTransactions(firstTransactionNumber);
    		
    	}
    	
    	bankApp.displayTopAndBottomSections();
    	
    }



	private void showTransactions(int firstTransactionNumber) {
		
		setTextsElements();
		
		addElementsToGridPane();
	
		showAtMost10Transactions(firstTransactionNumber);
		
		displayPreviousLink(firstTransactionNumber);
		
		displayNextLink(firstTransactionNumber);
		
		bankApp.mainPane.setCenter(gridPane);
		
	}



	private void setTextsElements() {
		
		dateText = new Text("Date");
		dateText.setFont(Font.font("Arial", FontWeight.BOLD, 25));
		
		typeText = new Text("Type");
		typeText.setFont(Font.font("Arial", FontWeight.BOLD,25));
		
		amountText = new Text("Amount");
		amountText.setFont(Font.font("Arial", FontWeight.BOLD,25));
		
		balanceText = new Text("Balance");
		balanceText.setFont(Font.font("Arial", FontWeight.BOLD,25));
		
	}
	
	
	
	private void addElementsToGridPane() {
		
		gridPane = new GridPane();
		gridPane.setAlignment(Pos.CENTER);
		gridPane.add(dateText, 0, 0);
		gridPane.add(typeText, 1, 0);
		gridPane.add(amountText, 2, 0);
		gridPane.add(balanceText, 3, 0);
		
	}

	
	
	private void showAtMost10Transactions(int firstTransactionNumber) {
		
		int transactionToDisplay = 0 ;

		for(int i = 0 ; i  + firstTransactionNumber < BankApp.customerList.get(BankApp.customerNumber).getTransactionsList().size() && i < 10 ; i++) {

			Transaction transaction = BankApp.customerList.get(BankApp.customerNumber).getTransactionsList().get(firstTransactionNumber + i);

			Text transactionDate = new Text(transaction.getDate().toString() + "\t");
			transactionDate.setFont(Font.font(20));
			Text transactionType = new Text(transaction.getType() + "\t");
			transactionType.setFont(Font.font(20));
			Text transactionAmount = new Text(Integer.toString(transaction.getAmount()) + "\t\t\t");
			transactionAmount.setFont(Font.font(20));
			Text transactionBalance = new Text(Integer.toString(transaction.getCurrentBalance()) + "\t");
			transactionBalance.setFont(Font.font(20));

			
			gridPane.add(transactionDate, 0 , transactionToDisplay + 1);
			gridPane.add(transactionType, 1 , transactionToDisplay + 1);
			gridPane.add(transactionAmount, 2 , transactionToDisplay + 1);
			gridPane.add(transactionBalance, 3 , ++transactionToDisplay);
		}
		
	}



	private void displayNoTransactionsMessage() {
		
		StackPane pane = new StackPane();
		
		Text noTransactionsText = new Text("You have no previous transactions");
		noTransactionsText.setFont(Font.font(30));
		noTransactionsText.setFill(Color.BLACK);
		
		pane.getChildren().add(noTransactionsText);
		
		bankApp.mainPane.setCenter(pane);
		
	}



	private void displayPreviousLink(int firstTransactionNumber) {
		
		if(firstTransactionNumber >= 10) {
			
			Hyperlink previousLink = new Hyperlink("Previous");
			previousLink.setFont(Font.font("Arial", FontWeight.BOLD, 20));
			previousLink.setStyle("-fx-text-fill: black;");
			previousLink.setBorder(Border.EMPTY);
			
			final int tmp = firstTransactionNumber - 10;
			
			previousLink.setOnAction(event -> activate(tmp));	
			
			gridPane.add(previousLink, 0 , firstTransactionNumber + 11);
		}		
	}



	private void displayNextLink(int firstTransactionNumber) {
		
		if(firstTransactionNumber < BankApp.customerList.get(BankApp.customerNumber).getTransactionsList().size() - 10) {
			
			Hyperlink nextLink = new Hyperlink("Next");
			nextLink.setFont(Font.font("Arial", FontWeight.BOLD, 20));
			nextLink.setStyle("-fx-text-fill: black;");
			nextLink.setBorder(Border.EMPTY);
			
			final int tmp = firstTransactionNumber + 10;
			
			nextLink.setOnAction(event -> activate(tmp));
			
			gridPane.add(nextLink, 3 , firstTransactionNumber + 11);
			
		}
	}



	private boolean noTransactionsToShow() {
		
		return BankApp.customerList.get(BankApp.customerNumber).getTransactionsList() == null || 
				BankApp.customerList.get(BankApp.customerNumber).getTransactionsList().size() == 0;
	
	}
}
