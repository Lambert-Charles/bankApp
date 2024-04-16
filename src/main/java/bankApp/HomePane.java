package bankApp;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


class HomePane{
	
	private PaneName activePane = PaneName.HOME;
	
	private GridPane pane;
	
	private Text optionText;
	
	private Hyperlink withdrawalHyperLink, checkBalanceHyperLink, previousTransactionsHyperLink, depositHyperLink;

	
	
    void activate(BankApp bankApp) {
    	
    	bankApp.updateActivePaneName(activePane);

    	initializeElements(bankApp);
    	
    	setTextsFonts();
    	
    	setHyperLinksStyles();
    	
    	setHyperLinksActions(bankApp);
    	
    	addElementsToPane();
    	
    	setLayouts(bankApp);
    	
		bankApp.displayTopAndBottomSections();

    }
    
    
    
	private void initializeElements(BankApp bankApp) {
		
		pane = new GridPane();
		
    	optionText = new Text(BankApp.customerList.get(BankApp.customerNumber).getName() + ", what do you want to do?");
    	
    	withdrawalHyperLink = new Hyperlink("Withdraw");
    	
    	checkBalanceHyperLink = new Hyperlink("Check balance");
    	
    	previousTransactionsHyperLink = new Hyperlink("See previous transactions");
    	
    	depositHyperLink = new Hyperlink("Deposit money");
    	
	}
	
	
	
	private void setTextsFonts() {
		
		optionText.setFont(Font.font("Arial", FontWeight.BOLD, 30));
		
	}
	
	
	private void setHyperLinksStyles() {
		
		setWithdrawalHyperLinkStyle();
    	
		setCheckBalanceHyperLinkStyle();
    	
		setPreviousTransactionsHyperLinkStyle();
    	
		setDepositHyperLinkStyle();

	}
	
	
	
	private void setWithdrawalHyperLinkStyle() {
		withdrawalHyperLink.setFont(Font.font(30));
    	withdrawalHyperLink.setStyle("-fx-text-fill: black;");
    	withdrawalHyperLink.setBorder(Border.EMPTY);
    	withdrawalHyperLink.setUnderline(false);
    	withdrawalHyperLink.setFocusTraversable(false);
		
	}



	private void setCheckBalanceHyperLinkStyle() {
    	checkBalanceHyperLink.setFont(Font.font(30));
    	checkBalanceHyperLink.setStyle("-fx-text-fill: black;");
    	checkBalanceHyperLink.setBorder(Border.EMPTY);
    	checkBalanceHyperLink.setUnderline(false);
    	checkBalanceHyperLink.setFocusTraversable(false);
		
	}



	private void setPreviousTransactionsHyperLinkStyle() {
    	previousTransactionsHyperLink.setFont(Font.font(30));
    	previousTransactionsHyperLink.setStyle("-fx-text-fill: black;");
    	previousTransactionsHyperLink.setBorder(Border.EMPTY);
    	previousTransactionsHyperLink.setUnderline(false);
    	previousTransactionsHyperLink.setFocusTraversable(false);
		
	}



	private void setDepositHyperLinkStyle() {
    	depositHyperLink.setFont(Font.font(30));
    	depositHyperLink.setStyle("-fx-text-fill: black;");
    	depositHyperLink.setBorder(Border.EMPTY);
    	depositHyperLink.setUnderline(false);
    	depositHyperLink.setFocusTraversable(false);
		
	}



	private void setHyperLinksActions(BankApp bankApp) {
		
    	withdrawalHyperLink.setOnAction(event -> bankApp.withdrawalPane.activate());

    	checkBalanceHyperLink.setOnAction(event -> bankApp.balancePane.activate(bankApp));

    	previousTransactionsHyperLink.setOnAction(event -> bankApp.previousTransactionsPane.activate(0));

    	depositHyperLink.setOnAction(event -> bankApp.depositPane.activate());
	}
	
	
	
	private void addElementsToPane() {
		
    	pane.add(optionText, 0, 0);
    	pane.add(withdrawalHyperLink, 0, 1);
    	pane.add(depositHyperLink, 0, 2);
    	pane.add(checkBalanceHyperLink, 0, 3);
    	pane.add(previousTransactionsHyperLink, 0, 4);
		
	}
	
	
	
	private void setLayouts(BankApp bankApp) {
		
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(12));
		pane.setHgap(6);
		pane.setVgap(6);
    	
		bankApp.mainPane.setLayoutY(100);

    	bankApp.mainPane.setCenter(pane);
	}
}