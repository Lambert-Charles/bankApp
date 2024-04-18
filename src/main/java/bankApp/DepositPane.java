package bankApp;

import java.time.LocalDate;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;



class DepositPane {
	
	private PaneName activePane = PaneName.DEPOSIT_REQUEST;
	
	private Text amountText, notAnIntegerText;

	private TextField amountTextField;
	
	private Button amountButton;
	
	private BankApp bankApp;
	
	private VBox vBox;
	
	private HBox hBox;
	
	
	
	DepositPane(BankApp bankApp){
		this.bankApp = bankApp;
	}
	
	
	
    void activate(){
    	
    	bankApp.updateActivePaneName(activePane);
    	
    	initializeElements();
    	
    	setElementsStyles();
    	
    	setAmountButtonAction();
    	
    	addElementsToBoxes();
    	
    	bankApp.mainPane.setLayoutY(200);
    	bankApp.mainPane.setCenter(vBox);

		bankApp.displayTopAndBottomSections();

    }



	private void initializeElements() {
    	
    	vBox = new VBox();
    	
    	hBox = new HBox();
    	
    	amountText = new Text("How much money do you want to deposit? ");
    	
    	notAnIntegerText = new Text("Please enter a valid integer");

    	amountTextField = new TextField();
    	
    	amountButton = new Button("OK");
    	
	}
	
    
    
    private void setElementsStyles() {
    	
    	vBox.setAlignment(Pos.CENTER);
    	vBox.setSpacing(10);

    	hBox.setAlignment(Pos.CENTER);
    	hBox.setSpacing(10);
    	
    	amountText.setFill(Color.BLACK);
    	amountText.setFont(Font.font(30));
    	
    	amountTextField.setPrefColumnCount(5);
    	
    	notAnIntegerText.setFill(Color.TRANSPARENT);	
    	
	}
    
    

	private void setAmountButtonAction() {
		
    	amountButton.setOnAction(event -> {
    		try{
    			
    			int amountTyped = Integer.valueOf(amountTextField.getText());
    			depositAmount(amountTyped);
    			
    		}catch(NumberFormatException e) {
    			
    	    	showNotAnIntegerText();
    	    	
    		} 
    	});
    	
	}



	private void depositAmount(int amountTyped) {
		
		bankApp.depositSuccessfullPane.activate(Integer.valueOf(amountTextField.getText()));
		
	}



	private void showNotAnIntegerText() {
		
		notAnIntegerText.setFill(Color.RED);
		
	}
	
	

	private void addElementsToBoxes() {
    	
    	hBox.getChildren().add(amountTextField);
    	hBox.getChildren().add(amountButton);
    	
    	vBox.getChildren().add(amountText);
    	vBox.getChildren().add(hBox);
    	vBox.getChildren().add(notAnIntegerText);
    	
	}
}



class DepositSuccessfullPane{

	private PaneName activePane = PaneName.DEPOSIT_SUCCESS;
	
	private Transaction transaction;
	
	private Text successfulDepositText;
	
	private VBox pane;
	
	private BankApp bankApp;
	
	
	
	public DepositSuccessfullPane(BankApp bankApp) {
		this.bankApp = bankApp;
	}
	
	
	
	void activate(int amount) {
    	
		bankApp.updateActivePaneName(activePane);
    	
    	BankApp.updateBalance(amount);
		
		createTransaction(amount);
   			
		BankApp.addTransactionToHistorial(transaction);
			
		BankApp.saveDataToFile();
		
		displayDepositSuccessfullMessage();
		
		bankApp.mainPane.setCenter(pane);
	
		bankApp.displayTopAndBottomSections();

    	
    }



	private void createTransaction(int amount) {
		
		transaction = new Transaction(BankApp.customerList.get(BankApp.customerNumber).getBalance(), amount, LocalDate.now(), "Deposit");
		
	}
	


	private void displayDepositSuccessfullMessage() {
		
    	pane = new VBox();
    	pane.setAlignment(Pos.CENTER);
    	pane.setSpacing(10);
    	
		successfulDepositText = new Text("Your money was successfully deposited");
		successfulDepositText.setFont(Font.font(30));
		
		pane.getChildren().add(successfulDepositText);
		
	}
}
