package bankApp;

import java.time.LocalDate;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


class WithdrawalPane {
	
	private BankApp bankApp;
	
	private PaneName activePane = PaneName.WITHDRAWAL_REQUEST;
	
	private Text optionText, otherAmountText, notAnIntegerText;

	private Hyperlink twentyEurosHyperLink, fiftyEurosHyperLink, oneHundredEurosHyperLink;
	
	private TextField otherAmountTextField;
	
	private HBox otherAmountBox;
	
	private Button otherAmountButton;
	
	private GridPane pane;
	
	private AmountWithdrawn amountWithdrawn;
		
	
	
	WithdrawalPane(BankApp bankApp){
		this.bankApp = bankApp;
		amountWithdrawn = new AmountWithdrawn(bankApp);
	}
	
	
	
    void activate(){
    	
    	bankApp.updateActivePaneName(activePane);
    	
    	initializeElements();
    	
    	setTextsStyles();
    	
    	setHyperlinksStyles();
    	
    	setHyperlinksActions();
    	
    	setOtherElementsStyles();
    	
    	setPaneLayout();
    	
    	setOtherAmountButtonAction();
    	
    	addElementsToOtherAmountBox();
    	
    	addElementsToPane();
    	
    	bankApp.mainPane.setLayoutY(100);

    	bankApp.mainPane.setCenter(pane);

    	bankApp.displayTopAndBottomSections();
    }



	private void initializeElements() {
		
    	optionText = new Text("Choose withdrawal amount:");
    	otherAmountText = new Text("Specify other amount ");
    	notAnIntegerText = new Text("Please enter a valid integer");

    	twentyEurosHyperLink = new Hyperlink("20 €");
    	fiftyEurosHyperLink = new Hyperlink("50 €");
    	oneHundredEurosHyperLink = new Hyperlink("100 €");
    	
    	otherAmountTextField = new TextField();
    	
    	otherAmountBox = new HBox();
    	
    	otherAmountButton = new Button("OK");
    	
    	pane = new GridPane();
		
	}
	
	
	
	private void setTextsStyles() {
		
    	optionText.setFont(Font.font("Arial", FontWeight.BOLD, 30));
    	
    	otherAmountText.setFont(Font.font(30));
    	otherAmountText.setFill(Color.BLACK);
    	
    	notAnIntegerText.setFill(Color.TRANSPARENT);
		GridPane.setHalignment(notAnIntegerText, HPos.RIGHT);

	}
	
	
	
	private void setHyperlinksStyles() {
		
		setTwentyEurosHyperLinkStyle();
    	
    	setFiftyEurosHyperLinkStyle();
    	
    	setOneHundredEurosHyperLinkStyle();
	}
	
	
    
	private void setOtherElementsStyles() {
    	otherAmountBox.setAlignment(Pos.BOTTOM_LEFT);
    	otherAmountBox.setSpacing(10);
    	otherAmountTextField.setPrefColumnCount(5);
    	
	}



