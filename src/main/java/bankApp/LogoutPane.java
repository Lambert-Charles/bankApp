package bankApp;

import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;



class LogoutPane {
	
	private PaneName activePane = PaneName.LOGOUT;
			
	private BankApp bankApp;
	
	
	
	LogoutPane(BankApp bankApp){
		
		this.bankApp = bankApp;
		
	}
	
	
	
	void activate() {
		
		bankApp.updateActivePaneName(activePane);		
		
		StackPane pane = new StackPane();
		
		Text dontForgetText = new Text("Successfully logged out.\nThank you for your visit, " + BankApp.customerList.get(BankApp.customerNumber).getName());
		dontForgetText.setFont(Font.font(35));
		
		pane.getChildren().add(dontForgetText);
		
    	bankApp.mainPane.setLayoutY(200);

        bankApp.mainPane.setCenter(pane);

		bankApp.displayTopAndBottomSections();
        
	}   
}
