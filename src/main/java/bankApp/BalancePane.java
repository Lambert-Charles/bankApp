package bankApp;

import java.time.LocalDate;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

class BalancePane{
	
	private PaneName activePane = PaneName.BALANCE;
	
    void activate(BankApp bankApp){
    	
    	bankApp.updateActivePaneName(activePane);
    	
    	StackPane pane = new StackPane();
    	
    	Text balanceText = new Text("Your balance at " + LocalDate.now() + " is : " + BankApp.customerList.get(BankApp.customerNumber).getBalance() + "  €");
    	balanceText.setFill(Color.BLACK);
    	balanceText.setFont(Font.font(30));
    	
    	pane.getChildren().add(balanceText);  	
    	
    	bankApp.mainPane.setLayoutY(200);
    	bankApp.mainPane.setCenter(pane);
    	
		bankApp.displayTopAndBottomSections();

    }
}