	private void setPaneLayout() {
		
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(12));
		pane.setHgap(6);
		pane.setVgap(6);
		
	}



	private void setOtherAmountButtonAction() {
		
    	otherAmountButton.setOnAction(event -> {
    		try{
    			amountWithdrawn.activate(-Integer.valueOf(otherAmountTextField.getText()));
    		}catch(NumberFormatException e) {
    	    	notAnIntegerText.setFill(Color.RED);
    		} 
    	});
		
	}



	private void addElementsToOtherAmountBox() {
		otherAmountBox.getChildren().add(otherAmountText);
    	otherAmountBox.getChildren().add(otherAmountTextField);
    	otherAmountBox.getChildren().add(otherAmountButton);
		
	}



	private void addElementsToPane() {
		pane.add(optionText, 0, 0);
    	pane.add(twentyEurosHyperLink, 0, 1);
    	pane.add(fiftyEurosHyperLink, 0, 2);
    	pane.add(oneHundredEurosHyperLink, 0, 3);
    	pane.add(otherAmountBox, 0, 4);
    	pane.add(notAnIntegerText, 0, 5);
		
	}



	private void setTwentyEurosHyperLinkStyle() {
		
		twentyEurosHyperLink.setFont(Font.font(30));
    	twentyEurosHyperLink.setStyle("-fx-text-fill: black;");
    	twentyEurosHyperLink.setBorder(Border.EMPTY);
    	twentyEurosHyperLink.setUnderline(false);
    	twentyEurosHyperLink.setFocusTraversable(false);
		
	}



	private void setFiftyEurosHyperLinkStyle() {
		
		fiftyEurosHyperLink.setFont(Font.font(30));
    	fiftyEurosHyperLink.setStyle("-fx-text-fill: black;");
    	fiftyEurosHyperLink.setBorder(Border.EMPTY);
    	fiftyEurosHyperLink.setUnderline(false);
    	fiftyEurosHyperLink.setFocusTraversable(false);
		
	}



	private void setOneHundredEurosHyperLinkStyle() {
		
    	oneHundredEurosHyperLink.setFont(Font.font(30));
    	oneHundredEurosHyperLink.setStyle("-fx-text-fill: black;");
    	oneHundredEurosHyperLink.setBorder(Border.EMPTY);
    	oneHundredEurosHyperLink.setUnderline(false);
    	oneHundredEurosHyperLink.setFocusTraversable(false);
		
	}
	
	
	
	private void setHyperlinksActions() {
		
    	twentyEurosHyperLink.setOnAction(event -> amountWithdrawn.activate(-20));

    	fiftyEurosHyperLink.setOnAction(event ->  amountWithdrawn.activate(-50));

    	oneHundredEurosHyperLink.setOnAction(event ->  amountWithdrawn.activate(-100));

	}
}




class AmountWithdrawn{
	
	private PaneName activePane = PaneName.WITHDRAWAL_SUCCESS;
	
	private Transaction transaction;
	
	private BankApp bankApp;
	
	private VBox pane;
	
	
	
	AmountWithdrawn(BankApp bankApp){
		this.bankApp = bankApp;
	}



    void activate(int amount) {
    	
    	bankApp.updateActivePaneName(activePane);
    	
    	setPaneLayout();
    	
   		if(isBalanceEnough(amount)) {
   			
   			BankApp.updateBalance(amount);
   			
   			createTransaction(amount);
   			
    		BankApp.addTransactionToHistorial(transaction);
   			
    		BankApp.saveDataToFile();
    		
    		displayTransactionOKMessage();
    		
    		
    	} else {
    		
    		displayTransactionNOKMessage(amount);
    	}
   		
		bankApp.mainPane.setCenter(pane);
		
		bankApp.displayTopAndBottomSections();
   		
    }



	private void setPaneLayout() {
    	pane = new VBox();
    	
    	bankApp.mainPane.setLayoutY(200);
    	
    	pane.setAlignment(Pos.CENTER);
    	pane.setSpacing(10);
		
	}



	private void displayTransactionOKMessage() {
		
		Text dontForgetText = new Text("Here you go. Don't forget your money");
		dontForgetText.setFont(Font.font(35));
		
		pane.getChildren().add(dontForgetText);
	}



	private void displayTransactionNOKMessage(int amount) {
		Text negativeBalance = new Text("Sorry, your current balance doesn't allow to withdraw " + amount + " €");
		negativeBalance.setFont(Font.font("Arial", 22));
		
		pane.getChildren().add(negativeBalance);
	}

	

	private void createTransaction(int amount) {
		transaction = new Transaction(BankApp.customerList.get(BankApp.customerNumber).getBalance(), amount, LocalDate.now(), "Withdrawal");
		
	}



	private boolean isBalanceEnough(int amount) {
		return BankApp.customerList.get(BankApp.customerNumber).getBalance() >= amount;
	}
}
